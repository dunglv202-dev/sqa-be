package vn.edu.ptit.sqa.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super();
    }

    public UnauthenticatedException(Throwable cause) {
        super(cause);
    }
}
