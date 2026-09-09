import java.time.LocalDate;

public class Module1_2 {
    private LocalDate date;

    public Module1_2() {
        date = LocalDate.now();
    }

    void main() {
        IO.print(date);
    }
}