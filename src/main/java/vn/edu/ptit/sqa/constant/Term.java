package vn.edu.ptit.sqa.constant;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
public enum Term {
    ON_DEMAND(null),
    ONE_MONTH(1),
    TWO_MONTHS(2),
    THREE_MONTHS(3),
    FIVE_MONTHS(5),
    SIX_MONTHS(6),
    TWELVE_MONTHS(12),
    THIRTEEN_MONTHS(13),
    FIFTEEN_MONTHS(15),
    EIGHT_TEEN_MONTHS(18),
    TWENTY_FOUR_MONTHS(24),
    THIRTY_SIX_MONTHS(36);

    private final Integer totalMonth;

    Term(Integer totalMonth) {
        this.totalMonth = totalMonth;
    }
}
