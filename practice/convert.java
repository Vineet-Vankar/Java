import java.util.Scanner;

public class convert {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter numer in meters: "); 
         double a = sc.nextDouble();
         System.out.println("Number in feets is: "+(a*3.28084));
         sc.close();
    }
}
