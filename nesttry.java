class nesttry {

    static void nest_try (int a) {

        try {
            if (a == 1) {
                a = a / (a-a);
            }
            if (a == 2) {
                int c[] = {1,2};
                c[20] = 54;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index Out of Bound");
            System.out.println("Error : "+e);
        }
    }

    public static void main (String[] args) {

        try {
            int l = args.length;
            double d = 42 / l;
            System.out.println("Length is : "+l);
            nest_try(l);
        }
        catch (ArithmeticException e){
            System.out.println("Error is : "+ e);

        }

        System.out.println("Statement at the End of Program");
    }
}