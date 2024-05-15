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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pagination pagination)) {
            return false;
        }

        return pagination.page == this.page && pagination.size == this.size;
    }
}
