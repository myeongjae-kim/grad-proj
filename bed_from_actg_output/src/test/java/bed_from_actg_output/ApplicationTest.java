package bed_from_actg_output;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class ApplicationTest {
  @Test
  void parseArgs_ShortArguments_NotNull() {
   String[] args = {"", "-f", "foo.flat", "-g", "bar.gff"};
   then(Application.parseArgs(args)).isNotNull();
  }

  @Test
  void parseArgs_LongArguments_NotNull() {
   String[] args = {"", "--flat", "foo.flat", "--gff", "bar.gff"};
   then(Application.parseArgs(args)).isNotNull();
  }

  @Test
  void parseArgs_InvalidArgument_Null() {
   String[] args = {""};
   then(Application.parseArgs(args)).isNull();
  }
}
