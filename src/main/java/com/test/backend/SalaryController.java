package com.test.backend;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    private final indonesia ind;
    private final vietnam vet;

    public SalaryController(indonesia ind,vietnam vet) {
        this.ind = ind;
        this.vet = vet;
    }

    @PostMapping("/calculate-tax")
    public double calculateTax(@RequestBody SalaryRequest salaryRequest) {
        Employee employee = salaryRequest.getEmployee();
        String  emp = salaryRequest.getCountry();
        double negara=0;
        List<SalaryComponent> salaryComponents = salaryRequest.getKomponengaji();
        System.out.println(ind.calcTotalEarning(salaryComponents));
        if (emp.equalsIgnoreCase("Indonesia")) {
            negara= ind.calcPajak(employee, salaryComponents);
        }
        else if (emp.equalsIgnoreCase("Vietnam")) {
            negara= vet.calcPajakV(employee, salaryComponents);
        }
        return negara;
    
    }
}