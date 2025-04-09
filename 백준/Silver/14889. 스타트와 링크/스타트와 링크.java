import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][]map;
    static int minResult = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        boolean[] selected = new boolean[N]; // 팀 선택을 위한 배열
        calc(0, 0, selected); // calc 메서드 호출
        System.out.println(minResult); // 결과 출력
    }


    static void calc(int index, int startCount, boolean[] selected) {
        if (startCount == N / 2) {
            int startTeamScore = 0;
            int linkTeamScore = 0;

            // Calculate scores for both teams
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (selected[i] && selected[j]) {
                        startTeamScore += map[i][j] + map[j][i];
                    } else if (!selected[i] && !selected[j]) {
                        linkTeamScore += map[i][j] + map[j][i];
                    }
                }
            }

            // Update the minimum difference
            int difference = Math.abs(startTeamScore - linkTeamScore);
            minResult = Math.min(minResult, difference);
            return;
        }

        for (int i = index; i < N; i++) {
            selected[i] = true;
            calc(i + 1, startCount + 1, selected);
            selected[i] = false;
        }
    }
}
