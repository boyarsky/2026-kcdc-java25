void main() {
    var set =  Stream.iterate(1, i -> ++i)
            .limit(100)
            .collect(Collectors.toCollection(LinkedHashSet::new));

    IO.println(set.iterator().next());

    var iterator = set.iterator();
    Integer last = null;
    while (iterator.hasNext()) {
        last = iterator.next();
    }

    IO.println(last);

    IO.println(set.getFirst());
    IO.println(set.getLast());
}