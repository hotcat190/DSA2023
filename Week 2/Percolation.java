import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final byte CLOSE = 0;
    private static final byte OPEN = 1;
    private static final byte BOT = 2;
    private static final byte TOP = BOT << 1;
    private final byte[] states;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private int countOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;

        states = new byte[n*n+2];
        for (int i = 0; i < n*n+2; i++) {
            states[i] = CLOSE;
        }
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    // validate arguments
    private void validateRowCol(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = n * (row-1) + col;
        states[index] = OPEN;
        
        int root = connect(row, col);
        
        countOpenSites++;
        connectToAdjacentOpenSites(row, col);
    }

    private int connect(int row, int col) {
    }

    // connect current site to adjacent open sites
    private void connectToAdjacentOpenSites(int row, int col) {
        validateRowCol(row, col);
        if (!isOpen(row, col)) {
            return;
        }
        int index = n * (row-1) + col;
        int[] adjacentIndex = getAdjacentIndex(row, col);
        for (int i : adjacentIndex) {
            if (states[i] > 0) {
                uf.union(index, i);
            }
        }
        if (row == 1) {
            states[index] = states[index] | TOP;
        }
        else if (row == n) {
            uf.union(index, n*n+1);
        }
    }

    // find valid adjacent sites of a site
    private int[] getAdjacentIndex(int row, int col) {
        validateRowCol(row, col);
        int index = n * (row-1) + col;
        if (index == 1) {
            // top left
            return new int[]{index+1, index+n};
        }
        else if (index == n) {
            // top right
            return new int[]{index-1, index+n};
        }
        else if (row == n && col == 1) {
            // bottom left
            return new int[]{index-n, index+1};
        }
        else if (row == n && col == n) {
            // bottom right
            return new int[]{index-n, index-1};
        }
        else if (row == 1) {
            // top row
            return new int[]{index-1, index+1, index+n};
        }
        else if (col == 1) {
            // left col
            return new int[]{index-n, index+1, index+n};
        }
        else if (row == n) {
            // bottom row
            return new int[]{index-1, index-n, index+1};
        }
        else if (col == n) {
            // right col
            return new int[]{index-n, index-1, index+n};
        }
        else {
            return new int[]{index-1, index+1, index-n, index+n};
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);
        int index = n * (row-1) + col;
        return (states[index] > 0);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        int index = n * (row-1) + col;
        return uf.find(index) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n*n+1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
