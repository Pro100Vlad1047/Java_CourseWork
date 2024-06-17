package com.leisure;

import java.util.ArrayList;
import java.util.List;

public class LeisureSystem {
    private List<Employee> employees;
    private List<Event> events;
    private List<Suggestion> suggestions;
    private List<Equipment> equipmentList;

    public LeisureSystem() {
        employees = new ArrayList<>();
        events = new ArrayList<>();
        suggestions = new ArrayList<>();
        equipmentList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void showEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void showSuggestions() {
        for (Suggestion suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }

    public void showEquipment() {
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }
}
