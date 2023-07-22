package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeworkModel {
    private List<HomeworkTask> tasks;

    public HomeworkModel() {
        tasks = new ArrayList<>();
    }

    public void addTask(HomeworkTask task) {
        tasks.add(task);
    }

    public List<HomeworkTask> getTasks() {
        return tasks;
    }

    public List<HomeworkTask> forTomorrow(HomeworkTask task) {
        HomeworkTask[] toBeEvaluated = tasks.toArray(new HomeworkTask[0]);
        List<HomeworkTask> tasksDueTomorrow = new ArrayList<>();
        LocalDate[] localDates = new LocalDate[toBeEvaluated.length];

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            localDates[i] = LocalDate.parse(toBeEvaluated[i].getDeadline());
        }

        for(int i = 0; i < localDates.length; i++)
        {
            if (localDates[i].isBefore(LocalDate.now().plusDays(1)))
                tasksDueTomorrow.add(task);
        }
        return tasksDueTomorrow;
    }

    public HomeworkTask[] doNow (HomeworkTask task) {
        // traverses each element of the task list
        // gets importance and the deadline
        //  first the deadline must be converted into numerical values
        // multiplies the values
        // gets top 3 largest by executing the bubble sort
        // get the results to match the task
        HomeworkTask[] toBeSorted = tasks.toArray(new HomeworkTask[0]);
        int[] qualificationValues = new int[toBeSorted.length];
        HomeworkTask[] finalTasks = new HomeworkTask[3];
        urgencyOfATask();

        for (int i = 0; i < toBeSorted.length; i++)
        {
            qualificationValues[i] = task.getImportance() * urgencyOfATask()[i];
        }

        boolean changed = true;
        while (changed)
        {
            changed = false;
            for (int i = 1; i < qualificationValues.length; i++)
            {
                if(qualificationValues[i - 1] > qualificationValues[i])
                {
                    swapHomeworktask(toBeSorted, i, i - 1);
                    changed = true;
                }
            }
        }
        for (int j = qualificationValues.length; j < qualificationValues.length - 3; j--)
        {
            finalTasks[qualificationValues.length - j] = toBeSorted[j];
        }
        return finalTasks;
    }
    public int[] urgencyOfATask() {
        HomeworkTask[] toBeEvaluated = tasks.toArray(new HomeworkTask[0]);
        LocalDate[] localDates = new LocalDate[toBeEvaluated.length];
        int[] urgencyValues = new int[tasks.size()];

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            localDates[i] = LocalDate.parse(toBeEvaluated[i].getDeadline());
        }

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            if (localDates[i].isBefore(LocalDate.now().plusDays(1)))
                urgencyValues[i] = 5;
            else if (localDates[i].isBefore(LocalDate.now().plusDays(3)))
                urgencyValues[i] = 4;
            else if (localDates[i].isBefore(LocalDate.now().plusDays(7)))
                urgencyValues[i] = 3;
            else if (localDates[i].isBefore(LocalDate.now().plusDays(14)))
                urgencyValues[i] = 2;
            else if (localDates[i].isAfter(LocalDate.now().plusDays(14)))
                urgencyValues[i] = 1;
        }
        return urgencyValues;
    }

    public List<HomeworkTask> sortByDeadline() {
        HomeworkTask[] toBeSorted = tasks.toArray(new HomeworkTask[0]);
        LocalDate[] localDates = new LocalDate[toBeSorted.length];
        List<HomeworkTask> tasksByDeadline = new ArrayList<>();

        for (int i = 0; i < toBeSorted.length; i++)
        {
            localDates[i] = LocalDate.parse(toBeSorted[i].getDeadline());
        }

        boolean changed = true;

        while (changed)
        {
            changed = false;
            for (int i = 1; i < toBeSorted.length; i++)
            {
                if (localDates[i - 1].isAfter(localDates[i]))
                {
                    swapHomeworktask(toBeSorted, i, i - 1);
                    changed = true;
                }
            }
        }
        for (int i = 0; i < toBeSorted.length; i++)
        {
            tasksByDeadline.add(toBeSorted[i]);
        }
        return tasksByDeadline;
    }

    public static void swapHomeworktask (HomeworkTask[] arr, int i, int j)
    {
        HomeworkTask tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swapInt (int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
