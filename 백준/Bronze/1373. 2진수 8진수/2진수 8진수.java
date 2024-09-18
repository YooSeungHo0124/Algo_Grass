import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String binaryString = s.nextLine(); 

        StringBuilder octalString = new StringBuilder();

        int length = binaryString.length();
        
        int padding = (3 - (length % 3)) % 3;
        binaryString = "0".repeat(padding) + binaryString;

        // 3자리씩 나누어 변환
        for (int i = 0; i < binaryString.length(); i += 3) {
            String threeBits = binaryString.substring(i, i + 3);
            int octalDigit = Integer.parseInt(threeBits, 2); 
            octalString.append(octalDigit); 
        }

        System.out.println(octalString); 
    }
}
