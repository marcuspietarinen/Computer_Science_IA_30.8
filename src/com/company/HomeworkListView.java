package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeworkListView extends JFrame {
    private HomeworkModel model;
    private JTextArea taskArea;
    private JTextArea forTomorrowArea;
    private JTextArea otherSuggestionsArea;
    private JTextArea longTermProjectsArea;


    public HomeworkListView(HomeworkModel model) {
        this.model = model;

        setTitle("Homework List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

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

        taskArea = new JTextArea(10, 20);
        taskArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(taskArea);

        panel.add(scrollPane);

        add(panel);
        // Create the menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu switchMenu = new JMenu("Menu");
        JMenuItem addViewMenuItem = new JMenuItem("Add Homework");
        addViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddHomeworkView addView = new AddHomeworkView(model);
                setVisible(false);
                addView.setVisible(true);
            }
        });
        switchMenu.add(addViewMenuItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);

        setVisible(true);

        updateTaskList();
    }

    public void updateTaskList() {
        List<HomeworkTask> tasks = model.getTasks();
        StringBuilder sb = new StringBuilder();

        for (HomeworkTask task : tasks) {
            sb.append(task.getTask()).append(" - ").append(task.getDeadline()).append("\n");
        }

        taskArea.setText(sb.toString());
    }
}
