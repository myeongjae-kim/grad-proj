package bed_from_actg_output.gff.domain;

import java.util.Arrays;
import lombok.Getter;

@Getter
public
enum Strand {
  EMPTY ("."), FORWARD("+"), REVERSE("-");

  private final String strand;

  Strand(String strand) {
    this.strand = strand;
  }

  static Strand of(String strand) {
    return Arrays.stream(values())
        .filter(v -> strand.equals(v.strand))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.format("%s is not a value of Strand.", strand)));
  }

  @Override
  public String toString() {
    return strand;
  }
}
