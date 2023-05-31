package errors;

public class RuntimeExceptionNotInitialized extends RuntimeException {
    public RuntimeExceptionNotInitialized(String message) {
        super(message);
    }

    public RuntimeExceptionNotInitialized() {
        this("This have been not initalized.");
    }
}
