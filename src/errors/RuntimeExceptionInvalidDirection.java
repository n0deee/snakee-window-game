package errors;

public class RuntimeExceptionInvalidDirection extends RuntimeException {
    public RuntimeExceptionInvalidDirection(String message) {
        super(message);
    }

    public RuntimeExceptionInvalidDirection() {
        this("Invalid Direction");
    }
}
