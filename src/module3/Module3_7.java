
import java.util.Random;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

record Count(int count1, int count2, int count3, int count4, int count5, int count6) {
}

private Map<Integer, Integer> createCountMap() {
    var map = new HashMap<Integer, Integer>();
    for (int i = 1; i <= 6; i++) {
        map.put(i, 0);
    }
    return map;
}

void main() {
    var random = new Random();
    Supplier<Map<Integer, Integer>> initializer = this::createCountMap;

    Gatherer.Integrator<Map<Integer, Integer>, Integer, Count> integrator =
            (counts, roll, downstream) -> {
        counts.put(roll, counts.get(roll) + 1);
        return counts.values().stream().noneMatch(count -> count >= 10);
    };

    BiConsumer<Map<Integer, Integer>, Gatherer.Downstream<? super Count>> finisher =
            (map, downstream) ->
                    downstream.push(new Count(map.get(1), map.get(2), map.get(3), map.get(4), map.get(5), map.get(6)));

    var gatherer = Gatherer.ofSequential(initializer, integrator, finisher);
    Stream.generate(() -> random.nextInt(6)+1)
            .gather(gatherer)
            .forEach(IO::println);

}