**[SAMtools tutorial.](https://drive.google.com/drive/folders/16awMt0GV5m6Gc2KeAC6bbVlkevVAJ8hD?usp=sharing)**

1. toy.sam 을 toy.bam으로 변환.

    samtools view -S -b toy.sam > toy.bam

2. toy.bam 을 sorting.

    samtools sort toy.sam -o toy.sorted.bam

3. sorted toy.bam 을 indexing.

    samtools index toy.sorted.bam

4. sorted toy.bam 에서 ref:1-10사이의 정보를 가져오기.

    samtools view toy.sorted.bam ref:1-10
