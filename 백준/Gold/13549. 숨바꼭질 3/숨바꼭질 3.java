/*
 * 반례 케이스
 * 4 6
 * 
 * 4 -> 3 -> 6 이 나와서 총 1초 걸리는데.
 * 기존 코드는 순간이동부터 우선 순위로 두어서 2초로 판단했다.
 * 
 * sol)
 * 다익스트라에서 distance 비교하듯이 값 비교해서 업데이트 계속 해주었다.
 *  
 * 
 * */
import java.util.*;

public class Main {
    static int N, K;
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
        time = new int[MAX + 1];
        Arrays.fill(time, Integer.MAX_VALUE); // 처음에는 무한대로 초기화

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        time[N] = 0;

        while (!deque.isEmpty()) {
            int x = deque.poll();

            if (x == K) {
                System.out.println(time[x]);
                return;
            }

            // 순간이동 0초
            if (x * 2 <= MAX && time[x * 2] > time[x]) {
                time[x * 2] = time[x];
                deque.offer(x * 2); 
            }

            // 걷기 1초
            if (x + 1 <= MAX && time[x + 1] > time[x] + 1) {
                time[x + 1] = time[x] + 1;
                deque.offer(x + 1);
            }

            if (x - 1 >= 0 && time[x - 1] > time[x] + 1) {
                time[x - 1] = time[x] + 1;
                deque.offer(x - 1); 
            }
        }
    }
}
