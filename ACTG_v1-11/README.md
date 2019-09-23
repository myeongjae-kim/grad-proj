## ACTG

1. `hg38.chromFa.tar.gz` 를 풀어서 FASTA 디렉토리에 넣기
2. `samtools/ACTG_toy/gencode.v27.basic.annotation.gtf` 를 GTF 디렉토리에 넣기.
3. `java -Xmx8G -Xss2m -jar ACTG_Construction.jar const_params.xml` 실행
4. `java -Xmx8G -Xss2m -jar ACTG_Mapping.jar mapping_params.xml` 실행
