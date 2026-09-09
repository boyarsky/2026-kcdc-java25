class SuperClass {
    SuperClass() {
        IO.println("super before");
        super();
        IO.println("super after");
    }
}

public class Module2_3 extends SuperClass {

    public Module2_3() {
        IO.println("Default before");
        this("second");
        IO.println("Default after");
    }

    public Module2_3(String value) {
        IO.println("String before");
        super();
        IO.println("String after");
    }

    void main() {
        new Module2_3();
    }
}
