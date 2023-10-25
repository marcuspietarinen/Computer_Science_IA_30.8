package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
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
    private JTextArea[] weekdays;

    public CalendarView (HomeworkController controller) {
        super("Calendar");
        this.controller = controller;

        startDate = LocalDate.now();

        JPanel calendarPanel = new JPanel();

        JPanel weekPanel = new JPanel();
        weekPanel.setLayout(new GridLayout(0, 7));

        setTitle("Calendar");
        setSize(1000, 625);

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

        weekdays = new JTextArea[7];
        for (int i = 0; i < 7; i++)
        {
            weekdays[i] = new JTextArea(15, 10);
            weekdays[i].setEditable(false);
            weekdays[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            weekPanel.add(weekdays[i]);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDate = startDate;
        LocalDate baseDate = LocalDate.parse("28.08.2023", formatter);

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
                controller.toggleAddHomeworkView();
                controller.toggleCalendarView();
            }
        });
        JMenuItem listViewMenuItem = new JMenuItem("Homework List");
        listViewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.toggleListView();
                controller.toggleCalendarView();
            }
        });

        JMenuItem allTasksItem = new JMenuItem("All Tasks");
        allTasksItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.toggleAllTasks();
                controller.toggleCalendarView();
            }
        });

        calendarPanel.add(ButtonPanel);
        calendarPanel.add(weekPanel);
        add(calendarPanel);
        switchMenu.add(addViewMenuItem);
        switchMenu.add(listViewMenuItem);
        switchMenu.add(allTasksItem);
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

        for (int i = 0; i < realTasks.length; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            localDates[i] = LocalDate.parse(realTasks[i].getDeadline(), formatter);
        }

        for (int i = 0; i < realTasks.length; i++)
        {
            if (localDates[i].getDayOfWeek().equals(DayOfWeek.MONDAY))
                sbMon.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.TUESDAY))
                sbTue.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.WEDNESDAY))
                sbWed.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.THURSDAY))
                sbThu.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.FRIDAY))
                sbFri.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.SATURDAY))
                sbSat.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");

            if (localDates[i].getDayOfWeek().equals(DayOfWeek.SUNDAY))
                sbSun.append(realTasks[i].getTask()).append(realTasks[i].getDeadline()).append("\n");
        }
        weekdays[0].setText(sbMon.toString());
        weekdays[1].setText(sbTue.toString());
        weekdays[2].setText(sbWed.toString());
        weekdays[3].setText(sbThu.toString());
        weekdays[4].setText(sbFri.toString());
        weekdays[5].setText(sbSat.toString());
        weekdays[6].setText(sbSun.toString());
    }
}
