package bed_from_actg_output.gff.domain;

import java.util.Arrays;
import lombok.Getter;

@Getter
enum Frame {
  EMPTY ("."), ZERO("0"), ONE("1"), TWO("2");

  private final String frame;

  Frame(String frame) {
    this.frame = frame;
  }

  static Frame of(String frame) {
    return Arrays.stream(values())
        .filter(v -> frame.equals(v.frame))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.format("%s is not a value of Frame.", frame)));
  }

  @Override
  public String toString() {
    return frame;
  }
}
