public class Runner {

    public static void main(String[] args) {
        System.out.println(
                "The result is:"+
                add(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1])
                )
        );
    }

    private static int add(int a, int b) {
        return a+b;
    }
}
