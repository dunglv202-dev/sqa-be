package vn.edu.ptit.sqa.exception;

public class LoanNotExistException extends ClientVisibleException {
    public LoanNotExistException() {
        super("{loan.not_exist}");
    }
}
