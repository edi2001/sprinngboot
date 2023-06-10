package com.test.backend;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class indonesia {
    public double calculateIncome(Employee emp, List<SalaryComponent> salaryComponents){
        double totalEarnings = calcTotalEarning(salaryComponents);
        double totalDeduction = calcDeduction(salaryComponents);
        double SemuaPenghasilan = totalEarnings - totalDeduction;
        double ptkp = calcPTKP(emp);
        double netIncome =SemuaPenghasilan-ptkp;
        return netIncome > 0 ? netIncome : 0;
    }

    public double calcTotalEarning(List<SalaryComponent> salaryComponents){
        double totalEarnings = 0;
        for (SalaryComponent component : salaryComponents) {
            if ("earning".equals(component.getType())) {
                totalEarnings += component.getAmount();
            }
        }
        return totalEarnings;
    }

    public double calcDeduction(List<SalaryComponent> salaryComponents){
        double totalDeduction = 0;
        for (SalaryComponent component : salaryComponents) {
            if ("deduction".equals(component.getType())) {
                totalDeduction += component.getAmount();
            }
        }
        return totalDeduction;
    }

    private double calcPTKP(Employee emp){
        String status = emp.getMaritalStatus();
        int childs = emp.getChilds();
        if (status ==null){
            return 0;
        }
        status=status.toLowerCase();
        switch (status){
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
    }
        public double calcPajak(Employee emp,List<SalaryComponent> salaryComponents) {
            double pajak = 0;
            double ptkp = calcPTKP(emp);
            double nettahun = ((calculateIncome(emp, salaryComponents)*12/1000000)-ptkp);
            double range1 = 50*0.05;
            double range2 = (nettahun-50)*0.1;
            double range3 = (nettahun-250)*0.15;
            
            if (nettahun>0&&nettahun<=50){
                pajak = range1/12;
            }else if(nettahun>50&&nettahun<=250){
                pajak = (range2+range1)/12;
            }
            else if(nettahun>250){
                pajak = (range3+range2+range1)/12;
            }
            return pajak*1000000;

        }

}

