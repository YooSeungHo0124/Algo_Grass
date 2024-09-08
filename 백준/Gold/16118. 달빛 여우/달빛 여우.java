import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        float w, speed; 

        Edge(int to, float w, float speed) {
            this.to = to;
            this.w = w;
            this.speed = speed;
        }
    }

    static int N, M;
    static float[] fox;
    static float[][] wolf; // 늑대  [노드][속도], 0: 2배, 1: 1/2배
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        // BufferedReader와 StringTokenizer로 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w, 2)); // 양방향 그래프
            graph[v].add(new Edge(u, w, 2));
        }

        // 여우
        fox = new float[N + 1];
        Arrays.fill(fox, Integer.MAX_VALUE);
        fox[1] = 0;
        dijkstraFox();

        // 늑대
        wolf = new float[N + 1][2]; // 2차원 배열로 늑대 상태 관리
        for (int i = 1; i <= N; i++) {
            Arrays.fill(wolf[i], Integer.MAX_VALUE);
        }
        wolf[1][0] = 0; // 시작점에서 빠른 상태로 시작
        dijkstraWolf();

        // 여우 < 늑대 -> 카운트
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (fox[i] < Math.min(wolf[i][0], wolf[i][1])) {
                count++;
            }
        }

        // 결과 출력
        System.out.println(count);
    }

    // 여우 다익스트라
    static void dijkstraFox() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> Float.compare(x.w, y.w));
        pq.offer(new Edge(1, 0, 1));

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int to = tmp.to;
            float w = tmp.w;

            if (fox[to] < w)
                continue;

            for (Edge nextEdge : graph[to]) {
                int next = nextEdge.to;
                float weight = nextEdge.w;

                if (fox[next] > fox[to] + weight) {
                    fox[next] = fox[to] + weight;
                    pq.offer(new Edge(next, fox[next], 1));
                }
            }
        }
    }

    // 늑대 다익스트라 
    static void dijkstraWolf() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> Float.compare(x.w, y.w));
        pq.offer(new Edge(1, 0, 2)); // 처음에는 빠르게 시작

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int to = tmp.to;
            float w = tmp.w;
            float speed = tmp.speed;

            int currentState = (speed == 2) ? 0 : 1; // 0: 빠름, 1: 느림

            if (wolf[to][currentState] < w)
                continue;

            float nextSpeed = (speed == 2) ? 0.5f : 2;
            int nextState = 1 - currentState; // 0 → 1, 1 → 0

            for (Edge nextEdge : graph[to]) {
                int next = nextEdge.to;
                float weight = nextEdge.w;

                float nextTime = w + weight * (currentState == 0 ? 0.5f : 2); // 빠르면 0.5 곱하고, 느리면 2 곱하고

                if (wolf[next][nextState] > nextTime) {
                    wolf[next][nextState] = nextTime;
                    pq.offer(new Edge(next, nextTime, nextSpeed));
                }
            }
        }
    }
}
