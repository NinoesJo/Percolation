import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault {

    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) return;

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Queue<int[]> queue = new LinkedList<>();
        myGrid[row][col] = FULL;
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] p = queue.remove();
            for(int k = 0; k < rowDelta.length; k++){
                int nRow = p[0] + rowDelta[k];
                int nCol = p[1] + colDelta[k];
                if (inBounds(nRow, nCol) && myGrid[nRow][nCol] == OPEN){
                    queue.add(new int[]{nRow, nCol});
                    myGrid[nRow][nCol] = FULL;
                }
            }
        }
	}

}
