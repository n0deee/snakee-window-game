package errors;

public class RuntimeExceptionBidimensionalArrayNegativeSize extends RuntimeException {
    public RuntimeExceptionBidimensionalArrayNegativeSize(String message) {
        super(message);
    }

    public RuntimeExceptionBidimensionalArrayNegativeSize() {
        this("Cannot create a bidimensional array with any dimensions having negative size");
    }
}
