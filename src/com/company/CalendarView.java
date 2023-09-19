package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
idea for implementation:
figure out the parsing errors first
A random monday is set as the base date, not possible to go more in the past.
instead of weekdays, a date would be shown in the caption.
Each date would have its own text area where the needed data is assigned.
week would change automatically every 7 days.

 */

public class CalendarView extends JFrame {
    private HomeworkController controller;
    private LocalDate startDate;
    private JTextArea monday;
    private JTextArea tuesday;
    private JTextArea wednesday;
    private JTextArea thursday;
    private JTextArea friday;
    private JTextArea saturday;
    private JTextArea sunday;

    public CalendarView (HomeworkController controller) {
        super("Calendar");
        this.controller = controller;

        startDate = LocalDate.now();
        monday = new JTextArea(15, 10);
        monday.setEditable(false);
        monday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tuesday = new JTextArea(15, 10);
        tuesday.setEditable(false);
        tuesday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        wednesday = new JTextArea(15, 10);
        wednesday.setEditable(false);
        wednesday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        thursday = new JTextArea(15, 10);
        thursday.setEditable(false);
        thursday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        friday = new JTextArea(15, 10);
        friday.setEditable(false);
        friday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        saturday = new JTextArea(15, 10);
        saturday.setEditable(false);
        saturday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        sunday = new JTextArea(15,10);
        sunday.setEditable(false);
        sunday.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel calendarPanel = new JPanel();


        setTitle("Calendar");
        setSize(1000, 625);



        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(0, 7));


        JLabel mondayLabel = new JLabel("Monday");
        JLabel tuesdayLabel = new JLabel("Tuesday");
        JLabel wednesdayLabel = new JLabel("Wednesday");
        JLabel thursdayLabel = new JLabel("Thursday");
        JLabel fridayLabel = new JLabel("Friday");
        JLabel saturdayLabel = new JLabel("Saturday");
        JLabel sundayLabel = new JLabel("Sunday");

        weekPanel.add(mondayLabel);
        weekPanel.add(tuesdayLabel);
        weekPanel.add(wednesdayLabel);
        weekPanel.add(thursdayLabel);
        weekPanel.add(fridayLabel);
        weekPanel.add(saturdayLabel);
        weekPanel.add(sundayLabel);
        weekPanel.add(monday);
        weekPanel.add(tuesday);
        weekPanel.add(wednesday);
        weekPanel.add(thursday);
        weekPanel.add(friday);
        weekPanel.add(saturday);
        weekPanel.add(sunday);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = startDate;
        LocalDate baseDate = LocalDate.parse("28-08-2023", formatter);

        /*JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BorderLayout());

        JLabel dateLabel = new JLabel(currentDate.format(formatter), JLabel.CENTER);
        dayPanel.add(dateLabel, BorderLayout.NORTH);

        JTextArea tasksArea = new JTextArea();
        tasksArea.setPreferredSize(new Dimension(150,100));
        tasksArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tasksArea);
        dayPanel.add(scrollPane, BorderLayout.CENTER); */

        JButton prevWeekButton = new JButton("Previous Week");
        prevWeekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startDate = startDate.minusWeeks(1);
                //updateCalendar();
            }
        });

        JButton nextWeekButton = new JButton("Next Week");
        nextWeekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startDate = startDate.plusWeeks(1);
                //updateCalendar();
            }
        });
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(prevWeekButton);
        ButtonPanel.add(nextWeekButton);


        // Create the menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu switchMenu = new JMenu("Switch");
        JMenuItem addViewMenuItem = new JMenuItem("Add Homework");
        addViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //AddHomeworkView addView = new AddHomeworkView(model);
                //controller.toggleAddHomeworkView();
                setVisible(false);
                //addView.setVisible(true);
            }
        });
        switchMenu.add(addViewMenuItem);
        JMenuItem listViewMenuItem = new JMenuItem("Homework List");
        listViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //HomeworkListView listView = new HomeworkListView(model);
                controller.toggleListView();
                //listView.setVisible(true);
            }
        });

        calendarPanel.add(ButtonPanel);
        calendarPanel.add(weekPanel);
        add(calendarPanel);
        switchMenu.add(listViewMenuItem);
        menuBar.add(switchMenu);
        setJMenuBar(menuBar);
        pack();
        setVisible(true);

        updateCalendar();
    }
    public void updateCalendar() {
        // copy the listview way to make tasks visible;
        HomeworkTask[] realTasks = controller.sortByDeadline().toArray(new HomeworkTask[controller.sortByDeadline().size()]);
        LocalDate[] localDates = new LocalDate[realTasks.length];

        StringBuilder sbMon = new StringBuilder();
        StringBuilder sbTue = new StringBuilder();
        StringBuilder sbWed = new StringBuilder();
        StringBuilder sbThu = new StringBuilder();
        StringBuilder sbFri = new StringBuilder();
        StringBuilder sbSat = new StringBuilder();
        StringBuilder sbSun = new StringBuilder();

        for (int i = 0; i < realTasks.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(realTasks[i].getDeadline(), formatter);

            if (localDates[i].getDayOfWeek().equals("Monday"))
                sbMon.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            else if (localDates[i].getDayOfWeek().equals("Tuesday"))
                sbTue.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            else if (localDates[i].getDayOfWeek().equals("Wednesday"))
                sbWed.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            else if (localDates[i].getDayOfWeek().equals("Thursday"))
                sbThu.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            else if (localDates[i].getDayOfWeek().equals("Friday"))
                sbFri.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");
            else if (localDates[i].getDayOfWeek().equals("Saturday"))
                sbSat.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            else if (localDates[i].getDayOfWeek().equals("Sunday"))
                sbSun.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");
        }
        monday.setText(sbMon.toString());
        tuesday.setText(sbTue.toString());
        wednesday.setText(sbWed.toString());
        thursday.setText(sbThu.toString());
        friday.setText(sbFri.toString());
        saturday.setText(sbSat.toString());
        sunday.setText(sbSun.toString());
    }
}
