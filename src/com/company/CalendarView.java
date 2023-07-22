package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarView extends JFrame {
    private HomeworkModel model;
    private JPanel calendarPanel;
    private LocalDate startDate;

    public CalendarView (HomeworkModel model) {
        this.model = model;
        startDate = LocalDate.now();

        setTitle("Calendar");
        setSize(1000, 625);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(null);
        calendarPanel.setBounds(50, 312, 900, 263);

        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(0,6));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM d");
        LocalDate currentDate = startDate;

        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BorderLayout());

        JLabel dateLabel = new JLabel(currentDate.format(formatter), JLabel.CENTER);
        dayPanel.add(dateLabel, BorderLayout.NORTH);

        JTextArea tasksArea = new JTextArea();
        tasksArea.setPreferredSize(new Dimension(150,100));
        tasksArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tasksArea);
        dayPanel.add(scrollPane, BorderLayout.CENTER);

        // add buttons at this point

        // Create the menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu switchMenu = new JMenu("Switch");
        JMenuItem addViewMenuItem = new JMenuItem("Add Homework");
        addViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddHomeworkView addView = new AddHomeworkView(model);
                setVisible(false);
                addView.setVisible(true);
            }
        });
        switchMenu.add(addViewMenuItem);
        JMenuItem listViewMenuItem = new JMenuItem("Homework List");
        listViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeworkListView listView = new HomeworkListView(model);
                setVisible(false);
                listView.setVisible(true);
            }
        });
        switchMenu.add(listViewMenuItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }
    private void updateCalendar () {
        for (int i = 0; i < 6; i++)
        {
            List<HomeworkTask> tasks = model.sortByDeadline();

        }
    }
}
