package bed_from_actg_output.flat.domain;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import org.junit.jupiter.api.Test;

class FlatTest {
  @Test
  void construct_ValidInput_ValidOutput() {
    Flat flat = Flat.builder()
        .gffId("gffId")
        .geneId("geneId")
        .peptide("peptide")
        .attribute("attribute").build();

    then(flat.getGffId()).isEqualTo("gffId");
    then(flat.getGeneId()).isEqualTo("geneId");
    then(flat.getPeptide()).isEqualTo("peptide");
    then(flat.getAttribute()).isEqualTo("attribute");
  }

  @Test
  void construct_NullGffId_ThrowException() {
    thenThrownBy(() -> Flat.builder()
        .gffId(null)
        .geneId("geneId")
        .peptide("peptide")
        .attribute("attribute").build()).isInstanceOf(NullPointerException.class);
  }

  @Test
  void construct_NullGeneId_ThrowException() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId(null)
        .peptide("peptide")
        .attribute("attribute").build()).isInstanceOf(NullPointerException.class);
  }

  @Test
  void construct_NullPeptide_ThrowException() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId("geneId")
        .peptide(null)
        .attribute("attribute").build()).isInstanceOf(NullPointerException.class);
  }

  @Test
  void construct_NullAttribute_ThrowException() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId("geneId")
        .peptide("peptide")
        .attribute(null).build()).isInstanceOf(NullPointerException.class);
  }

  @Test
  void construct_EmptyGffId_AssertionError() {
    thenThrownBy(() -> Flat.builder()
        .gffId("")
        .geneId("geneId")
        .peptide("peptide")
        .attribute("attribute").build()).isInstanceOf(AssertionError.class);
  }

  @Test
  void construct_EmptyGeneId_AssertionError() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId("")
        .peptide("peptide")
        .attribute("attribute").build()).isInstanceOf(AssertionError.class);
  }

  @Test
  void construct_EmptyPeptide_AssertionError() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId("geneId")
        .peptide("")
        .attribute("attribute").build()).isInstanceOf(AssertionError.class);
  }

  @Test
  void construct_EmptyAttribute_AssertionError() {
    thenThrownBy(() -> Flat.builder()
        .gffId("gffId")
        .geneId("geneId")
        .peptide("peptide")
        .attribute("").build()).isInstanceOf(AssertionError.class);
  }
}
