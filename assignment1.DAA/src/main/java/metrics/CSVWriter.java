package metrics;

public class CSVWriter {
    public static void printRow(String algo, int n, long time, long comps, int depth) {
        System.out.printf("%-12s %-10d %-12d %-12d %-5d%n",
                algo, n, time, comps, depth);
    }
}