package bed_from_actg_output.gff.domain;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StrandTest {
  @ParameterizedTest
  @ValueSource(strings={".", "+", "-"})
  void of_ValidInput_NoException(String value) {
    Strand.of(value);
  }

  @Test
  void of_InvalidInput_ThrowException() {
    thenThrownBy(() -> Strand.of("arbitrary string")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void toString_ValidInput_ValidOutput() {
    then(Strand.EMPTY).hasToString(".");
    then(Strand.FORWARD).hasToString("+");
    then(Strand.REVERSE).hasToString("-");
  }
}
