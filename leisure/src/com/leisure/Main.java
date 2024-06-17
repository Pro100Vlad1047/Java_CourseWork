package com.leisure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static LeisureSystem system = new LeisureSystem();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Система организации досуга и отдыха персонала");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton addEmployeeButton = new JButton("Добавить сотрудника");
        JButton addEventButton = new JButton("Добавить мероприятие");
        JButton addSuggestionButton = new JButton("Добавить пожелание");
        JButton addEquipmentButton = new JButton("Добавить оборудование");
        JButton showEventsButton = new JButton("Показать мероприятия");
        JButton showSuggestionsButton = new JButton("Показать пожелания");
        JButton showEquipmentButton = new JButton("Показать оборудование");
        JButton exitButton = new JButton("Выйти");

        panel.add(addEmployeeButton);
        panel.add(addEventButton);
        panel.add(addSuggestionButton);
        panel.add(addEquipmentButton);
        panel.add(showEventsButton);
        panel.add(showSuggestionsButton);
        panel.add(showEquipmentButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });

        addSuggestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSuggestion();
            }
        });

        addEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipment();
            }
        });

        showEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEvents();
            }
        });

        showSuggestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSuggestions();
            }
        });

        showEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEquipment();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void addEmployee() {
        JFrame frame = new JFrame("Добавить сотрудника");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Имя:");
        JTextField nameField = new JTextField();
        JLabel departmentLabel = new JLabel("Отдел:");
        JTextField departmentField = new JTextField();
        JButton addButton = new JButton("Добавить");

        frame.add(idLabel);
        frame.add(idField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(departmentLabel);
        frame.add(departmentField);
        frame.add(new JLabel());
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String department = departmentField.getText();
                Employee employee = new Employee(id, name, department);
                system.addEmployee(employee);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void addEvent() {
        JFrame frame = new JFrame("Добавить мероприятие");
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Название:");
        JTextField nameField = new JTextField();
        JLabel dateLabel = new JLabel("Дата (dd-MM-yyyy):");
        JTextField dateField = new JTextField();
        JLabel participantsLabel = new JLabel("ID участников (через пробел):");
        JTextField participantsField = new JTextField();
        JButton addButton = new JButton("Добавить");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(participantsLabel);
        frame.add(participantsField);
        frame.add(new JLabel());
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    Date date = dateFormat.parse(dateField.getText());
                    String[] participantIds = participantsField.getText().split(" ");
                    List<Employee> participants = new ArrayList<>();
                    for (String participantId : participantIds) {
                        Employee participant = system.getEmployees().stream()
                                .filter(emp -> emp.getId() == Integer.parseInt(participantId))
                                .findFirst()
                                .orElse(null);
                        if (participant != null) {
                            participants.add(participant);
                        }
                    }
                    Event event = new Event(name, date, participants);
                    system.addEvent(event);
                    frame.dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Неправильный формат даты. Используйте dd-MM-yyyy.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void addSuggestion() {
        JFrame frame = new JFrame("Добавить пожелание");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel suggestionLabel = new JLabel("Пожелание:");
        JTextField suggestionField = new JTextField();
        JLabel employeeIdLabel = new JLabel("ID сотрудника:");
        JTextField employeeIdField = new JTextField();
        JButton addButton = new JButton("Добавить");

        frame.add(suggestionLabel);
        frame.add(suggestionField);
        frame.add(employeeIdLabel);
        frame.add(employeeIdField);
        frame.add(new JLabel());
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String suggestion = suggestionField.getText();
                int employeeId = Integer.parseInt(employeeIdField.getText());
                Employee employee = system.getEmployees().stream()
                        .filter(emp -> emp.getId() == employeeId)
                        .findFirst()
                        .orElse(null);
                if (employee != null) {
                    Suggestion sug = new Suggestion(suggestion, employee);
                    system.addSuggestion(sug);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Сотрудник с указанным ID не найден.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void addEquipment() {
        JFrame frame = new JFrame("Добавить оборудование");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Название:");
        JTextField nameField = new JTextField();
        JLabel priceLabel = new JLabel("Цена:");
        JTextField priceField = new JTextField();
        JButton addButton = new JButton("Добавить");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(new JLabel());
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                Equipment equipment = new Equipment(name, price);
                system.addEquipment(equipment);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void showEvents() {
        JFrame frame = new JFrame("Мероприятия");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (Event event : system.getEvents()) {
            textArea.append(event.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void showSuggestions() {
        JFrame frame = new JFrame("Пожелания");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (Suggestion suggestion : system.getSuggestions()) {
            textArea.append(suggestion.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void showEquipment() {
        JFrame frame = new JFrame("Оборудование");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (Equipment equipment : system.getEquipmentList()) {
            textArea.append(equipment.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

