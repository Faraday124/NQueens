import java.util.ArrayList;
import java.util.List;


public class BacktrackingSolver {

    private int N;
    private long visitedNodes;

    public BacktrackingSolver(int boardSize) {
        this.N = boardSize;
    }

    public List<int[]> solve()
    {
        List<int[]> result = new ArrayList<int[]>();
        int[] solution = new int[N];
        solve(solution, 0, result);
        return result;
    }

    private int[] solve(int[] queens, int number, List<int[]> results) {
        if (isSolution(queens, number))
        {
            int[] copy = new int[N];
            System.arraycopy(queens, 0 , copy, 0, N);
            results.add(copy);
            return null;
        }
        else
            for (int i = 0; i < N; i++) {
                {
                    visitedNodes++;
                    if (isSafe(queens, number, i)) {
                        queens[number] = i;
                        number++;
                        int[] solution = solve(queens, number, results);
                        if (solution != null)
                           return solution;
                        else
                            number--;
                }
                }
            }
        return null;
    }

    private boolean isSolution(int[] queens, int number) {
        if (number < N)
            return false;

        boolean correct = true;
        for (int i = 0; i < N && correct; i++)
            for (int j = i + 1; j < N && correct; j++)
                if (queens[i] == queens[j] || Math.abs(i - j) == Math.abs(queens[i] - queens[j]))
                    correct = false;
        return correct;
    }

    private boolean isSafe(int[] queens, int number, int value) {
        queens[number] = value;
        number++;

        boolean correct = true;
        for (int i = 0; i < number && correct; i++)
            for (int j = i + 1; j < number && correct; j++)
                if (queens[i] == queens[j] || Math.abs(i - j) == Math.abs(queens[i] - queens[j]))
                    correct = false;
        return correct;
    }

    public long getVisitedNodes()
    {
        return visitedNodes;
    }
}