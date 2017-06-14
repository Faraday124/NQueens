import java.util.ArrayList;
import java.util.List;


public class ForwardCheckingSolver {
    private int boardSize;
    private boolean[][] board;
    private long visitedNodes;

    public ForwardCheckingSolver(int boardSize)
    {
        this.boardSize = boardSize;
        board = new boolean[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++)
            for(int j = 0; j < boardSize; j++)
                board[i][j] = true;
    }

    public List<int[]> solve()
    {
        List<int[]> result = new ArrayList<int[]>();
        int[] solution = new int[boardSize];
        solve(solution, 0, board, result);
        return result;
    }

    private int[] solve(int[] queens, int number, boolean[][] board, List<int[]> results)
    {
        if(isSolution(queens, number))
        {
            int[] copy = new int[boardSize];
            System.arraycopy(queens, 0 , copy, 0, boardSize);
            results.add(copy);
            return null;
        }
        else
        {
            for(int i = 0; i < boardSize ; i++)
                if(board[i][number])  
                {
                    visitedNodes++;
                    boolean[][] boardCopy = copyBoard(board);
                    queens[number] = i;
                    removeAvaibles(number, i, boardCopy);
                    number++;
                    if(!checkForEmptyColumns(number, boardCopy))
                    {
                        int[] solution = solve(queens, number, boardCopy, results);
                        if(solution != null)
                         	return solution;
                                                 
                        else
                       	 number--;
                        
                           
                    }
                    else
                        number--;
                }
        }
        return null;
    }



    private boolean isSolution(int[] queens, int number) {
        if (number < boardSize)
            return false;

        boolean correct = true;
        for (int i = 0; i < boardSize && correct; i++)
            for (int j = i + 1; j < boardSize && correct; j++)
                if (queens[i] == queens[j] || Math.abs(i - j) == Math.abs(queens[i] - queens[j]))
                    correct = false;
        return correct;
    }

    private void removeAvaibles(int number, int value, boolean[][] board)
    {
        for(int i = 0; i < boardSize; i++)
            for(int j = number; j < boardSize; j++)
            {
                if (i == value)
                    board[i][j] = false;    // ten sam wiersz
                if (Math.abs(number - j) == Math.abs(value - i))
                    board[i][j] = false;    // przek¹tne
            }
    }

    
     
    private boolean checkForEmptyColumns(int number, boolean[][] boardCopy)
    {
        boolean foundEmpty = false;
        for (int j = number; j < boardSize && !foundEmpty; j++)
        {
            int falseCounter = 0;
            for (int i = 0; i < boardSize; i++)
                if(!boardCopy[i][j])
                    falseCounter++;
            if(falseCounter == boardSize)
                foundEmpty = true;
        }
        return foundEmpty;
    }
    private boolean[][] copyBoard(boolean[][] board)
    {
        boolean[][] result = new boolean[boardSize][boardSize];
        for(int i = 0; i < boardSize ; i++)
            for(int j = 0; j < boardSize; j++)
                result[i][j] = board[i][j];
        return result;
    }

    public long getVisitedNodes()
    {
        return visitedNodes;
    }
}