# Infosys Specialist Programmer - Java Training Module

This repository now contains a Java training module for 4 exam-style problems:

- `training.FoodStamps` (Easy)
- `training.MSSWithSwaps` (Medium)
- `training.LockAndParity` (Hard)
- `training.LayerSplitPathMaximization` (Complex)

All source files are in:
`/tmp/workspace/Manoj2805/Manoj2805/src/main/java/training`

## 1) Food Stamps
**Idea**: maximize the sum of the top marginal meal values across arithmetic progressions.

- Food `i` contributes sequence: `v[i], v[i]-d[i], v[i]-2d[i], ...`
- We can buy **up to** `M` meals, so only positive marginal terms matter.
- Use binary search on value threshold + arithmetic-series sum formulas.

**Complexity**
- Time: `O(N log V)` where `V = max(v[i])`
- Space: `O(1)` extra (excluding input arrays)

## 2) MSS With Swaps
**Idea**: for every subarray `[l..r]`, improve it with up to `k` swaps by:
- swapping smallest inside values out,
- swapping largest outside values in.

Because `a[i]` is in `[-1000, 1000]`, counting arrays are used for fast extraction of candidates.

**Complexity**
- Time: `O(n^2 * (R + k))`, where `R=2001`
- Space: `O(R)` per iteration

## 3) Lock & Parity
**Key proof insight**:
- Every assignment has strictly positive cost.
- Validity requires `even >= odd` and at least one assignment.
- If a solution has any odd edges, removing an odd-even pair always reduces cost and keeps parity valid.
- Therefore, the minimum valid solution is always a **single even-cost assignment** (if one exists).

So we only need the smallest even `|L[j]-L[i]|` over valid pairs `(j<i, L[j]!=L[i])`.

**Complexity**
- Time: `O(N^2)`
- Space: `O(1)` extra

## 4) Layer-Split Path Maximization
**Model used in this module**:
- Build a forward DAG using monotonic order `(layer, nodeId)`.
- Edge direction:
  - lower layer -> higher layer,
  - for equal layer, lower node id -> higher node id.
- Transition score: `+V[next] - (layerJump)^2`.
- DP over DAG order gives best path score; starting at any node is allowed.

**Complexity**
- Time: `O((N + M) log N)` (sorting nodes by layer)
- Space: `O(N + M)`

## Build & Run
From repository root:

```bash
javac -d out $(find src/main/java -name '*.java')
```

Run a problem class (example Food Stamps):

```bash
java -cp out training.FoodStamps
```

## Sample Input/Output Validation
Use the sample cases from the problem statements by piping input to each class.
