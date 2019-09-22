package bed_from_actg_output;

import bed_from_actg_output.common.service.CsvFileService;
import bed_from_actg_output.flat.domain.FlatRepository;
import bed_from_actg_output.gff.domain.GffRepository;
import java.io.IOException;
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

    System.out.println(flatRepository.findAll().get(0));
    System.out.println(gffRepository.findAll().get(0));
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
