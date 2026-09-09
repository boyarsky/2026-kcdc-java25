void main() {
    var originalSystemIn = System.in;
    var testStream = new ByteArrayInputStream(new byte[0]);
    System.setIn(testStream);
    IO.readln("in?");
    System.setIn(originalSystemIn);
    IO.readln("in?");
}