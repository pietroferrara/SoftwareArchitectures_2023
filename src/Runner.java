public class Runner {

    public static void main(String[] args) {
        System.out.println(
                "The result is:"+
                        addStringValues(args[0], args[1])
        );
    }

    public static int addStringValues(String arg0, String arg1) {
        return add(
                Integer.parseInt(arg0),
                Integer.parseInt(arg1)
        );
    }

    public static int add(int a, int b) {
        return a+b;
    }
}
