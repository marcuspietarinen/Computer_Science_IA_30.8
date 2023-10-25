package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeworkListView extends JFrame {
    private HomeworkController controller;
    private JTextArea forTomorrowArea;
    private JTextArea otherSuggestionsArea;
    private JTextArea longTermProjectsArea;


    public HomeworkListView(HomeworkController controller) {
        this.controller = controller;

        setTitle("Homework List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 625);

        JPanel panel = new JPanel();
        JLabel forTomorrowLabel = new JLabel("For Tomorrow");
        forTomorrowArea = new JTextArea(10, 20);
        forTomorrowArea.setEditable(false);

        JLabel otherSuggestionsLabel = new JLabel("Other Suggestions");
        otherSuggestionsArea = new JTextArea(10, 20);
        otherSuggestionsArea.setEditable(false);

        JLabel longTermProjectsLabel = new JLabel("Long-Term Projects");
        longTermProjectsArea = new JTextArea(10, 20);
        longTermProjectsArea.setEditable(false);

        panel.add(forTomorrowLabel);
        panel.add(forTomorrowArea);
        panel.add(otherSuggestionsLabel);
        panel.add(otherSuggestionsArea);
        panel.add(longTermProjectsLabel);
        panel.add(longTermProjectsArea);

        add(panel);

        // Create the menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu switchMenu = new JMenu("Menu");
        JMenuItem addViewMenuItem = new JMenuItem("Add Homework");
        addViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.toggleAddHomeworkView();
                controller.toggleListView();
            }
        });

        JMenuItem calendarViewItem = new JMenuItem("Calendar");
        calendarViewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.toggleCalendarView();
                controller.toggleListView();
            }
        });

        JMenuItem allTasksItem = new JMenuItem("All Tasks");
        allTasksItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.toggleAllTasks();
                controller.toggleListView();
            }
        });

        switchMenu.add(addViewMenuItem);
        switchMenu.add(calendarViewItem);
        switchMenu.add(allTasksItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);

        setVisible(true);

        updateTaskList();
    }

    public void updateTaskList() {

        List<HomeworkTask> dueTomorrow = controller.forTomorrow();
        StringBuilder sb1 = new StringBuilder();

        List<HomeworkTask> suggestions = controller.doNow();
        StringBuilder sb2 = new StringBuilder();

        List<HomeworkTask> dontForgetThese = controller.longTermProjects();
        StringBuilder sb3 = new StringBuilder();

        for (HomeworkTask task : dueTomorrow) {
            sb1.append(task.getTask()).append("\n");
        }

        for (HomeworkTask task : suggestions) {
            sb2.append(task.getTask()).append("\n");
        }


        for (HomeworkTask task : dontForgetThese) {
            sb3.append(task.getTask()).append(task.getDeadline()).append("\n");
        }

        forTomorrowArea.setText(sb1.toString());
        otherSuggestionsArea.setText(sb2.toString());
        longTermProjectsArea.setText(sb3.toString());

    }
}
