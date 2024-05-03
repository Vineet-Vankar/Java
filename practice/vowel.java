import java.util.Scanner;

public class vowel {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter character : "); 
        char ch = sc.next().charAt(0);
       
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            System.out.print("Entered character is vowel"); 
        }
        else {
            System.out.print("Entered character is consonant");
        }
        sc.close();
    }
}
