package bed_from_actg_output;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class ApplicationIntTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Test
  void main_ValidInput_ValidOutput() throws IOException {
    // given
    File flatFile = File.createTempFile("bed_from_actg_output", "flat");
    File gffFile = File.createTempFile("bed_from_actg_output", "gff");
    flatFile.deleteOnExit();
    gffFile.deleteOnExit();
    FileWriter flatFileWriter = new FileWriter(flatFile);
    FileWriter gffFileWriter = new FileWriter(gffFile);
    flatFileWriter.write("GFFID\tPeptide\tGeneID\tAttribute\n" +
        "1\tYVLTQPPSVSVAPGQTAR\tENSG00000211662.2\tCDS\n");
    gffFileWriter.write("chr22\tACTG\texon\t22712913\t22712966\t.\t+\t0\tID=1\n");
    flatFileWriter.flush();
    gffFileWriter.flush();

    String[] args = {"-f", flatFile.getPath(), "-g", gffFile.getPath()};

    System.setOut(new PrintStream(outContent));

    // when
    Application.main(args);
    then(outContent).hasToString("chr22\t22712912\t22712966\tYVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+\n");
  }
}
