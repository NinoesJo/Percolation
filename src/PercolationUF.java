public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
        myGrid = new boolean[size][size];
    }

    protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (myGrid[row][col] == false) {
            myGrid[row][col] = true;
            myOpenCount++;
            if (row == 0) {
                myFinder.union(row * myGrid.length + col, VTOP);
            }
            if (row == myGrid.length - 1) {
                myFinder.union(row * myGrid.length + col, VBOTTOM);
            }
            if (row > 0 && myGrid[row - 1][col] == true) {
                myFinder.union(row * myGrid.length + col, (row - 1) * myGrid.length + col);
            }
            if (row < myGrid.length - 1 && myGrid[row + 1][col] == true) {
                myFinder.union(row * myGrid.length + col, (row + 1) * myGrid.length + col);
            }
            if (col > 0 && myGrid[row][col - 1] == true) {
                myFinder.union(row * myGrid.length + col, row * myGrid.length + (col - 1));
            }
            if (col < myGrid[0].length - 1 && myGrid[row][col + 1] == true) {
                myFinder.union(row * myGrid.length + col, row * myGrid.length + (col + 1));
            }
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return myFinder.connected(row * myGrid.length + col, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

}
