package bed_from_actg_output.flat.domain;

import bed_from_actg_output.common.service.CsvFileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatRepository {
  private List<Flat> flats;
  private Map<String, Flat> flatsByAttributeCdsMappedByGffId;

  public FlatRepository(String filePath, CsvFileService csvFileService) throws IOException {
    List<List<String>> records = csvFileService.readFileOf(filePath, true);

    flats = new ArrayList<>();
    records.forEach(r -> flats.add(Flat.builder()
        .gffId(r.get(0))
        .peptide(r.get(1))
        .geneId(r.get(2))
        .attribute(r.get(3)).build()
    ));

    flatsByAttributeCdsMappedByGffId = new HashMap<>();

    flats.stream()
        .filter(f -> f.getAttribute().equals("CDS"))
        .forEach(f -> flatsByAttributeCdsMappedByGffId.put(f.getGffId(), f));
  }

  public List<Flat> findAll() {
    return flats;
  }

  public Map<String, Flat> findAllByAttributeCdsMappedByGffId() {
    return flatsByAttributeCdsMappedByGffId;
  }
}
