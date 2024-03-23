package vn.edu.ptit.sqa.constant;

import lombok.Getter;

@Getter
public enum Term {
    ONE_MONTH(1),
    TWO_MONTHS(2),
    THREE_MONTHS(3),
    SIX_MONTHS(6),
    TWELVE_MONTHS(12),
    EIGHT_TEEN_MONTHS(18),
    TWENTY_FOUR_MONTHS(24),
    THIRTY_SIX_MONTHS(36);

    private final int totalMonth;

    Term(int totalMonth) {
        this.totalMonth = totalMonth;
    }
}
