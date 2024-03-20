package vn.edu.ptit.sqa.constant;

import lombok.Getter;

import java.time.Period;

@Getter
public enum Term {
    ONE_MONTH ("1 tháng", Period.ofMonths(1)),
    TWO_MONTHS ("2 tháng", Period.ofMonths(2)),
    THREE_MONTHS ("3 tháng", Period.ofMonths(3)),
    SIX_MONTHS ("6 tháng", Period.ofMonths(6)),
    NINE_MONTHS ("9 tháng", Period.ofMonths(9)),
    TWELVE_MONTHS ("12 tháng", Period.ofMonths(12)),
    EIGHTEEN_MONTHS ("18 tháng", Period.ofMonths(18)),
    TWENTY_FOUR_MONTHS ("24 tháng", Period.ofMonths(24)),
    THIRTY_SIX_MONTHS ("36 tháng", Period.ofMonths(36));

    private final String label;
    private final Period period;

    Term(String label, Period period) {
        this.label = label;
        this.period = period;
    }
}
