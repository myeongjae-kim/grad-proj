## SAMtools tutorial.

1. toy.sam 을 toy.bam으로 변환.

```bash
samtools view -S -b toy.sam > toy.bam
```

2. toy.bam 을 sorting.

```bash
samtools sort toy.sam -o toy.sorted.bam
```

3. sorted toy.bam 을 indexing.

```bash
samtools index toy.sorted.bam
```

4. sorted toy.bam 에서 ref:1-10사이의 정보를 가져오기.

```bash
samtools view toy.sorted.bam ref:1-10
```
