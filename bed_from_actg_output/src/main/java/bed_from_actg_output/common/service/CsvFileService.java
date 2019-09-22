package bed_from_actg_output.common.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvFileService {
  public List<List<String>> readFileOf(String path, boolean isFirstRecordHeader) throws IOException {
    return readFileOf(path, isFirstRecordHeader, '\t');
  }

  public List<List<String>> readFileOf(String path, boolean isFirstRecordHeader, char delimiter) throws IOException {
    Reader in = new FileReader(path);

    CSVFormat format = CSVFormat.newFormat(delimiter);
    if (isFirstRecordHeader) {
      format = format.withFirstRecordAsHeader();
    }

    Iterable<CSVRecord> csvRecords = format.parse(in);

    List<List<String>> records = new ArrayList<>();
    for (CSVRecord csvRecord : csvRecords) {
      int size = csvRecord.size();
      List<String> record = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        record.add(csvRecord.get(i));
      }
      records.add(record);
    }

    return records;
  }
}
