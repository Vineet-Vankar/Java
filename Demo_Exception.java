class MyException extends Exception      //create own Exception class
{
    private int details;

    MyException (int d)
    {
        details = d;
    }

    public String toString()
    {
        return "MyException["+details+"]";     //create Exception message
    }
}


public class Demo_Exception {

    static void compute (int a) throws MyException
    {
        System.out.println("Compute is : "+a);

        if (a > 10)     //condition to trigger the exception
        {
            throw new MyException(a);      // throw the exception
        }
        System.out.println("Normal Exit");
    } 
    public static void main (String[] args)
    {
        try
        {
            compute(1);         
            compute(20);     
        }
        catch (MyException e)
        {
            System.out.println("Caught : "+e);       //message of Exception
        }
    }
}

