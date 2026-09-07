# 2026-kcdc-java25

This repository includes the lab of my KCDC Java 25 workshop along with the lab solutions. The lab assignment is as follows.

Note that you are allowed to use Google or the Java 25 JavaDoc for any of these. You are not expected to have memorized the methods from the presentation. While it is ok to use AI to look stuff up, please don't have it complete the lab exercises for you. The goal is for you to learn.

# Module 1

All the exercises in this module except for #4, #5, and #8 can be done in https://onecompiler.com/java

1. Write a program that uses the least number of characters to print kCDC (not counting whitespace there should be 29 characters)
2. Write a program to wrap it in a class. Add an instance variable named date of type LocalDate and sets it to the current date in a public no-args constructor. Have the main method print the date. This should be no more than 136 characters not counting whitespace.
3. Try making the constructor in #2 private. What happens?
4. Put this class in #2 in a package named pkg. Why do you need an import? 
5. Update #4 to use a module import
6. Write a main method that uses contains the following code. How many ways can you write the import to avoid a fully qualified name.
```
var factory = javax.xml.parsers.SAXParserFactory.newInstance();
IO.print(factory);
```
7. Write a main method that contains the following. What does this tell you about System.out? 
```
var originalSystemOut = System.out;
var testStream = new ByteArrayOutputStream();
System.setOut(new PrintStream(testStream));
IO.print("redirected output");
System.setOut(originalSystemOut);
IO.println("real system out");
IO.println(testStream.toString());
```
8. Can you do the same thing with System.in or System.err? (You can write but not run the code for this if using the online IDE)

Optional: Ask AI the benefits of using these new features. Then ask it for bonus multiple choice review questions.

# Module 2

For all exercises in this module except #2, you can use https://dev.java/playground/. See tips at: https://www.selikoff.net/2025/08/10/using-the-java-playground/

1. Write a class that has two chained constructors. One should call super() and the other this(). Add a println before/after each one to see the flow.
    1. If on your computer, use the original long form public static void
    2.  If on Java playground, do not use a main method at all. Instead put the contents of the main method (aka a constructor call) after the constructor definition.
2. If on a computer, change to instance main. How does the output differ?
3. Create a superclass of the code in #1 and print the prologu/epilogue in the superclass constructor to see the flow.
4. Add an undefined final instance variable to #1 and write it it from the prologue.
5. How many of the variables in this code can you replace with _ (unnamed variable)?. If you are using the Java playground, remember to declare args yourself since there is no wrapper method.
```
void main(String... args) {
    try {
        int num = 0;
        for (var arg : args) {
            IO.print("arg");
        }
        Arrays.stream(args).forEach(arg -> IO.println("arg"));
        String test = "";
        switch (test) {
            case String s -> IO.println("string");
        }
    } catch (NullPointerException e) {
        IO.println("error");
    }
}
```
6. Try adding these to the previous example at the same time. Do they all work?
```
var _ = 1;
var _ = "";
var _ = new Object();
```
7. Try printing out one of the unnamed variables. What happened?

Optional: Ask AI the benefits of using these new features. Then ask it for bonus multiple choice review questions.

# Module 3

For all exercises in this module, you can use https://dev.java/playground/. See tips at: https://www.selikoff.net/2025/08/10/using-the-java-playground/

1. LinkedHashSet defines an encounter order. Given the following code, print the first and last elemnent both with and without the sequenced collection APIs. (Use an iterator for the later). 
```
var set =  Stream.iterate(1, i -> ++i)
  .limit(100)
  .collect(Collectors.toCollection(LinkedHashSet::new));
```
2. Given the set in the previous example, use windowFixed() to output four lists
3. Given the same set, use windowSliding() to store a list of 4 lists and then print the size.
4. Create a stream of 9 elements of "[]" and use to make a tic tac toe board.
5. Create a record called Count with 6 fields; one for each side of a six sided die. Generate a stream of 10 random numbers from 1-6 and use fold to count how many of each you see. Print the record
6. Change #5 to use scan and show the interim results (the only change you should make is changing "fold" to "scan")
7. Optional/advanced (ok to use AI to help with this one): Instead of having 10 rolls, have an infinite number of rolls and have the gatherer stop when there are 10 of any number rolled.

# Module 4

Note that only question 1 can be done in the playground.

1. Create a class for using ScopedValue. It should create a static field ScopedValue<String> named DINNER. Use ScopedValue.where to bind it printing isBound() before/in/after that statement. Also use get() within the statement.
2. Try running this code. How long does it take?
```
 static class Logic {
        static void waitUp() {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

public static void main(String[] args) throws InterruptedException {

        var start = System.nanoTime();

        var platformThreads = Stream.generate(() -> Thread.ofPlatform().unstarted(Logic::waitUp)).limit(1_000).toList();
        platformThreads.forEach(Thread::start);
        for (var t : platformThreads) {
            t.join();
        }
        
        var end = System.nanoTime();
        System.out.printf("Done. %d seconds ", (end - start) / (1_000 * 1_000));
}
```

3. Try increasing the number of threads in the limit to a million. Does it work?
4. Try changing the above example to use a virtual thread. Now can you change the number of threads to a million? How long does it take to run?
5. Now see how many actual platform threads were used to service all these virtual threads:

```
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

    public static void main(String[] args) throws InterruptedException {

        var start = System.nanoTime();

        var virtualThreads = Stream.generate(() -> Thread.ofVirtual().unstarted(Logic::waitUp)).limit(1_000_000).toList();
        virtualThreads.forEach(Thread::start);
        for (var t : virtualThreads) {
            t.join();
        }

        var end = System.nanoTime();
        System.out.printf("Done. %d seconds ", (end - start) / (1_000 * 1_000));
        Logic.workers.forEach(System.out::println);
    }
```

Optional: Ask AI the benefits of using these new features. Then ask it for bonus multiple choice review questions.
