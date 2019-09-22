package bed_from_actg_output.common.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvFileServiceTest {
  private File tempFile;

  @BeforeEach
  void setup() throws IOException {
    tempFile = File.createTempFile("prefix", "suffix");
    tempFile.deleteOnExit();

  }

  @Test
  void readFileOf_FirstLineIsHeader_ValidOutput() throws IOException {
    // given
    FileWriter fileWriter = new FileWriter(tempFile);
    fileWriter.write("GFFID\tPeptide\tGeneID\tAttribute\n" +
        "1\tYVLTQPPSVSVAPGQTAR\tENSG00000211662.2\tCDS\n");
    fileWriter.flush();

    // when
    CsvFileService csvFileService = new CsvFileService();
    List<List<String>> values = csvFileService.readFileOf(tempFile.getPath(), true);

    // then
    then(values.get(0).get(0)).isEqualTo("1");
    then(values.get(0).get(1)).isEqualTo("YVLTQPPSVSVAPGQTAR");
    then(values.get(0).get(2)).isEqualTo("ENSG00000211662.2");
    then(values.get(0).get(3)).isEqualTo("CDS");
  }

  @Test
  void readFileOf_FirstLineIsNotHeader_ValidOutput() throws IOException {
    // given
    FileWriter fileWriter = new FileWriter(tempFile);
    fileWriter.write("chr22\tACTG\texon\t22712913\t22712966\t.\t+\t0\tID=1\n");
    fileWriter.flush();

    // when
    CsvFileService csvFileService = new CsvFileService();
    List<List<String>> values = csvFileService.readFileOf(tempFile.getPath(), false);

    // then
    then(values.get(0).get(0)).isEqualTo("chr22");
    then(values.get(0).get(1)).isEqualTo("ACTG");
    then(values.get(0).get(2)).isEqualTo("exon");
    then(values.get(0).get(3)).isEqualTo("22712913");
    then(values.get(0).get(4)).isEqualTo("22712966");
    then(values.get(0).get(5)).isEqualTo(".");
    then(values.get(0).get(6)).isEqualTo("+");
    then(values.get(0).get(7)).isEqualTo("0");
    then(values.get(0).get(8)).isEqualTo("ID=1");
  }
}
