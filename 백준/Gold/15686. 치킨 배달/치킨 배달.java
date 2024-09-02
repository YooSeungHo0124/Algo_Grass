import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> house, chicken;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        M = s.nextInt();
        map = new int[N][N];
        house = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int input = s.nextInt();
                map[i][j] = input;
                if (input == 1) {
                    house.add(new int[]{i, j});  
                } else if (input == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        comb(0, new int[M], 0);
        System.out.println(result);
    }

    static void comb(int idx, int[] selected, int start) {
        if (idx == M) {
            // 선택된 치킨집에 대해 치킨 거리 계산
            int dist = 0;
            for (int[] house : house) {
                int min = Integer.MAX_VALUE;
                for (int i : selected) {
                    int[] chickenShop = chicken.get(i);
                    int distance = Math.abs(house[0] - chickenShop[0]) + Math.abs(house[1] - chickenShop[1]);
                    min = Math.min(min, distance);
                }
                dist += min;
            }
            result = Math.min(result, dist);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            selected[idx] = i;
            comb(idx + 1, selected, i + 1);
        }
    }
}
