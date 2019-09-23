package bed_from_actg_output.bed.domain;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class BedTest {
  @Test
  void construct_ValidInput_ValidOutput() {
    Bed bed = Bed.builder()
        .geneId("chr22")
        .start(22712912L)
        .end(22712966L)
        .memo("YVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+").build();

    then(bed.getGeneId()).isEqualTo("chr22");
    then(bed.getStart()).isEqualTo(22712912L);
    then(bed.getEnd()).isEqualTo(22712966L);
    then(bed.getMemo()).isEqualTo("YVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+");
  }
}
