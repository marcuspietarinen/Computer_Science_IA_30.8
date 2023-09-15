package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class HomeworkController {
    private HomeworkModel model;
    private AddHomeworkView addView;
    private HomeworkListView listView;
    private CalendarView calendarView;
    //private AllTasks allTasks;

    public HomeworkController(HomeworkModel model, AddHomeworkView addView, HomeworkListView listView, CalendarView calendarView /*, AllTasks allTasks*/) {
        this.model = model;
        this.addView = addView;
        this.listView = listView;
        this.calendarView = calendarView;
        //this.allTasks = allTasks;

        String fname = "data/model.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            List<HomeworkTask> tasks = new ArrayList<>(model.getTasks());
            out.writeObject(tasks);
        }

        catch (Exception e) {
            System.out.println(e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            List<HomeworkTask> tasks = (List) in.readObject();
            System.out.println(tasks);
        }

        catch (Exception e) {
            System.out.println(e);
        }


        addView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                listView.updateTaskList();
            }
        });
        addView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                calendarView.updateCalendar();
            }
        });
    }
}
