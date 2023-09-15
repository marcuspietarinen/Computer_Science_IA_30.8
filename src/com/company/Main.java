package com.company;

//Advice

//1. Back up code to GitHub or to a separate file folder before ANY changes.
//NOTE: do this incrementally, so try to run code after every change to verify it works and maybe commit to git.
//2. View never touches the model, i.e. there is never a word called model in a file called view.
//  INSTEAD, View has reference to controller and is able to call functions on controller.
//  AND, Controller has reference to model and is able to call functions on model.
//3. Think of every time you create something new.

public class Main {

    public static void main(String[] args) {
        HomeworkModel model = new HomeworkModel();
        AddHomeworkView addView = new AddHomeworkView(model);
        HomeworkListView listView = new HomeworkListView(model);
        CalendarView calendarView = new CalendarView(model);
        //AllTasks allTasks = new AllTasks(model);
        HomeworkController controller = new HomeworkController(model, addView, listView, calendarView);
    }
}
