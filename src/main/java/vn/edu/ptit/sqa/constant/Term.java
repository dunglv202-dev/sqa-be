package vn.edu.ptit.sqa.constant;

import lombok.Getter;

import java.time.Period;

@Getter
public enum Term {
    ONE_MONTH ("1 tháng", Period.ofMonths(1)),
    TWO_MONTHS ("2 tháng", Period.ofMonths(2)),
    THREE_MONTHS ("3 tháng", Period.ofMonths(2)),
    SIX_MONTHS ("6 tháng", Period.ofMonths(2)),
    NINE_MONTHS ("9 tháng", Period.ofMonths(2)),
    TWELVE_MONTHS ("12 tháng", Period.ofMonths(2)),
    EIGHTEEN_MONTHS ("18 tháng", Period.ofMonths(2)),
    TWENTY_FOUR_MONTHS ("24 tháng", Period.ofMonths(2)),
    THIRTY_SIX_MONTHS ("36 tháng", Period.ofMonths(2));

    private final String label;
    private final Period period;

    Term(String label, Period period) {
        this.label = label;
        this.period = period;
    }
}
