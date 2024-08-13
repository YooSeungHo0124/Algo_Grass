import java.util.*;
public class Solution {
	public static void main(String[] args) {
		
		Scanner s= new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;
		while(test_case>0) {
			int H = s.nextInt();
			int W = s.nextInt();
			char[][] map = new char[H][W];
			
			int start_x=0;
			int start_y=0;
			int state=0;    // state 1:상, 2:하, 3:좌, 4:우    
			for (int r=0; r<H; r++) {
				char[] arr=s.next().toCharArray();
				for (int c=0; c<W; c++) {
					map[r][c] = arr[c];
					
					if (map[r][c]=='<') {
						start_x=r;
						start_y=c;
						state=3;
						map[r][c]='.';
					}
					else if (map[r][c]=='>') {
						start_x=r;
						start_y=c;
						state=4;
						map[r][c]='.';
					}
					else if (map[r][c]=='v') {
						start_x=r;
						start_y=c;
						state=2;
						map[r][c]='.';
					}
					else if (map[r][c]=='^') {
						start_x=r;
						start_y=c;
						state=1;
						map[r][c]='.';
					}
				}
			}
			
			
			
			int N = s.nextInt();
			char[] order = s.next().toCharArray();
			for (int i=0; i<order.length; i++) {
				if (order[i]=='S') { // 포탄 발사
					if (state==3) {     // 좌 
						for (int j=start_y-1;j>=0; j--) {
							if (map[start_x][j]=='#') {break;}  // 강철이면 그냥 끝
							else if (map[start_x][j]=='*') {map[start_x][j]='.'; break;}  // 벽돌이면 . 로 변경
						}
					}
					else if (state==4) {// 우
						for (int j=start_y; j<W; j++) {
							if (map[start_x][j]=='#') {break;}  // 강철이면 그냥 끝
							else if (map[start_x][j]=='*') {map[start_x][j]='.'; break;}  // 벽돌이면 . 로 변경
						}
					}
					else if (state==1) { // 상
						for (int j=start_x-1;j>=0; j--) {
							if (map[j][start_y]=='#') {break;}  
							else if (map[j][start_y]=='*') {map[j][start_y]='.'; break;}  
						}
					}
					else if (state==2) { // 하
						for (int j=start_x;j<H; j++) {
							if (map[j][start_y]=='#') {break;}  
							else if (map[j][start_y]=='*') {map[j][start_y]='.'; break;}  
						}
					}
				}
				else if (order[i]=='U') {
					state=1;
					if (0<=(start_x-1) && (start_x-1)<H && map[start_x-1][start_y]=='.') {
						start_x--;
					}
				}
				else if (order[i]=='D') {
					state=2;
					if (0<=(start_x+1) && (start_x+1)<H && map[start_x+1][start_y]=='.') {
						start_x++;
					}
				}
				else if (order[i]=='L') {
					state=3;
					if (0<=(start_y-1) && (start_y-1)<W && map[start_x][start_y-1]=='.') {
						start_y--;
					}
				}
				else if (order[i]=='R') {
					state=4;
					if (0<=(start_y+1) && (start_y+1)<W && map[start_x][start_y+1]=='.') {
						start_y++;
					}
				}
			}
			
			// 전차 위치에 방향 표시
			if (state==1) {
				map[start_x][start_y]='^';
			}
			else if (state==2) {
				map[start_x][start_y]='v';
			}
			else if (state==3) {
				map[start_x][start_y]='<';
			}
			else if (state==4) {
				map[start_x][start_y]='>';
			}
			
			System.out.print("#"+result_cnt+" ");
			for (int i=0;i<H;i++) {
				for (int j=0;j<W;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			test_case--;
			result_cnt++;
		}
		
	}
}
