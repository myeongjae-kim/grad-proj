package bed_from_actg_output.gff.domain;

import bed_from_actg_output.common.service.CsvFileService;
import java.io.IOException;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GffRepositoryTest {
  @Mock
  private CsvFileService csvFileService;

  @BeforeEach
  void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void findAll_ValidInput_ValidOutput() throws IOException {
    // given
    List<List<String>> records = new ArrayList<>();
    List<String> record = new ArrayList<>();
    record.add("chr22");
    record.add("ACTG");
    record.add("exon");
    record.add("22712913");
    record.add("22712966");
    record.add(".");
    record.add("+");
    record.add("0");
    record.add("ID=1; hid=dust; hstart=2419108; hend=2419128");
    records.add(record);
    given(csvFileService.readFileOf(anyString(), anyBoolean())).willReturn(records);

    // when
    GffRepository gffRepository = new GffRepository("./foo/bar", csvFileService);
    List<Gff> gffs = gffRepository.findAll();

    // then
    then(gffs.size()).isEqualTo(1);
    Gff gff = gffs.get(0);
    then(gff.getSeqname()).isEqualTo("chr22");
    then(gff.getSource()).isEqualTo("ACTG");
    then(gff.getFeature()).isEqualTo("exon");
    then(gff.getStart()).isEqualTo(22712913L);
    then(gff.getEnd()).isEqualTo(22712966L);
    then(gff.getScore()).isEqualTo(NaN);
    then(gff.getStrand()).isEqualTo(Strand.FORWARD);
    then(gff.getFrame()).isEqualTo(Frame.ZERO);
    then(gff.getAttribute().get("ID")).isEqualTo("1");
    then(gff.getAttribute().get("hid")).isEqualTo("dust");
    then(gff.getAttribute().get("hstart")).isEqualTo("2419108");
    then(gff.getAttribute().get("hend")).isEqualTo("2419128");
  }
}
