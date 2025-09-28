package metrics;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int depth = 0;
    private int maxDepth = 0;

    public void incComparisons() { comparisons++; }
    public void incAllocations() { allocations++; }
    public void enterRecursion() { depth++; maxDepth = Math.max(maxDepth, depth); }
    public void exitRecursion() { depth--; }

    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }
}
