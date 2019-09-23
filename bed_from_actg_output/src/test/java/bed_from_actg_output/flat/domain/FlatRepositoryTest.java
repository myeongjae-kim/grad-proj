package bed_from_actg_output.flat.domain;

import bed_from_actg_output.common.service.CsvFileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FlatRepositoryTest {
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
    record.add("1");
    record.add("YVLTQPPSVSVAPGQTAR");
    record.add("ENSG00000211662.2");
    record.add("CDS");
    records.add(record);
    given(csvFileService.readFileOf(anyString(), anyBoolean())).willReturn(records);

    // when
    FlatRepository flatRepository = new FlatRepository("./foo/bar", csvFileService);
    List<Flat> flats = flatRepository.findAll();

    // then
    then(flats.size()).isEqualTo(1);
    Flat flat = flats.get(0);
    then(flat.getGffId()).isEqualTo("1");
    then(flat.getPeptide()).isEqualTo("YVLTQPPSVSVAPGQTAR");
    then(flat.getGeneId()).isEqualTo("ENSG00000211662.2");
    then(flat.getAttribute()).isEqualTo("CDS");
  }

  @Test
  void findAllByAttributeCdsMappedByGffId_ValidInput_ValidOutput() throws IOException {
    // given
    List<List<String>> records = new ArrayList<>();
    List<String> record = new ArrayList<>();
    record.add("1");
    record.add("YVLTQPPSVSVAPGQTAR");
    record.add("ENSG00000211662.2");
    record.add("CDS");
    records.add(record);
    given(csvFileService.readFileOf(anyString(), anyBoolean())).willReturn(records);

    // when
    FlatRepository flatRepository = new FlatRepository("./foo/bar", csvFileService);
    Map<String, Flat> flats = flatRepository.findAllByAttributeCdsMappedByGffId();

    // then
    then(flats.size()).isEqualTo(1);
    Flat flat = flats.get("1");
    then(flat.getGffId()).isEqualTo("1");
    then(flat.getPeptide()).isEqualTo("YVLTQPPSVSVAPGQTAR");
    then(flat.getGeneId()).isEqualTo("ENSG00000211662.2");
    then(flat.getAttribute()).isEqualTo("CDS");
  }
}
