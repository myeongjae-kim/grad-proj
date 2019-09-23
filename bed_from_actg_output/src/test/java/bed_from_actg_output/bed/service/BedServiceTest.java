package bed_from_actg_output.bed.service;

import bed_from_actg_output.bed.domain.Bed;
import bed_from_actg_output.flat.domain.Flat;
import bed_from_actg_output.flat.domain.FlatRepository;
import bed_from_actg_output.gff.domain.Gff;
import bed_from_actg_output.gff.domain.GffRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BedServiceTest {
  private @Mock FlatRepository flatRepository;
  private @Mock GffRepository gffRepository;
  private BedService bedService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    bedService = new BedService(flatRepository, gffRepository);
  }

  @Test
  void createdBeds_ValidInput_ValidOutput() {
    // given
    Flat flat = Flat.builder()
        .gffId("1")
        .peptide("YVLTQPPSVSVAPGQTAR")
        .geneId("ENSG00000211662.2")
        .attribute("CDS").build();
    Gff gff = Gff.builder()
        .seqname("chr22")
        .source("ACTG")
        .feature("exon")
        .start(22712913L)
        .end(22712966L)
        .score("1.0")
        .strand("+")
        .frame("0")
        .attributeSemicolonSeparated("ID=1").build();

    Map<String, Flat> flatMap = new HashMap<>();
    flatMap.put(flat.getGffId(), flat);

    given(flatRepository.findAllByAttributeCdsMappedByGffId()).willReturn(flatMap);
    given(gffRepository.findAll()).willReturn(Collections.singletonList(gff));

    // when
    List<Bed> beds = bedService.createBeds();

    // then
    then(beds).hasSize(1);
    then(beds.get(0)).hasToString("chr22\t22712912\t22712966\tYVLTQPPSVSVAPGQTAR_ENSG00000211662.2_+");
  }
}
