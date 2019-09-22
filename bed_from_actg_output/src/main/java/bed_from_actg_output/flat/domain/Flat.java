package bed_from_actg_output.flat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Flat {
  private String gffId;
  private String peptide;
  private String geneId;
  private String attribute;

  @Builder
  private Flat(
      @NonNull String gffId,
      @NonNull String peptide,
      @NonNull String geneId,
      @NonNull String attribute
  ) {
    assert gffId.length() > 0;
    assert peptide.length() > 0;
    assert geneId.length() > 0;
    assert attribute.length() > 0;

    this.gffId = gffId;
    this.peptide = peptide;
    this.geneId = geneId;
    this.attribute = attribute;
  }

  @Override
  public String toString() {
    return gffId + "\t" + peptide + "\t" + geneId + "\t" + attribute;
  }
}
