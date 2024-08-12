import java.util.*;
public class Solution {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int test_case= s.nextInt();
		int result_cnt =1;
		while(test_case>0) {
			
			String str = s.next();
			char[] c= str.toCharArray();
			
			int[] space_cnt=new int[c.length];
			for (int i =0; i<c.length; i++) {
				char[] clone = c.clone();
				String strClone="";
				for (int k=0; k<clone.length; k++) {
					strClone +=clone[k];
				}
				// i번째 글자들을 스페이스로 변경
				strClone = strClone.replace(Character.toString(clone[i]), " ");
				
				// 두번째 스페이스 idx - 첫번째 스페이스 idx -> space_cnt 배열에 넣기 
				int first=0;
				boolean count=false;
				for (int j=0; j<clone.length; j++) {
					if (count==true && strClone.charAt(j)==' ') {space_cnt[i]=j-first;break;}
					else if(strClone.charAt(j)==' ') {count=true; first=j;}
				}
			}
			
//			System.out.println(Arrays.toString(space_cnt));
			
			int max=Integer.MIN_VALUE;
			for (int i=0; i<space_cnt.length; i++) {
				if (space_cnt[i]>max) {
					max = space_cnt[i];
				}
			}
			System.out.println("#"+result_cnt+" "+max);
			

			
			test_case--;
			result_cnt++;
		}
	}
}
