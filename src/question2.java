import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;

public class question2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt(); 
            
            boolean isStack = true;
            boolean isQueue = true;
            boolean isPQ = true;

            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int type = scanner.nextInt();
                int x = scanner.nextInt();
                if (!isStack && !isQueue && !isPQ) {
                    continue; 
                }

                if (type == 1) {
                    if (isStack) stack.push(x);
                    if (isQueue) queue.add(x);
                    if (isPQ) pq.add(x);
                } else if (type == 2) {

                    if (isStack) {
                        if (stack.isEmpty() || stack.pop() != x) isStack = false;
                    }
                    if (isQueue) {
                        if (queue.isEmpty() || queue.poll() != x) isQueue = false;
                    }
                    if (isPQ) {
                        if (pq.isEmpty() || pq.poll() != x) isPQ = false;
                    }
                }
            }

            int possibleCount = (isStack ? 1 : 0) + (isQueue ? 1 : 0) + (isPQ ? 1 : 0);

            if (possibleCount == 0) {
                System.out.println("tidak ada"); 
            } else if (possibleCount > 1) {
                System.out.println("tidak yakin"); 
            } else if (isStack) {
                System.out.println("stack"); 
            } else if (isQueue) {
                System.out.println("queue"); 
            } else if (isPQ) {
                System.out.println("priority queue"); 
            }
        }
        scanner.close();
    }
}