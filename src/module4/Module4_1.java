static final ScopedValue<String> DINNER = ScopedValue.newInstance();

void main() {
    IO.println(DINNER.isBound());
    ScopedValue.where(DINNER, "BBQ").run(() -> {
        IO.println(DINNER.isBound());
        IO.println(DINNER.get());
    });
    IO.println(DINNER.isBound());
}