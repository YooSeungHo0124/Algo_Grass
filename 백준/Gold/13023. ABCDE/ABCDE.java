import java.util.*;
public class Main {
	    static ArrayList<Integer>[] list;
	    static boolean[] visit;
	    static boolean found;

	    public static void main(String[] args) {
	        Scanner s = new Scanner(System.in);
	        int N = s.nextInt();
	        int M = s.nextInt();
	        list = new ArrayList[N];

	        for (int i = 0; i < N; i++) {
	            list[i] = new ArrayList<>();
	        }

	        for (int i = 0; i < M; i++) {
	            int x = s.nextInt();
	            int y = s.nextInt();
	            list[x].add(y);
	            list[y].add(x);
	        }

	        visit = new boolean[N];
	        found = false;

	        for (int i = 0; i < N; i++) {
	            if (!found) {
	                dfs(i, 0);
	            }
	        }

	        if (found) {
	            System.out.println(1);
	        } else {
	            System.out.println(0);
	        }
	    }

	    static void dfs(int node, int depth) {
	        if (depth == 4) {  // 깊이가 4에 도달하면 바로 종료
	            found = true;
	            return;
	        }

	        visit[node] = true;

	        for (int next : list[node]) {
	            if (!visit[next]) {
	                dfs(next, depth + 1);
	                if (found) return;  // 이미 찾았다면 더 이상 탐색할 필요가 없음
	            }
	        }

	        visit[node] = false;   
	        // dfs 선언할떄 메모리 낭비하면서 새로 초기화하거나 새로 만드는 것보다
	        // 들어왔던 node를 나갈때 false 해줌으로써 메모리나 성능에서 더 우수한 결과를 얻을 수 있다.
	    }
	}
