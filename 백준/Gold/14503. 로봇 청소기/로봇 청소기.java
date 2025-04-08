import java.util.*;
import

java.io.*;

public class Main {

    static int N,M,r,c,d;
    static int[][] map;

    // 0 : 북 , 1 : 동, 2: 남, 3:서
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    

    public static void main(String[] args) throws Exception {
        BufferedReader br   = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N*M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];


        // 청소기 초기 좌표, 방향향      
        st = new StringTokenizer(br.readLine());
        r =  Integer.parseInt(st.nextToken());
        c =  Integer.parseInt(st.nextToken());
        d =  Integer.parseInt(st.nextToken());


        // map 
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 청소
        int cnt = 0;
        while (true) {
            // 현재 칸 청소
            if (map[r][c] == 0) {
                cnt++;
                map[r][c] = 2; // 청소한 칸 -> 2
            }

            int newX = r + dr[d];
            int newY = c + dc[d];

            // 주변 4칸 중 청소 되지 않은 빈 칸이 없는 경우
            if ((map[r+dr[0]][c+dc[0]]!=0) && (map[r+dr[1]][c+dc[1]]!=0) && (map[r+dr[2]][c+dc[2]]!=0) && (map[r+dr[3]][c+dc[3]]!=0) ) {

                newX = r-dr[d];
                newY = c-dc[d];
                // 벽이 아니라면 ->  방향 유지, 뒤로 후진
                if (map[newX][newY] != 1) {
                    r = newX;
                    c = newY;
                    continue;
                }
                // 벽이라면 작동 중지 
                else {
                    break;
                }
            }

            // 주변 4칸 중 청소 되지 않은 빈 칸이 있는 경우
            else {
                d = (d+3) % 4; // 반시계 회전
                newX = r+dr[d];
                newY = c+dc[d];

                // 앞칸이 청소 안되어있다면 전진
                if (map[newX][newY] == 0) {
                    r = newX;
                    c = newY;
                    continue;
                }
            }
            
        }
        System.out.println(cnt);
        
    }
    
}
