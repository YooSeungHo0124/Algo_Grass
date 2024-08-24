
import java.util.*;

public class Main {

	static int N;
	static ArrayList<Integer>[] node;
	static boolean[] visit = new boolean[N + 1];
	static Set<Integer> result;
	static int start;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		node = new ArrayList[N + 1];
		visit = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			node[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			int input = s.nextInt();
			node[i].add(input);
//			node[input].add(i);
		}
//		System.out.println(Arrays.deepToString(node));
		
		
		result = new HashSet<>();
		
//		탈출 조건 -> dfs 맨처음에 넣었던 값 x 가 다시 돌고 돌아 왔을때
		for (int i=1; i<=N ; i++) {
			Arrays.fill(visit, false);
			start=i;
			dfs(i);
		}
		
		ArrayList<Integer> resultList = new ArrayList<>(result);
		resultList.sort(Comparator.naturalOrder());
		System.out.println(resultList.size());
		for (int i=0; i<resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}
		
	}
	
	static void dfs(int x) {
		for (int i : node[x]) {
			if (!visit[i]) {
				visit[i] =true;
				dfs(i);
				if (start == i) {
					for (int j=1; j<=N; j++) {
						if (visit[j]) {
							result.add(j);
						}
					}
					return;
				}
			}
		}
	}
	
	
}
