

import java.util.*;

public class Main {

	static ArrayList<Integer>[] list;
	static boolean[] visit;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int vertex = s.nextInt();
		int edge = s.nextInt();

		list = new ArrayList[vertex + 1];
		visit = new boolean[vertex + 1];

		for (int i = 1; i <= vertex; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < edge; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			list[x].add(y);
			list[y].add(x);
		}

		int cnt = 0;
		for (int i = 1; i < vertex + 1; i++) {
			if (!visit[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	static void dfs(int x) {
		visit[x] = true;

		for (int i = 0; i < list[x].size(); i++) {
			if (!visit[list[x].get(i)]) {
				dfs(list[x].get(i));
			}
		}

	}
}
