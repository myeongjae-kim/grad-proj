package bed_from_actg_output.bed.domain;

import bed_from_actg_output.flat.domain.Flat;
import bed_from_actg_output.gff.domain.Gff;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Bed {
  private String geneId;
  private long start;
  private long end;
  private String memo;

  @Builder
  private Bed(@NonNull String geneId, long start, long end, @NonNull String memo) {
    assert geneId.length() > 0;
    assert memo.length() > 0;

    this.geneId = geneId;
    this.start = start;
    this.end = end;
    this.memo = memo;
  }

  public static Bed of(Gff gff, Flat flat) {
    List<String> memo = new ArrayList<>();
    memo.add(flat.getPeptide());
    memo.add(flat.getAttribute());
    memo.add(gff.getStrand().toString());

    return Bed.builder()
        .geneId(flat.getGeneId())
        .start(gff.getStart() - 1)
        .end(gff.getEnd())
        .memo(memo.stream().reduce((prev, curr) -> prev + "_" + curr).orElse(".")).build();
  }
}
