import java.util.*;

public class Main {
    static int N, K;
    static boolean[] visit;
    static int[] time;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        K = s.nextInt();

        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        int MAX = 100000;
        visit = new boolean[MAX + 1];
        time = new int[MAX + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        visit[N] = true;
        time[N] = 0;

        while (!deque.isEmpty()) {
            int x = deque.poll();

            if (x == K) {
                System.out.println(time[x]);
                return;
            }

            if (x * 2 <= MAX && !visit[x * 2]) {
                visit[x * 2] = true;
                time[x * 2] = time[x] + 1;
                deque.offer(x * 2);
            }

            if (x + 1 <= MAX && !visit[x + 1]) {
                visit[x + 1] = true;
                time[x + 1] = time[x] + 1;
                deque.offer(x + 1);
            }

            if (x - 1 >= 0 && !visit[x - 1]) {
                visit[x - 1] = true;
                time[x - 1] = time[x] + 1;
                deque.offer(x - 1);
            }
        }
    }
}
