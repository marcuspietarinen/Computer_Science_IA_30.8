package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeworkListView extends JFrame {
    private HomeworkModel model;
    private JTextArea forTomorrowArea;
    private JTextArea otherSuggestionsArea;
    private JTextArea longTermProjectsArea;


    public HomeworkListView(HomeworkModel model) {
        this.model = model;

        setTitle("Homework List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 625);

        JPanel panel = new JPanel();
        //setLayout(null);
        JLabel forTomorrowLabel = new JLabel("For Tomorrow");
        forTomorrowArea = new JTextArea(10, 20);
        forTomorrowArea.setEditable(false);
        //forTomorrowArea.setBounds(50, 312, 300, 262);

        JLabel otherSuggestionsLabel = new JLabel("Other Suggestions");
        otherSuggestionsArea = new JTextArea(10, 20);
        otherSuggestionsArea.setEditable(false);
        //otherSuggestionsArea.setBounds(350, 312, 300, 262);

        JLabel longTermProjectsLabel = new JLabel("Long-Term Projects");
        longTermProjectsArea = new JTextArea(10, 20);
        longTermProjectsArea.setEditable(false);
        //longTermProjectsArea.setBounds(650, 312, 300, 262);

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

        List<HomeworkTask> dueTomorrow = model.forTomorrow();
        StringBuilder sb1 = new StringBuilder();

        //HomeworkTask[] suggestions = model.doNow();
        List<HomeworkTask> suggestions = model.doNow();
        StringBuilder sb2 = new StringBuilder();

        List<HomeworkTask> dontForgetThese = model.longTermProjects();
        StringBuilder sb3 = new StringBuilder();

        for (HomeworkTask task : dueTomorrow) {
            sb1.append(task.getTask()).append("\n");
        }

        //for (int i = 0; i < model.doNow().length; i++)
        for (HomeworkTask task : suggestions)
        {
            //sb2.append(suggestions[i].getTask()).append("\n");
            sb2.append(task.getTask()).append("\n");
        }

        /*for (HomeworkTask task : suggestions) {
            sb2.append(task.getTask()).append("\n");
        }*/

        for (HomeworkTask task : dontForgetThese) {
            sb3.append(task.getTask()).append(task.getDeadline()).append("\n");
        }

        forTomorrowArea.setText(sb1.toString());
        otherSuggestionsArea.setText(sb2.toString());
        longTermProjectsArea.setText(sb3.toString());

    }
}
