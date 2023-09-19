package com.company;

//Advice

//1. Back up code to GitHub or to a separate file folder before ANY changes.
//NOTE: do this incrementally, so try to run code after every change to verify it works and maybe commit to git.
//2. View never touches the model, i.e. there is never a word called model in a file called view.
//  INSTEAD, View has reference to controller and is able to call functions on controller.
//  AND, Controller has reference to model and is able to call functions on model.
//3. Think of every time you create something new.
//4. Class diagrams and UI diagrams.

public class Main {

    public static void main(String[] args) {
        // MODEL
        HomeworkModel model = new HomeworkModel();

        // CONTROLLER
        HomeworkController controller = new HomeworkController(model);

        // VIEW
        AddHomeworkView addView = new AddHomeworkView(controller);
        HomeworkListView listView = new HomeworkListView(controller);
        CalendarView calendarView = new CalendarView(controller);
        AllTasks allTasks = new AllTasks(controller);

    }
}
