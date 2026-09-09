public class Module2_4 {

    private final int counter;

    public Module2_4() {
        IO.println("Default before");
        this("second");
        IO.println("Default after");
    }

    public Module2_4(String value) {
        IO.println("String before");
        counter = 1;
        super();
        IO.println("String after");
    }

    public static void main(String[] args) {
        new Module2_4();
    }
}
