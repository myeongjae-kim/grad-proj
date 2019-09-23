package bed_from_actg_output.bed.domain;

import bed_from_actg_output.flat.domain.Flat;
import static bed_from_actg_output.flat.domain.FlatTest.getFlatFixture;
import bed_from_actg_output.gff.domain.Gff;
import static bed_from_actg_output.gff.domain.GffTest.getGffFixture;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class BedTest {
  @Test
  void construct_ValidInput_ValidOutput() {
    Bed bed = Bed.builder()
        .seqname("chr22")
        .start(22712912L)
        .end(22712966L)
        .memo("YVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+").build();

    then(bed.getSeqname()).isEqualTo("chr22");
    then(bed.getStart()).isEqualTo(22712912L);
    then(bed.getEnd()).isEqualTo(22712966L);
    then(bed.getMemo()).isEqualTo("YVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+");
  }

  @Test
  void of_ValidInput_ValidOutput() {
    Gff gff = getGffFixture();
    Flat flat = getFlatFixture();

    Bed bed = Bed.of(gff, flat);

    then(bed.getSeqname()).isEqualTo(gff.getSeqname());
    then(bed.getStart()).isEqualTo(gff.getStart() - 1);
    then(bed.getEnd()).isEqualTo(gff.getEnd());
    then(bed.getMemo()).isEqualTo(flat.getPeptide() + "_" + flat.getGeneId() + "_" + gff.getStrand());
  }
}
