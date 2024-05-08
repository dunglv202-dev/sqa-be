package vn.edu.ptit.sqa.exception;

public class CustomerNotExistException extends RuntimeException {
    public CustomerNotExistException(Long customerId) {
        super("{customer.not_exist} - " + customerId);
    }
}
