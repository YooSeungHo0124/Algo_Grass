import java.util.*;
public class Main {
	static class edge{
		int to,w;
		ArrayList<Integer> nodes;
		public edge(int to, int w, ArrayList<Integer> nodes) {
			this.to = to;
			this.w = w;
			this.nodes = nodes;
		}
		
	}
	
	static int N,M;
	static int[] distance;
	static ArrayList[] distance_node;
	static ArrayList<edge>[] graph;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		distance = new int[N+1];
		distance_node = new ArrayList[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE );
		
		graph = new ArrayList[100000];
		for (int i=0; i<100000; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			int from = s.nextInt();
			int to= s.nextInt();
			int w = s.nextInt();
			ArrayList<Integer> node = new ArrayList<>();
			node.add(from);
			
			graph[from].add(new edge(to,w,node));
		}
		
		int start = s.nextInt();
		int end = s.nextInt();
		
		
		distance[start] = 0;
		PriorityQueue<edge> pq = new PriorityQueue<>((x,y)->(x.w-y.w));
		pq.offer(new edge(start,0, new ArrayList<Integer>()));
		while (!pq.isEmpty()) {
			edge tmp = pq.poll();
			int to = tmp.to;
			int w = tmp.w;
			ArrayList<Integer> node = tmp.nodes;
			
			if (distance[to] < w) {continue;}
			for (edge imsi : graph[to]) {
				int next = imsi.to;
				int weight = imsi.w;
				ArrayList<Integer> no = imsi.nodes;
				no.addAll(node);
				if (distance[next] > distance[to]+weight) {
					distance[next] = distance[to]+weight;
					no.add(next);
					distance_node[next] = no;
					pq.offer(new edge(next, distance[next],no));
				}
			}
		}
		System.out.println(distance[end]);
//		System.out.println(Arrays.toString(distance));
//		for (int i=0; i<distance_node.length; i++) {
//			System.out.println(distance_node[i]);
//		}
		
//		System.out.println(distance_node[end].size()-1);
		
		// 시작 부분 부터
		boolean isStart=false;
		int cnt=0;
		for (int i=0; i<distance_node[end].size(); i++) {
			if ((int) distance_node[end].get(i) == start) { isStart = true;}
			if (!isStart) continue;
			cnt++;
		}
		
		System.out.println(cnt);
		for (int i=cnt-2; i<distance_node[end].size(); i++) {
			System.out.print(distance_node[end].get(i)+" ");
		}
	}
}
