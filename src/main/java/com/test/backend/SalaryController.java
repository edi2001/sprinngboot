package com.test.backend;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    private final TaxCalculationService taxCalculationService;

    public SalaryController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PostMapping("/calculate-tax")
    public double calculateTax(@RequestBody SalaryRequest salaryRequest) {
        Employee employee = salaryRequest.getEmployee();
        List<SalaryComponent> salaryComponents = salaryRequest.getKomponengaji();
        return taxCalculationService.calculatePajak(employee, salaryComponents);
    }
}