void main(String... args) {
    try {
        int _ = 0;
        for (var _ : args) {
            IO.print("arg");
        }
        Arrays.stream(args).forEach(_ -> IO.println("arg"));
        String test = "";
        switch (test) {
            case String _ -> IO.println("string");
        }
    } catch (NullPointerException _) {
        IO.println("error");
    }
}