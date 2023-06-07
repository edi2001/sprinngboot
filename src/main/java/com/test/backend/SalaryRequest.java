package com.test.backend;

import java.util.List;

public class SalaryRequest {
    private Employee employee;
    private List<SalaryComponent> komponengaji; // Ubah nama variabel ini

    // Tambahkan getter dan setter untuk kedua variabel di atas

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<SalaryComponent> getKomponengaji() {
        return komponengaji;
    }

    public void setKomponengaji(List<SalaryComponent> komponengaji) {
        this.komponengaji = komponengaji;
    }
}
