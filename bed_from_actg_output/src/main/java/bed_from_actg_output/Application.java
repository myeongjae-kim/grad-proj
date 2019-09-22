package bed_from_actg_output;

import org.apache.commons.cli.*;

public class Application {
  public static void main(String[] args) {
    CommandLine cmd = parseArgs(args);
    if (cmd == null) {
      System.exit(1);
    }

    String flatFilePath = cmd.getOptionValue("flat");
    String gffFilePath = cmd.getOptionValue("gff");

    System.out.println(flatFilePath);
    System.out.println(gffFilePath);
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
