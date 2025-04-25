import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int a = s.charAt(0) - '0';
        int b = s.charAt(1) - '0';

        s = br.readLine();
        int c = s.charAt(0) - '0';
        int d = s.charAt(1) - '0';

        int first = Math.abs(a-c);
        int second = Math.abs(b-d);

        System.out.println(Math.min(first,second)+" "+Math.max(first,second));

    }

}
