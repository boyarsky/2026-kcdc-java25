import java.util.Random;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

record Count(int count1, int count2, int count3, int count4, int count5, int count6) {
    static Count getZeroed() {
        return new Count(0,0,0,0,0,0);
    }

    Count increment(int num) {
        return switch (num) {
            case 1 -> new Count(count1+1,count2,count3,count4,count5,count6);
            case 2 -> new Count(count1,count2+1,count3,count4,count5,count6);
            case 3 -> new Count(count1,count2,count3+1,count4,count5,count6);
            case 4 -> new Count(count1,count2,count3,count4+1,count5,count6);
            case 5 -> new Count(count1,count2,count3,count4,count5+1,count6);
            case 6 -> new Count(count1,count2,count3,count4,count5,count6+1);
            default -> throw new IllegalArgumentException("Invalid number");
        };
    }
}

void main() {
    var random = new Random();
    Stream.generate(() -> random.nextInt(6)+1)
            .limit(10)
            .gather(Gatherers.fold(Count::getZeroed, Count::increment))
            .forEach(IO::println);
}