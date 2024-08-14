import java.util.*;

public class Solution {

	static char[] graph;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = 10;
		int result_cnt = 1;

		while (test_case > 0) {

			int N = s.nextInt();
			graph = new char[N + 1];
			for (int i = 1; i < N + 1; i++) {
				s.nextInt();
				String str = s.nextLine();
				graph[i] = str.charAt(1); // 앞의 알파벳만 저장
			}

			System.out.print("#"+result_cnt+" ");
			inorder(1);
			test_case--;
			result_cnt++;
			System.out.println();
		}
		
	}

	// 중위순회 L -> V -> R
	public static void inorder(int root) {
		if (root >= graph.length || graph[root] == 0)
			return;
		inorder(root * 2);
		System.out.print(graph[root]);
		inorder(root * 2 + 1);
	}

}
