package vn.edu.ptit.sqa.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResultPage<T> {
    private int totalPages;
    private List<T> items;
}
