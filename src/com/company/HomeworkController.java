package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//Advice

//1. Back up code to GitHub or to a separate file folder before ANY changes.
//NOTE: do this incrementally, so try to run code after every change to verify it works and maybe commit to git.
//2. View never touches the model, i.e. there is never a word called model in a file called view.
//  INSTEAD, View has reference to controller and is able to call functions on controller.
//  AND, Controller has reference to model and is able to call functions on model.
//3. Think of every time you create something new.


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
