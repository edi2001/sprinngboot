package com.test.backend;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class TaxCalculationService {
    public double calculateNetIncomeindonesia(Employee employee, List<SalaryComponent> salaryComponents) {
        double totalEarnings = calculateTotalEarnings(salaryComponents);
        double totalDeduction = calculateDeduction(salaryComponents);
        double SemuaPenghasilan = totalEarnings - totalDeduction;
        double ptkp = calculatePTKP(employee);
        double netIncome =SemuaPenghasilan-ptkp;
        return netIncome > 0 ? netIncome : 0;
    }

    public double calculateNetIncomevietnam(Employee employee, List<SalaryComponent> salaryComponents) {
        double totalEarnings = calculateTotalEarnings(salaryComponents);
        double totalDeduction = calculateDeduction(salaryComponents);
        double SemuaPenghasilan = totalEarnings - totalDeduction;
        double ptkp = calculatePTKP(employee);
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

    private double calculatePTKP(Employee employee) {
        String maritalStatus = employee.getMaritalStatus();
        String country = employee.getCountry();
        int childs = employee.getChilds();

        if (maritalStatus == null) {
            return 0;
        }

        maritalStatus = maritalStatus.toLowerCase();

        if (country.equals("indonesia")) {
            switch (maritalStatus) {
                case "single":
                    return 25;
                case "married":
                    if (childs == 0) {
                        return 50;
                    } else {
                        return 75;
                    }
                default:
                    return 0;
            }
        } else if (country.equals("vietnam")) {
            switch (maritalStatus) {
                case "single":
                    return 15;
                case "married":
                    return 30;
                default:
                    return 0;
            }
        }

        return 0;
    }

    public double calculatePajak(Employee employee, List<SalaryComponent> salaryComponents) {
        double pajak=0;
        String country = employee.getCountry();
        double ptkp = calculatePTKP(employee);
        double netTahunIndo = ((calculateNetIncomeindonesia(employee, salaryComponents) * 12) / 1000000) - ptkp;
        double netTahunV = ((calculateNetIncomevietnam(employee, salaryComponents)*12+getAsuransi(salaryComponents)*12)/1000000)-ptkp;
        double range_1_indo = 50*0.05;
        double range_2_indo = (netTahunIndo-50)*0.1;
        double range_3_indo = (netTahunIndo-250)*0.15;
        double range_1_v  = 50*0.025;
        double range_2_v  = (netTahunV-50)*0.075;

        if (country.equals("indonesia")){
            if(netTahunIndo>0&&netTahunIndo<=50){
                pajak =range_1_indo/12; 
            }
            else if(netTahunIndo>50&&netTahunIndo<=250){
                pajak =(range_2_indo+range_1_indo)/12;
            }
            else if(netTahunIndo>250){
                pajak=(range_1_indo+range_2_indo+range_3_indo)/12;
            }
        }else if(country.equalsIgnoreCase("vietnam")){
            if(netTahunV>0&&netTahunV<=50){
                pajak = range_1_v/12;
            }
            else if(netTahunV>50){
                pajak = (range_1_v+range_2_v)/12;
            }
        }

        
        return pajak*1000000;
    }
    
}
