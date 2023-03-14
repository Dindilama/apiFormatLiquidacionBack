package co.com.ias.apiFormatLiquidacionBack.domain.model.employee;

import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;

public class Employee {

    private final Long idEmployee;
    private final Document document;
    private final Job job;
    private Salary salary;
    private String status;
    private final Name name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Job getJob() {
        return job;
    }

    private final StartDate startDate;

    public Document getDocument() {
        return document;
    }

    public Name getName() {
        return name;
    }


    public StartDate getStartDate() {
        return startDate;
    }

    public Employee(Long idEmployee, Document document, Job job, Name name, StartDate startDate, Salary salary, String status) {
        this.idEmployee = idEmployee;
        this.document = document;
        this.job = job;
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.status = status;
    }

    public Employee(Long idEmployee, Document document, Job job, Name name, StartDate startDate, String status) {
        this.idEmployee = idEmployee;
        this.document = document;
        this.job = job;
        this.name = name;
        this.startDate = startDate;
        this.status = status;
    }
}
