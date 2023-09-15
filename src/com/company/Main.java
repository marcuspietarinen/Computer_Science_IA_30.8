package com.company;

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
