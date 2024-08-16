
import java.util.*;

public class Main {
    
    static ArrayList<Integer>[] list;
    static int[] parent;
    static boolean[] visit;
    static int N;
    
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        N = s.nextInt();
        
        list = new ArrayList[N+1];
        parent = new int[N+1];
        visit = new boolean[N+1];
        
        for (int i=1; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i=2; i<=N; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            list[x].add(y);
            list[y].add(x);
        }
        
//        System.out.println(Arrays.toString(list));
        
        // 루트 노드를 1로 가정하고 DFS 시작
        // parent 배열에 포함 안되도록
        dfs(1);
        
        // 2번 노드부터 부모를 출력
        for (int i=2; i<=N; i++) {
            System.out.println(parent[i]);
        }
    }
    
    static void dfs(int node) {
        visit[node] = true;
        
        for (int next : list[node]) {
            if (!visit[next]) {
                parent[next] = node;
//                System.out.println("NEXT : "+ next + "  NODE : "+ node);
                dfs(next);
            }
        }
    }
}
