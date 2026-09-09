void main() {
    var set =  Stream.iterate(1, i -> ++i)
            .limit(100)
            .collect(Collectors.toCollection(LinkedHashSet::new));

    var lists = set.stream()
            .gather(Gatherers.windowFixed(25))
            .toList();
    IO.println(lists.size());
}