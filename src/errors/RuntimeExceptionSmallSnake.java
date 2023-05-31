package errors;

public class RuntimeExceptionSmallSnake extends RuntimeException {
    public RuntimeExceptionSmallSnake(String message) {
        super(message);
    }

    public RuntimeExceptionSmallSnake() {
        this("The snake is too small");
    }
}
