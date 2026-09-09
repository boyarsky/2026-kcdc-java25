

static class Logic {
    private static Set<String> workers = new TreeSet<>();

    static void waitUp() {
        String name = Thread.currentThread().toString();
        workers.add(name.replaceFirst("^.*/", ""));
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

void main() throws InterruptedException {

    var start = System.nanoTime();

    // a little over a second
    var platformThreads = Stream.generate(() -> Thread.ofPlatform().unstarted(Logic::waitUp)).limit(1_000).toList();
    platformThreads.forEach(Thread::start);
    for (var t : platformThreads) {
        t.join();
    }

    // still a little over a second
        /*var virtualThreads = Stream.generate(() -> Thread.ofVirtual().unstarted(Logic::waitUp)).limit(1_000_000).toList();
        virtualThreads.forEach(Thread::start);
        for (var t : virtualThreads) {
            t.join();
        }*/

    // about 5-10 seconds (that's the overhead for a million threads)
        /*var virtualThreads = Stream.generate(() -> Thread.ofVirtual().unstarted(Logic::waitUp)).limit(1_000_000).toList();
        virtualThreads.forEach(Thread::start);
        for (var t : virtualThreads) {
            t.join();
        }*/

    var end = System.nanoTime();
    System.out.printf("Done. %d seconds ", (end - start) / (1_000 * 1_000));
    // 8 worker threads on my machine
    Logic.workers.forEach(IO::println);
}
