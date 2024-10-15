import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int memory, cost;

        public Node(int memory, int cost) { // 메모리, 비용
            this.memory = memory;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);  // 비용이 작은 순서대로 정렬
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();  
        int M = s.nextInt();  

        int[] memory = new int[N];  //  메모리
        int[] cost = new int[N];    //  비용

        for (int i = 0; i < N; i++) {
            memory[i] = s.nextInt();
        }
        for (int i = 0; i < N; i++) {
            cost[i] = s.nextInt();
        }

        // 비용의 최대값은 100 * N
        int maxCost = 100 * N;  
        // dp[cost]: 해당 cost로 확보할 수 있는 최대 메모리
        int[] dp = new int[maxCost + 1];  // 비용의 최대값은 100 * N이므로 크기를 그만큼 설정
        Arrays.fill(dp, 0);  // 초기엔 확보한 메모리가 0

        // 우선순위 큐를 사용할 필요 없음, 단순히 dp 배열 업데이트
        // 각 앱을 비활성화하는 경우를 반복
        for (int i = 0; i < N; i++) {
            for (int j = maxCost; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }

        // 최소 비용을 찾아야 하므로, 메모리 확보가 목표 메모리 M 이상이 되는 경우를 찾음
        for (int costUsed = 0; costUsed <= maxCost; costUsed++) {
            if (dp[costUsed] >= M) {
                System.out.println(costUsed);
                return;
            }
        }

    }
}
