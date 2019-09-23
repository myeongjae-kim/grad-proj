package bed_from_actg_output.bed.service;

import bed_from_actg_output.bed.domain.Bed;
import bed_from_actg_output.flat.domain.Flat;
import bed_from_actg_output.gff.domain.Gff;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BedService {
  public List<Bed> createBedsFrom(Map<String, Flat> flats, List<Gff> gffs) {
    List<Bed> beds = new ArrayList<>();

    gffs.stream()
        .filter(g -> flats.containsKey(g.getAttribute().get("ID")))
        .forEach(g -> beds.add(
            Bed.of(g, flats.get(g.getAttribute().get("ID")))
        ));

    return beds;
  }
}
