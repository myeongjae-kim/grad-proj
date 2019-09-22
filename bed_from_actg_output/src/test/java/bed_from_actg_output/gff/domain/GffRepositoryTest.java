package bed_from_actg_output.gff.domain;

import bed_from_actg_output.common.service.CsvFileService;
import bed_from_actg_output.gff.domain.Gff;
import bed_from_actg_output.gff.domain.GffRepository;
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
    GffRepository flatRepository = new GffRepository("./foo/bar", csvFileService);
    List<Gff> flats = flatRepository.findAll();

    // then
    Gff flat = flats.get(0);
    then(flat.getSeqname()).isEqualTo("chr22");
    then(flat.getSource()).isEqualTo("ACTG");
    then(flat.getFeature()).isEqualTo("exon");
    then(flat.getStart()).isEqualTo(22712913L);
    then(flat.getEnd()).isEqualTo(22712966L);
    then(flat.getScore()).isEqualTo(NaN);
    then(flat.getStrand()).isEqualTo(Strand.FORWARD);
    then(flat.getFrame()).isEqualTo(Frame.ZERO);
    then(flat.getAttribute().get("ID")).isEqualTo("1");
    then(flat.getAttribute().get("hid")).isEqualTo("dust");
    then(flat.getAttribute().get("hstart")).isEqualTo("2419108");
    then(flat.getAttribute().get("hend")).isEqualTo("2419128");
  }
}
