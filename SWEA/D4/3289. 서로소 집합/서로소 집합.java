import java.util.*;
public class Solution {
	static int[] parents;
	static int N,M;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		
		int test_case = s.nextInt(), result_cnt =1;
	
		while (test_case>0) {
			N = s.nextInt(); // 정점수
			M = s.nextInt(); // 연산의 개수
			parents = new int[N+1];
			make(); // 집합만들기
			
			StringBuffer sb = new StringBuffer(); // 출력결과 담기
			for (int i=0; i<M; i++) {
				int op = s.nextInt();
				int x = s.nextInt();
				int y = s.nextInt();
				switch (op) {
					case 0: // 합치는 작업
						union(x,y);
						break;
					case 1: // 두 집합이 같은 작업인지 체크, 두집합 같으면 -> 1, 다르면 -> 0
						if (find(x) == find(y)) {
							sb.append("1");
						}
						else {
							sb.append("0");
						}
				}
			}
			System.out.println("#"+result_cnt+" "+sb.toString());
			test_case--;
			result_cnt++;
		}
	}
	
	static void make() {
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot == yRoot) return false;  // 같은 루트에 있으면
		parents[yRoot] = xRoot;            // 다른 루트에 있으면
		return true;
	}
	
	static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
}
