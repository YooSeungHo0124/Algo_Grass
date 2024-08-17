import java.util.*;
public class Main {
	
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static int cnt=0;
	static int max=0;
	
	static Queue<Integer> q = new LinkedList<>(); // Queue
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int M = s.nextInt();
		list = new ArrayList[N+1];
		visit = new boolean[N+1]; // 다른 노드에서 시작할 때마다 초기화
		
		for (int i=1; i<=N; i++) {
			list[i]=new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			list[y].add(x);
		}
//		System.out.println(Arrays.toString(list));
		
		ArrayList<Integer> result = new ArrayList<>();
		result.add(0); // 제일 앞칸 채우기
		for (int i=1; i<=N; i++) {
			bfs(i);
			result.add(cnt);
			max=Math.max(max, cnt);
			
//			초기화
			cnt=0;
			visit = new boolean[N+1];
		}
		
		// max값을 가진 idx가 여러개인 경우가 있어서
		for (int i=1; i<=N; i++) {
			if (max == result.get(i)) {
				System.out.print(i+" ");
			}
		}
	}
	
	static void bfs(int x) {
		q.offer(x);
		visit[x]=true;
		
		while(!q.isEmpty()) {
			int poll = q.poll();
			for (int i: list[poll]) {
				if (!visit[i]) {
					q.offer(i);
					cnt++;
					visit[i]=true;
				}
			}
		}
		

	}
}
