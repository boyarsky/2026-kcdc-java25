
public class Module2_2 {

    public Module2_2() {
        IO.println("Default before");
        this("second");
        IO.println("Default after");
    }

    public Module2_2(String value) {
        IO.println("String before");
        super();
        IO.println("String after");
    }

    void main() {
        new Module2_2();
    }
}
