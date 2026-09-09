public class Module2_1 {

    public Module2_1() {
        IO.println("Default before");
        this("second");
        IO.println("Default after");
    }

    public Module2_1(String value) {
        IO.println("String before");
        super();
        IO.println("String after");
    }

    public static void main(String[] args) {
        new Module2_1();
    }
}
