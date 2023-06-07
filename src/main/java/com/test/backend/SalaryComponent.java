package com.test.backend;

public class SalaryComponent  {
    private String name;
    private String type;
    private double amount;
    public SalaryComponent(String name, String type, double amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
