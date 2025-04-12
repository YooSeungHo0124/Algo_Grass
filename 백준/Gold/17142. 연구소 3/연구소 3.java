import java.util.*;
import java.io.*;

public class Main {
    static int N, M, virusSize;
    static int[][] map;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static int result = Integer.MAX_VALUE;
    static ArrayList<int[]> virusList = new ArrayList<>(); // 바이러스 위치 저장
    static ArrayList<int[]> selected = new ArrayList<>(); // 선택된 조합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new int[]{i,j}); // 바이러스 위치 저장
                }
            }
        }
        virusSize = virusList.size();

        combination(0, 0); // 조합 생성 시작
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // 조합 생성 함수 (start: 시작 인덱스, depth: 선택 개수)
    static void combination(int start, int depth) {
        if (depth == M) {
            spread();
            return;
        }

        for (int i=start; i<virusSize; i++) {
            selected.add(virusList.get(i));
            combination(i+1, depth+1); // 다음 요소 선택
            selected.remove(selected.size()-1); // 백트래킹
        }
    }

    static void spread() {
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][N];
        
        // 초기 활성 바이러스 설정
        for (int[] pos : selected) {
            q.add(new int[]{pos[0], pos[1]});
            visited[pos[0]][pos[1]] = 1;
        }

        int time = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int d=0; d<4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr<0 || nc<0 || nr>=N || nc>=N) continue;
                if (map[nr][nc] == 1 || visited[nr][nc] != 0) continue;
                
                visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
                q.add(new int[]{nr, nc});
                
                // 빈 칸인 경우에만 시간 갱신
                if (map[nr][nc] == 0) { 
                    time = Math.max(time, visited[nr][nc]-1);
                }
            }
        }

        // 모든 빈 칸 확인
        boolean valid = true;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    valid = false;
                    break;
                }
            }
        }
        
        if (valid) result = Math.min(result, time);
    }
}