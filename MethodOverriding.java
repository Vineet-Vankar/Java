//create superclass
class Dimension {
    double dim1;
    double dim2;
    
    //create constructor
    dimension(double dim) {
        dim1 = dim;
        dim2 = dim;
    }
    
    //constructor overloading
    dimension (double dim1, double dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }
    
    void area() {
        System.out.print("Area of ");
    }
}

//square subclass of dimension superclass
class Square extends Dimension {
 
    square(double side) {
        //super keyword to call superclass constructor
        super(side,side);
    }
    
    void area() {
        super.area();   //calling area method from superclass
        System.out.print("Square = "+(dim1*dim2));
        System.out.println();
    }
    
}

//rectangle subclass of dimension superclass
class Rectangle extends Dimension {

    rectangle(double length, double width) {
        super(length,width);
    }
    
    void area() {
        super.area();   //calling area method from superclass
        System.out.print("Rectangle = "+(dim1*dim2));
        System.out.println();
    }
    
}

//triangle subclass of dimension superclass
class Triangle extends Dimension {

    triangle(double base, double height) {
        super(base,height);
    }
    
    void area() {
        super.area();   //calling area method from superclass
        System.out.print("Triangle = "+(0.5*dim1*dim2));
        System.out.println();
    }
    
}

class MethodOverriding {
    public static void main(String args[]) {
        
        //object of subclass
        Square s = new square(5);
        Rectangle r = new rectangle(4,5);
        Triangle t = new triangle(2,5);
        
        //calling superclass by reference 
        Dimension d;
        d = s;
        d.area();
        d = r;
        d.area();
        d = t;
        d.area();
        
    }
} 
