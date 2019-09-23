package bed_from_actg_output;

import bed_from_actg_output.bed.domain.Bed;
import bed_from_actg_output.bed.service.BedService;
import bed_from_actg_output.common.service.CsvFileService;
import bed_from_actg_output.flat.domain.FlatRepository;
import bed_from_actg_output.gff.domain.GffRepository;
import java.io.IOException;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Application {
  public static void main(String[] args) throws IOException {
    CommandLine cmd = parseArgs(args);
    if (cmd == null) {
      System.exit(1);
    }

    String flatFilePath = cmd.getOptionValue("flat");
    String gffFilePath = cmd.getOptionValue("gff");

    CsvFileService csvFileService = new CsvFileService();

    FlatRepository flatRepository = new FlatRepository(flatFilePath, csvFileService);
    GffRepository gffRepository = new GffRepository(gffFilePath, csvFileService);

    BedService bedService = new BedService(flatRepository, gffRepository);
    List<Bed> beds = bedService.createBeds();

    System.out.println(beds.stream()
        .map(Object::toString)
        .reduce((prev, curr) -> prev + "\n" + curr).orElse(""));
  }

  public static CommandLine parseArgs(String[] args) {
    Options options = new Options();

    Option input = new Option("f", "flat", true, "flat file path");
    input.setRequired(true);
    options.addOption(input);

    Option output = new Option("g", "gff", true, "gff file path");
    output.setRequired(true);
    options.addOption(output);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try {
      return parser.parse(options, args);
    } catch (ParseException e) {
      System.err.println(e.getMessage());
      formatter.printHelp("utility-name", options);
      return null;
    }
  }
}
