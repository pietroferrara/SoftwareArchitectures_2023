import org.junit.jupiter.api.Test;

public class Tester {

    @Test
    void test1() {
        assert 3 == Runner.add(1, 2);
    }


    @Test
    void test2() {
        assert 3 == Runner.addStringValues("1", "2");
    }

    @Test
    void test2a() {
        try {
            Runner.addStringValues("true", "2");
        }
        catch(NumberFormatException e) {}
    }

    @Test
    void test3() {
        String[] args = {"1", "2"};
        Runner.main(args);
    }
}
