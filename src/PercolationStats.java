import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] x;
    private final int t;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        t = trials;
        x = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row;
                int col;
                do {
                    row = StdRandom.uniformInt(n) + 1;
                    col = StdRandom.uniformInt(n) + 1;
                } while (percolation.isOpen(row, col));
                percolation.open(row, col);
            }
            x[i] = (double) (percolation.numberOfOpenSites())/(double) (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-CONFIDENCE_95*stddev()/Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+CONFIDENCE_95*stddev()/Math.sqrt(t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.printf("stddev                  = " + String.format("%.17f", percolationStats.stddev()) + "\n");
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
