void main() {
    Stream.generate(() -> "[]")
            .limit(9)
            .gather(Gatherers.windowFixed(3))
            .forEach(IO::println);
}