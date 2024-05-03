import java.util.Arrays;
import java.util.Scanner;

public class order {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Enter Numbers"); 
        System.out.print("A : "); 
        int a = sc.nextInt();
        System.out.print("B : "); 
        int b = sc.nextInt();
        System.out.print("C : "); 
        int c = sc.nextInt();
       
        int[] temp = {a,b,c};
        Arrays.sort(temp);
        System.out.print(temp[0]+" "+temp[1]+" "+temp[2]);
        sc.close();
    }
}
