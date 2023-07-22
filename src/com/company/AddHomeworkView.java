package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHomeworkView extends JFrame {
    private HomeworkModel model;
    private JTextField taskField;
    private JTextField deadlineField;
    private JComboBox<String> typeComboBox;
    private JSpinner importanceSpinner;

    public AddHomeworkView(HomeworkModel model) {
        this.model = model;

        setTitle("Add Homework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

        JPanel panel = new JPanel();

        JLabel taskLabel = new JLabel("Task:");
        taskField = new JTextField(20);

        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineField = new JTextField(20);

        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"Homework", "Exam", "IA", "EE", "Other"};
        typeComboBox = new JComboBox<>(types);

        JLabel importanceLabel = new JLabel("Importance:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 5, 1);
        importanceSpinner = new JSpinner(spinnerModel);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                String deadline = deadlineField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                int importance = (int) importanceSpinner.getValue();
                model.addTask(new HomeworkTask(task, deadline, type, importance));
                taskField.setText("");
                deadlineField.setText("");
                typeComboBox.setSelectedIndex(0);
                importanceSpinner.setValue(1);
            }
        });

        panel.add(taskLabel);
        panel.add(taskField);
        panel.add(deadlineLabel);
        panel.add(deadlineField);
        panel.add(typeLabel);
        panel.add(typeComboBox);
        panel.add(importanceLabel);
        panel.add(importanceSpinner);
        panel.add(new JLabel());
        panel.add(addButton);

        add(panel);
        setVisible(true);

        // Create the menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu switchMenu = new JMenu("Menu");
        JMenuItem listViewItem = new JMenuItem("Homework List");
        listViewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeworkListView listView = new HomeworkListView(model);
                setVisible(false);
                listView.setVisible(true);
            }
        });
        switchMenu.add(listViewItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }
}
