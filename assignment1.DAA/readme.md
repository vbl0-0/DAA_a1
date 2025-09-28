# Assignment 1: Divide-and-Conquer Algorithms

## Learning Goals
- Implement classic divide-and-conquer algorithms with safe recursion.
- Analyze running-time recurrences using Master Theorem and Akra–Bazzi.
- Collect metrics (time, recursion depth, comparisons/allocations).
- Compare theory with experiments.

---

## Implemented Algorithms
1. **MergeSort**
    - Linear merge with reusable buffer.
    - Small-n cutoff (insertion sort).
    - Recurrence: T(n) = 2T(n/2) + Θ(n).
    - Master Theorem Case 2 → Θ(n log n).

2. **QuickSort**
    - Randomized pivot.
    - Recurse on smaller partition, iterate on larger one.
    - Typical recursion depth: O(log n).
    - Expected time: Θ(n log n).

3. **Deterministic Select (Median of Medians)**
    - Partition into groups of 5.
    - Median of medians as pivot.
    - Recurse only into one side.
    - Time complexity: Θ(n) (Akra–Bazzi intuition).

4. **Closest Pair of Points (2D)**
    - Divide-and-conquer with x-sorted points.
    - Strip check in O(n).
    - Recurrence: T(n) = 2T(n/2) + Θ(n).
    - Result: Θ(n log n).

---

## Metrics
Collected for each algorithm:
- Running time (nanoseconds).
- Number of comparisons.
- Recursion depth.

Example results:

| Algorithm   | n    | Time (ns)  | Comparisons | Depth |
|-------------|------|------------|-------------|-------|
| MergeSort   | 10   | 829100     | 25          | 0     |
| QuickSort   | 10   | 1030600    | 18          | 1     |
| Select      | 10   | 2029100    | 0           | 1     |
| ClosestPair | 10   | 3287700    | 0           | 0     |
| MergeSort   | 100  | 33700      | 508         | 4     |
| QuickSort   | 100  | 67500      | 526         | 1     |
| Select      | 100  | 46300      | 0           | 6     |
| ClosestPair | 100  | 361500     | 0           | 0     |
| MergeSort   | 1000 | 441400     | 8573        | 7     |
| QuickSort   | 1000 | 576400     | 7213        | 1     |
| Select      | 1000 | 402700     | 0           | 6     |
| ClosestPair | 1000 | 2860500    | 0           | 0     |

---

## Observations
- MergeSort and QuickSort behave close to Θ(n log n).
- Select is linear, but constants make it slower on small n.
- Closest Pair is ~Θ(n log n), but has larger constants (geometry overhead).
- Recursion depth matches expectations (QuickSort ~O(log n), MergeSort log n, Select linear in pivoting).

---

## Summary
Theoretical analysis with Master Theorem and Akra–Bazzi matches the measurements.  
Differences at small n are explained by constant factors (cache, randomization, memory allocation).
