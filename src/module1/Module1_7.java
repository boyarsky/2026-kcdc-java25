void main() {
    var originalSystemOut = System.out;
    var testStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testStream));
    IO.print("redirected output");
    System.setOut(originalSystemOut);
    IO.println("real system out");
    IO.println(testStream.toString());
}
