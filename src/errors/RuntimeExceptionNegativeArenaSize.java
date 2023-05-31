package errors;

public class RuntimeExceptionNegativeArenaSize extends RuntimeException {
    public RuntimeExceptionNegativeArenaSize(String message) {
        super(message);
    }

    public RuntimeExceptionNegativeArenaSize() {
        this("Arena size cannot be negative");
    }
}
