package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHomeworkView extends JFrame {
    private HomeworkModel model;
    private JTextField taskField;
    private JTextField deadlineField;
    private JTextField deleteField;
    private JTextField editField;
    private JTextField toBeEditedField;
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

        JLabel deadlineLabel = new JLabel("Deadline: (dd-MM-yyyy)");
        deadlineField = new JTextField(20);

        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"Homework", "Exam", "IA", "EE", "Other"};
        typeComboBox = new JComboBox<>(types);

        JLabel importanceLabel = new JLabel("Importance:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 5, 1);
        importanceSpinner = new JSpinner(spinnerModel);

        JLabel deleteLabel = new JLabel("Delete task: (write the name of the task you want to delete)");
        deleteField = new JTextField(20);

        JLabel toBeEditedLabel = new JLabel("Reschedule task: (write the name of the task you want to reschedule)");
        JLabel editLabel = new JLabel ("Enter new deadline: (dd-MM-yyyy)");
        editField = new JTextField(20);
        toBeEditedField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String toBeDeleted = deleteField.getText();
                for (int i = 0; i < model.getTasks().size(); i++)
                {
                    if (toBeDeleted.equals(model.getTasks().get(i).getTask()))
                    {
                        model.getTasks().remove(model.getTasks().get(i));
                    }
                }
                deleteField.setText("");
            }
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                String deadline = deadlineField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                int importance = (int) importanceSpinner.getValue();
                model.addTask(new HomeworkTask(task, deadline, type, importance));
                //controller.addTask(...) <- is what you want instead of the line above

                taskField.setText("");
                deadlineField.setText("");
                typeComboBox.setSelectedIndex(0);
                importanceSpinner.setValue(1);
            }
        });

        JButton editButton = new JButton("Reschedule");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String toBeRescheduled = toBeEditedField.getText();
                String newDeadline = editField.getText();
                for (int i = 0; i < model.getTasks().size(); i++)
                {
                    if (toBeRescheduled.equals(model.getTasks().get(i).getTask()))
                    {
                        model.addTask(new HomeworkTask(model.getTasks().get(i).getTask(), newDeadline, model.getTasks().get(i).getType(), model.getTasks().get(i).getImportance()));
                        model.getTasks().remove(model.getTasks().get(i));
                    }
                }
                toBeEditedField.setText("");
                editField.setText("");
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
        panel.add(deleteLabel);
        panel.add(deleteField);
        panel.add(deleteButton);
        panel.add(toBeEditedLabel);
        panel.add(toBeEditedField);
        panel.add(editLabel);
        panel.add(editField);
        panel.add(editButton);

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

        JMenuItem calendarViewItem = new JMenuItem("Calendar");
        calendarViewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalendarView calendarView = new CalendarView(model);
                setVisible(false);
                calendarView.setVisible(true);
            }
        });

        JMenuItem allTasksItem = new JMenuItem("All Tasks");
        allTasksItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AllTasks allTasks = new AllTasks(model);
                setVisible(false);
                allTasks.setVisible(true);
            }
        });


        switchMenu.add(listViewItem);
        switchMenu.add(calendarViewItem);
        switchMenu.add(allTasksItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }
}
