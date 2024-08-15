import java.util.*;

public class Main {

    static boolean[] visited; 
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt(); // 컴퓨터의 수
        int edges = s.nextInt(); // 연결된 간선의 수

        visited = new boolean[num + 1];
        graph = new ArrayList[num + 1];

        for (int i = 1; i <= num; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            graph[a].add(b);
            graph[b].add(a); // 무방향 그래프이므로 양쪽에 간선 추가
        }

        dfs(1);
        
        int infected = 0;
        for (int i = 2; i <= num; i++) { // 2번부터 num까지 확인
            if (visited[i]) {
                infected++;
            }
        }
        System.out.println(infected);
    }

    public static void dfs(int x) {
        visited[x] = true;
        for (int i : graph[x]) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
