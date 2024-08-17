
import java.util.*;

public class Main {

    static ArrayList<Integer>[] list;
    static boolean[] visit;
    static int start;
    static int end;
    static int result = -1;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt(); // 전체 사람 수
        start = s.nextInt(); 
        end = s.nextInt(); 
        int edge = s.nextInt();

        list = new ArrayList[N+1];
        visit = new boolean[N+1];
        for (int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i=1; i<=edge; i++) {
            int x = s.nextInt();
            int y = s.nextInt();

            list[x].add(y);
            list[y].add(x);
        }

        dfs(start, 0);

        System.out.println(result);
    }

    // DFS에서 depth를 사용하여 촌수를 추적
    static void dfs(int x, int depth) {
        visit[x] = true;

        if (x == end) {
            result = depth; // 경로를 찾았을 때 result에 depth 저장
            return;
        }

        for (int i : list[x]) {
            if (!visit[i]) {
                dfs(i, depth + 1);
            }
        }
    }
}
