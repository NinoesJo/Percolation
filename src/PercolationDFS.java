import java.util.Stack;

public class PercolationDFS extends PercolationDefault {

    public PercolationDFS(int n) {
        super(n);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col) || isFull(row, col) || !isOpen(row, col)) return;

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Stack<int[]> stack = new Stack<>();
        myGrid[row][col] = FULL;
        stack.push(new int[]{row, col});
        while (!stack.isEmpty()) {
            int[] coords = stack.pop();
            for(int k = 0; k < rowDelta.length; k++){
                int nRow = coords[0] + rowDelta[k];
                int nCol = coords[1] + colDelta[k];
                if (inBounds(nRow, nCol) && myGrid[nRow][nCol] == OPEN){
                    stack.push(new int[]{nRow, nCol});
                    myGrid[nRow][nCol] = FULL;
                }
            }
        }
	}

}
