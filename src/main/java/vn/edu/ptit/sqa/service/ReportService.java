package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.model.GeneralReport;

import java.time.LocalDate;

public interface ReportService {
    GeneralReport generateGeneralReport(LocalDate from, LocalDate to);
}
