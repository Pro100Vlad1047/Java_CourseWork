package com.leisure;

public class Suggestion {
    private String suggestion;
    private Employee employee;

    public Suggestion(String suggestion, Employee employee) {
        this.suggestion = suggestion;
        this.employee = employee;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return suggestion + " от " + employee.getName();
    }
}
