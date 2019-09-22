package bed_from_actg_output.gff.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

// https://asia.ensembl.org/info/website/upload/gff.html
@Getter
public class Gff {
  private String seqname;
  private String source;
  private String feature;
  private long start;
  private long end;
  private double score;
  private Strand strand;
  private Frame frame;
  private Map<String, String> attribute;

  @Builder
  private Gff(
      @NonNull String seqname,
      @NonNull String source,
      @NonNull String feature,
      long start,
      long end,
      @NonNull String score,
      @NonNull String strand,
      @NonNull String frame,
      @NonNull String attributeSemicolonSeparated
  ) {
    this.seqname = seqname;
    this.source = source;
    this.feature = feature;
    this.start = start;
    this.end = end;
    this.score = score.equals(".") ? Double.NaN : Double.parseDouble(score);
    this.strand = Strand.of(strand);
    this.frame = Frame.of(frame);
    this.attribute = createAttributeMapOf(attributeSemicolonSeparated);
  }

  private Map<String, String> createAttributeMapOf(String attributeSemicolonSeparated) {
    Map<String, String> attributeMap = new HashMap<>();

    if (attributeSemicolonSeparated.equals(".")) {
      return attributeMap;
    }

    String whitespaceRemoved = attributeSemicolonSeparated.replaceAll("\\s+","");
    String[] keyValues = whitespaceRemoved.split(";");

    for (String keyValue : keyValues) {
      String[] split = keyValue.split("=");
      attributeMap.put(split[0], split[1]);
    }

    return attributeMap;
  }
}
