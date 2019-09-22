package bed_from_actg_output.common.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvFileReadService {
  public Iterable<CSVRecord> readFileOf(String path) throws IOException {
    Reader in = new FileReader(path);
    return CSVFormat.newFormat('\t').withAllowMissingColumnNames().parse(in);
  }
}
