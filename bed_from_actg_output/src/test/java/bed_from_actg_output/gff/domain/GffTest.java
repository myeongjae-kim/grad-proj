package bed_from_actg_output.gff.domain;

import static java.lang.Double.NaN;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

public class GffTest {
  public static Gff getGffFixture() {
    return Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score("1.0")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1; hid=trf; hstart=1; hend=21").build();
  }

  @Test
  void construct_ValidInput_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score("1.0")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1; hid=trf; hstart=1; hend=21").build();

    then(gff.getSeqname()).isEqualTo("chr22");
    then(gff.getSource()).isEqualTo("ACTG");
    then(gff.getFeature()).isEqualTo("exon");
    then(gff.getStart()).isEqualTo(22712913L);
    then(gff.getEnd()).isEqualTo(22712966L);
    then(gff.getScore()).isEqualTo(1.0);
    then(gff.getStrand()).isEqualTo(Strand.FORWARD);
    then(gff.getFrame()).isEqualTo(Frame.ZERO);
    then(gff.getAttribute().get("ID")).isEqualTo("1");
    then(gff.getAttribute().get("hid")).isEqualTo("trf");
    then(gff.getAttribute().get("hstart")).isEqualTo("1");
    then(gff.getAttribute().get("hend")).isEqualTo("21");
  }

  @Test
  void construct_EmptyAttribute_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score(".")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated(".").build();

    then(gff.getAttribute().size()).isEqualTo(0);
  }

  @Test
  void construct_EmptyScore_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score(".")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1; hid=trf; hstart=1; hend=21").build();

    then(gff.getScore()).isEqualTo(NaN);
  }

  @Test
  void toString_ValidInput_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score("1.0")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1; hid=trf; hstart=1; hend=21").build();

    then(gff.toString())
        .isEqualTo("chr22\tACTG\texon\t22712913\t22712966\t1.0\t+\t0\thid=trf; hend=21; hstart=1; ID=1;");
  }

  @Test
  void toString_EmptyScore_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score(".")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1; hid=trf; hstart=1; hend=21").build();

    then(gff.toString())
        .isEqualTo("chr22\tACTG\texon\t22712913\t22712966\t.\t+\t0\thid=trf; hend=21; hstart=1; ID=1;");
  }

  @Test
  void toString_EmptyAttribute_ValidOutput() {
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score(".")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated(".").build();

    then(gff.toString())
        .isEqualTo("chr22\tACTG\texon\t22712913\t22712966\t.\t+\t0\t.");
  }
}
