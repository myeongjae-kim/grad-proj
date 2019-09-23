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
  private String seqname;
  private long start;
  private long end;
  private String memo;

  @Builder
  private Bed(@NonNull String seqname, long start, long end, @NonNull String memo) {
    assert seqname.length() > 0;
    assert memo.length() > 0;

    this.seqname = seqname;
    this.start = start;
    this.end = end;
    this.memo = memo;
  }

  public static Bed of(Gff gff, Flat flat) {
    List<String> memo = new ArrayList<>();
    memo.add(flat.getPeptide());
    memo.add(flat.getGeneId());
    memo.add(gff.getStrand().toString());

    return Bed.builder()
        .seqname(gff.getSeqname())
        .start(gff.getStart() - 1)
        .end(gff.getEnd())
        .memo(memo.stream().reduce((prev, curr) -> prev + "_" + curr).orElse(".")).build();
  }

  @Override
  public String toString() {
    return seqname + "\t"
        + start + "\t"
        + end + "\t"
        + memo;
  }
}
