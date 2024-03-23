package vn.edu.ptit.sqa.model;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int page;

    @Max(value = 20, message = "{pagination.size.max}")
    private int size = 10;
}
