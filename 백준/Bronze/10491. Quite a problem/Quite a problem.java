import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            while (true) {
                String str = s.nextLine().toLowerCase();
                System.out.println(str.contains("problem") ? "yes" : "no");
            }
        } catch (NoSuchElementException e) {
            return; 
        }
    }
}