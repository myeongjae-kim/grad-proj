package bed_from_actg_output.bed.service;

import bed_from_actg_output.bed.domain.Bed;
import bed_from_actg_output.flat.domain.Flat;
import bed_from_actg_output.flat.domain.FlatRepository;
import bed_from_actg_output.gff.domain.Gff;
import bed_from_actg_output.gff.domain.GffRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BedService {
  private final FlatRepository flatRepository;
  private final GffRepository gffRepository;

  public List<Bed> createBeds() {
    List<Gff> gffs = gffRepository.findAll();
    Map<String, Flat> flats = flatRepository.findAllByAttributeCdsMappedByGffId();

    List<Bed> beds = new ArrayList<>();

    gffs.stream()
        .filter(g -> flats.containsKey(g.getAttribute().get("ID")))
        .forEach(g -> beds.add(
            Bed.of(g, flats.get(g.getAttribute().get("ID")))
        ));

    return beds;
  }
}
