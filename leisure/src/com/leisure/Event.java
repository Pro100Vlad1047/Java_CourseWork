package com.leisure;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Event {
    private String name;
    private Date date;
    private List<Employee> participants;

    public Event(String name, Date date, List<Employee> participants) {
        this.name = name;
        this.date = date;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<Employee> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder participantsStr = new StringBuilder();
        for (Employee participant : participants) {
            participantsStr.append(participant.getName()).append(", ");
        }
        // Удаление последней запятой и пробела
        if (participantsStr.length() > 0) {
            participantsStr.setLength(participantsStr.length() - 2);
        }

        return "\nНазвание: " + name + ",\nДата: " + dateFormat.format(date) + ",\nУчастники: " + participantsStr.toString();
    }
}
