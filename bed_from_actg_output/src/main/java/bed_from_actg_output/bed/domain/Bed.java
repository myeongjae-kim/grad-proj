package bed_from_actg_output.bed.domain;

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
}
