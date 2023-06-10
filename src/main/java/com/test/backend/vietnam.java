package com.test.backend;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class vietnam {
    public double calcnet(Employee emp,List<SalaryComponent> salaryComponents){
        double totalEarnings = calculateTotalEarnings(salaryComponents);
        double totalDeduction = calculateDeduction(salaryComponents);
        double SemuaPenghasilan = totalEarnings - totalDeduction;
        double ptkp = calcPTKP(emp);
        double netIncome =SemuaPenghasilan-ptkp-getAsuransi(salaryComponents);
        return netIncome > 0? netIncome : 0;
    }
    private double calculateTotalEarnings(List<SalaryComponent> salaryComponents) {
        double totalEarnings = 0;
        for (SalaryComponent component : salaryComponents) {
            if ("earning".equals(component.getType())) {
                totalEarnings += component.getAmount();
            }
        }
        return totalEarnings;
    }
    private double calculateDeduction(List <SalaryComponent> salaryComponents){
        double totalDeduction = 0;
        for (SalaryComponent component : salaryComponents) {
            if ("deduction".equals(component.getType())) {
                totalDeduction += component.getAmount();
            }
        }
        return totalDeduction;
    }

    private double getAsuransi(List<SalaryComponent>salaryComponents){
        double asuransi =0;
        for (SalaryComponent component : salaryComponents) {
                    if ("asuransi".equals(component.getName())) {
                        asuransi += component.getAmount();
                    }
                }
                return asuransi;
    }

    private double calcPTKP(Employee emp){
        String status = emp.getMaritalStatus();
        if (status ==null){
            return 0;
        }
        status=status.toLowerCase();
        switch (status) {
            case "single":
                return 15;
            case "married":
                return 30;
            default:
                return 0;
        }
    }
     double calcPajakV(Employee emp,List<SalaryComponent> salaryComponents) {
        double pajak = 0;
        double ptkp = calcPTKP(emp);
        double nettahun = ((calcnet(emp, salaryComponents)*12/1000000)-ptkp-getAsuransi(salaryComponents));
        double range1 = 50*0.025;
        double range2 = (nettahun-50)*0.075;        
        if (nettahun>0&&nettahun<=50){
            pajak = range1/12;
        }else if(nettahun>50){
            pajak = (range2+range1)/12;
        }
        return pajak*1000000;

    }
}
