import java.util.*;

public class Main {

	static class Monster implements Comparable<Monster> {
		int x, y, d; // 행, 열, 거리

		public Monster(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Monster o) {
			if (this.d == o.d) { 
				return this.y - o.y; // 왼쪽 2순위
			} 
			return this.d - o.d; // 거리 1순위
		}
	}

	static int N, M, D;
	static int map[][];
	static List<int[]> list;
	static int archer[];
	static int res; // 공격할 수 있는 최대 적 수(정답)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); 
		M = sc.nextInt();
		D = sc.nextInt(); // 궁수의 공격 거리

		map = new int[N][M];
		archer = new int[3]; // 궁수 3명의 공격 위치(열) 저장
		list = new ArrayList<>(); // 몬스터 좌표 저장

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) // 몬스터 좌표
					list.add(new int[] { i, j });
			}
		}

		locateArcher(0, 0); // 궁수 3명 배치(조합)
		System.out.println(res);
	}

	private static void locateArcher(int idx, int start) {
		if (idx == 3) { // 3명 다 배치했으면
			List<int[]> data = copy(list);
			attackMonster(data); // 몬스터 공격하러 가기
			return;
		}
		for (int i = start; i < M; i++) {
			archer[idx] = i;
			locateArcher(idx + 1, i + 1);
		}
	}

	private static List<int[]> copy(List<int[]> list) { // 몬스터 좌표 리스트 복사
		List<int[]> tmp = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			tmp.add(new int[] { cur[0], cur[1] });
		}
		return tmp;
	}

	private static void attackMonster(List<int[]> list) {
		int count = 0; // 공격한 몬스터 수

		while (true) {
			if (list.isEmpty()) // 더이상 적 없으면
				break;
			List<int[]> targets = new ArrayList<>(); // 공격할 적의 좌표

			for (int k = 0; k < 3; k++) { // 각 궁수마다 잡을 몬스터 설정
				PriorityQueue<Monster> pq = new PriorityQueue<>(); // 현재 궁수의 사정거리에 있는 몬스터들 저장(거리 순, 열 순 정렬)

				for (int i = 0; i < list.size(); i++) { 
					int[] cur = list.get(i);
					int d = Math.abs(cur[0] - N) + Math.abs(cur[1] - archer[k]); // 맨하탄 거리 계산
					if (d <= D) 
						pq.add(new Monster(cur[0], cur[1], d));   // 사정거리 안에있으면 큐 삽입
				}

				if (!pq.isEmpty()) { // 잡을 몬스터가 있다면
					Monster target = pq.poll(); 
					boolean flag = false;  // 현재 타겟을 다른 궁수가 잡으려 하는지 여부 / true면 이미 다른 궁수가 잡으려 함
					for (int i = 0; i < targets.size(); i++) {
						int[] cur2 = targets.get(i);
						if (target.x == cur2[0] && target.y == cur2[1]) // 이미 다른 누군가가 잡으려 함
							flag = true;
					}
					if (!flag) {
						targets.add(new int[] { target.x, target.y });  // 새롭게 잡으려는 적이면 타겟에 추가
					}
				}
			}

			// targets 리스트에 있는 애들 전부 제거
			for (int i = 0; i < targets.size(); i++) {
				for (int j = list.size() - 1; j >= 0; j--) {
					if (targets.get(i)[0] == list.get(j)[0] && targets.get(i)[1] == list.get(j)[1]) {
						list.remove(j);
						count++;       // 죽이면 카운트 추가
						break;
					}
				}
			}
			// 남아있는 몬스터들 이동(좌표 벗어나면 삭제)
			for (int i = list.size() - 1; i >= 0; i--) {
				list.get(i)[0] += 1;
				if (list.get(i)[0] == N)
					list.remove(i);
			}
		}
		res = Math.max(res, count);
	}
}
