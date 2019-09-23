package bed_from_actg_output.gff.domain;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FrameTest {
  @ParameterizedTest
  @ValueSource(strings={".", "0", "1", "2"})
  void of_ValidInput_NoException(String value) {
    Frame.of(value);
  }

  @Test
  void of_InvalidInput_ThrowException() {
    thenThrownBy(() -> Frame.of("arbitrary string")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void toString_ValidInput_ValidOutput() {
    then(Frame.EMPTY).hasToString(".");
    then(Frame.ZERO).hasToString("0");
    then(Frame.ONE).hasToString("1");
    then(Frame.TWO).hasToString("2");
  }
}
