import java.util.List;
import java.util.Scanner;


public class Simulation {

    private static int N ;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Podaj rozmiar szachownicy");
        N = s.nextInt();
        if(N > 15)
        	System.out.println("Nie przesadzaj...");
        else{
    	// BACKTRACKING
    	
        BacktrackingSolver b = new BacktrackingSolver(N);
        long startB = System.nanoTime();
        List<int[]> resultB = b.solve();
        long endB = System.nanoTime();
        long timeB = endB - startB;
        System.out.println("Backtracking Czas wykoniania w mili: " + timeB/1000000. + " i w sekundach: " + timeB/1000000000.);
        System.out.println("Liczba rozwi¹zañ: " + resultB.size());
        System.out.println("Liczba odwiedzonych wêz³ów: " + b.getVisitedNodes());
//        printList(resultB);

      
        ForwardCheckingSolver f = new ForwardCheckingSolver(N);
        long startF = System.nanoTime();
        List<int[]> resultF = f.solve();
        long endF = System.nanoTime();
        long timeF = endF - startF;
        System.out.println("\nForwardChecking Czas wykoniania w mili: " + timeF/1000000. + " i w sekundach: " + timeF/1000000000.);
        System.out.println("Liczba rozwi¹zañ: " + resultF.size());
        System.out.println("Liczba odwiedzonych wêz³ów: " + f.getVisitedNodes());
       // printList(resultF);
        }
    }

    public static void printArray(int[] arr)
    {
        for(int el : arr)
            System.out.print(++el + ", ");
    }

    public static void printList(List<int []> list)
    {
        for(int[] el : list)
        {
            printArray(el);
            System.out.println();
        }
    }
}