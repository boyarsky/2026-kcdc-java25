void main() {
    var set =  Stream.iterate(1, i -> ++i)
            .limit(100)
            .collect(Collectors.toCollection(LinkedHashSet::new));

    set.stream()
            .gather(Gatherers.windowSliding(97))
            .forEach(IO::println);

}