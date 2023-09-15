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
    private boolean doesPercolates;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;

        states = new byte[n*n];
        for (int i = 0; i < n*n; i++) {
            states[i] = CLOSE;
        }
        uf = new WeightedQuickUnionUF(n*n);
    }

    // validate arguments
    private void validateRowCol(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // return one-dimensional index from two-dimensional index
    private int flatIndex(int row, int col) {
        return n * (row - 1) + col - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = flatIndex(row, col);
        states[index] |= OPEN;

        if (row == 1) {
            states[index] |= TOP;
        }
        if (row == n) {
            states[index] |= BOT;
        }
        
        countOpenSites++;
        connect(row, col);
    }

    // connect current site to grid
    private void connect(int row, int col) {
        if (!isOpen(row, col)) {
            return;
        }
        int index = flatIndex(row, col);
        int[] adjacentIndex = getAdjacentIndex(row, col);
        for (int i : adjacentIndex) {
            if (states[i] != CLOSE) {
                connectToAdjacentOpenSite(index, i);
            }
        }

        if ((states[index] & TOP) != 0 && (states[index] & BOT) != 0) {
            doesPercolates = true;
        }
    }

    // connect current site to an open adjacent site
    private void connectToAdjacentOpenSite(int newSite, int adjacentSite) {
        int root = uf.find(adjacentSite);
        states[newSite] |= states[root];
        uf.union(newSite, root);

        int newRoot = uf.find(newSite);
        states[newRoot] |= states[newSite];
    }

    // find valid adjacent sites of a site
    private int[] getAdjacentIndex(int row, int col) {
        int index = flatIndex(row, col);
        if (row == 1 && col == 1) {
            // top left
            return new int[]{index+1, index+n};
        }
        else if (row == 1 && col == n) {
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
        int index = flatIndex(row, col);
        return (states[index] != CLOSE);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        int index = flatIndex(row, col);
        return (states[uf.find(index)] & TOP) != 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return doesPercolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(5, 5);
    }
}
