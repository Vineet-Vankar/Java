import java.util.Scanner;

public class bmi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter weight in pounds: "); 
        double w = sc.nextDouble();
        System.out.print("Enter height in inches: "); 
        double h = sc.nextDouble();

        double weight, height;
        weight = w*0.453592;
        height = h*0.0254;
        double BMI = weight / (height*height);
        System.out.println("Your BMI is: "+BMI);
        sc.close();
    }
}
