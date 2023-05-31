package errors;

public class ExceptionAlreadyInitialized extends Exception {
    public ExceptionAlreadyInitialized(String message) {
        super(message);
    }

    public ExceptionAlreadyInitialized() {
        this("Already initialized");
    }
}
