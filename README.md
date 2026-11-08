# Assignment 4. Patient Zero

**Student ID:** 254735  
**Name:** Akberdiyeva Zhibek  
**Group:** SE-2511

## Program Output

The program was executed using my personal data generated from Student ID 254735.

```text
Mutations: 72
Shared core: 933
Marker hits: 5
First hit: 9218
Border length: 4
Shared words: 380
Tests needed: 42
Cheapest testing: 197
Longest chain: 31
```

## My Outbreak Report

The screenshot of the generated outbreak visualization is saved in `output/outbreak.png`.

![outbreak](output/outbreak.png)

## Questions

### Mission 1. Edit Distance

The cell `d[i][j]` represents the minimum number of operations needed to transform the first `i` characters of string A into the first `j` characters of string B. It is calculated using three possible operations: insertion, deletion, and replacement. We choose the minimum cost among these operations.

### Mission 2. Longest Common Subsequence

The longest common subsequence (LCS) finds the longest sequence of characters that appear in both strings in the same order, but they do not have to be next to each other. The longest common substring is different because its characters must be consecutive. The Shared core can be larger than the number of letters at the same positions because LCS can match characters from different positions.

### Mission 3. Prefix Function and KMP

The prefix function shows the length of the longest proper prefix that is also a suffix for each prefix of the pattern. KMP uses this information to avoid checking characters again, so the pointer in the text never moves backwards. My marker has a Border length of 4, which means that its longest proper prefix that is also a suffix contains four characters.

### Mission 4. Rabin-Karp

Rabin-Karp uses hashes to compare text windows efficiently. However, two different strings can have the same hash because of hash collisions, so we must compare their actual characters after the hashes match. If many windows have the same hash, the algorithm becomes slower because more character comparisons are required.

### Mission 5. Dynamic Programming on Tree

For each person, there are two states: `in[v]`, the minimum cost when the person is tested, and `out[v]`, the minimum cost when the person is not tested. If a person is not tested, all their children must be tested. If the person is tested, each child can either be tested or not tested, depending on which choice has the smaller cost. A greedy approach that selects the person with the most connections does not always give the cheapest solution because it does not consider the testing costs and the choices of neighboring people.

## AI Use

I used ChatGPT to understand the assignment requirements, explain the algorithms, review and debug Java code, and help prepare the README report. I reviewed the solutions and used the provided test examples to check the implementation.

## Timing (Mission 7)

The following results show the average execution time of three runs using `java -cp out Bench`.

| Test | Size | Average time (ms) |
|---|---:|---:|
| Naive search | 20,000 | 79.8 |
| Naive search | 40,000 | 129.7 |
| Naive search | 80,000 | 267.2 |
| KMP | 20,000 | 5.3 |
| KMP | 40,000 | 2.0 |
| KMP | 80,000 | 0.6 |
| Edit distance | 500 | 10.0 |
| Edit distance | 1,000 | 20.5 |
| Edit distance | 2,000 | 35.4 |
| Edit distance | 4,000 | 153.3 |

The naive search becomes slower as the text size increases because it repeatedly compares characters. KMP is faster than naive search because it avoids unnecessary comparisons using the prefix function. The KMP measurements are not strictly increasing, which may be caused by JVM warm-up and measurement variation. Edit distance generally takes more time as the input strings become longer because it fills a two-dimensional DP table.
## Complexity

Let `n` and `m` be the lengths of the input strings, `k` be the window length, and `v` be the number of people in the tree.

| Algorithm | Time Complexity | Space Complexity | Assumptions |
|---|---|---|---|
| Edit Distance | O(n × m) | O(n × m) | Two-dimensional DP table |
| LCS | O(n × m) | O(n × m) | Two-dimensional DP table |
| Prefix Function and KMP | O(n + m) | O(n + m) | Prefix array and result array |
| Rabin-Karp (shared words) | Expected O(n + m), worst-case O(n × m × k) | O(m) | Hash collisions may require character comparisons |
| Tree DP | O(v) | O(v) | Tree represented using adjacency lists |

## Before You Submit

-  The four personal files are in the `data` folder.
-  The screenshot `output/outbreak.png` is in the repository.
-  `java tools/Check.java` passes all three examples.
-  The program produces all nine correct output lines.
-  The timing table contains actual benchmark results.
-  The README is complete.
-  At least five meaningful Git commits have been made.
-  The project is pushed to the public `main` branch.
-  The GitHub repository link is submitted in Moodle.