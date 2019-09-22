package bed_from_actg_output.gff.domain;

import bed_from_actg_output.common.service.CsvFileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GffRepository {
  private List<Gff> gffs;

  public GffRepository(String filePath, CsvFileService csvFileService) throws IOException {
    List<List<String>> records = csvFileService.readFileOf(filePath, false);

    gffs = new ArrayList<>();
    records.forEach(r -> gffs.add(Gff.builder()
        .seqname(r.get(0))
        .source(r.get(1))
        .feature(r.get(2))
        .start(Long.parseLong(r.get(3)))
        .end(Long.parseLong(r.get(4)))
        .score(r.get(5))
        .strand(r.get(6))
        .frame(r.get(7))
        .attributeSemicolonSeparated(r.get(8)).build()
    ));
  }

  public List<Gff> findAll() {
    return gffs;
  }
}
