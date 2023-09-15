package com.company;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// figure out how to either delete task or switch the order
// make other windows work

public class HomeworkModel implements Serializable {
    private List<HomeworkTask> tasks;

    public HomeworkModel() {
        tasks = new ArrayList<>();
    }

    public void addTask(HomeworkTask task) {
        tasks.add(task);
    }

    public void deleteTask() {
        HomeworkTask[] toBeDeleted = tasks.toArray(new HomeworkTask[tasks.size()]);
        LocalDate[] localDates = new LocalDate[toBeDeleted.length];
        for (int i = 0; i < toBeDeleted.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(toBeDeleted[i].getDeadline(), formatter);
        }
        for (int i = 0; i < toBeDeleted.length; i++)
        {
            if(localDates[i].isBefore(LocalDate.now()))
                tasks.remove(i);
        }
    }

    public List<HomeworkTask> getTasks() {
        return tasks;
    }

    public List<HomeworkTask> forTomorrow() {
        HomeworkTask[] toBeEvaluated = tasks.toArray(new HomeworkTask[tasks.size()]);
        List<HomeworkTask> tasksDueTomorrow = new ArrayList<>();
        LocalDate[] localDates = new LocalDate[toBeEvaluated.length];

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(toBeEvaluated[i].getDeadline(), formatter);
        }

        for(int i = 0; i < localDates.length; i++)
        {
            if (localDates[i].isBefore(LocalDate.now().plusDays(2)))
                tasksDueTomorrow.add(toBeEvaluated[i]);
        }
        return tasksDueTomorrow;
    }

    public List<HomeworkTask> doNow () {
        // traverses each element of the task list
        // gets importance and the deadline
        //  first the deadline must be converted into numerical values
        // multiplies the values
        // gets top 3 largest by executing the bubble sort
        // get the results to match the task
        HomeworkTask[] toBeEvaluated = tasks.toArray(new HomeworkTask[tasks.size()]);
        LocalDate[] localDates = new LocalDate[toBeEvaluated.length];
        List<HomeworkTask> finalTasks = new ArrayList<>();
        int[] urgencyValues = new int[tasks.size()];

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(toBeEvaluated[i].getDeadline(), formatter);
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


        HomeworkTask[] toBeQualified = tasks.toArray(new HomeworkTask[tasks.size()]);
        int[] qualificationValues = new int[toBeQualified.length];

        for (int i = 0; i < toBeQualified.length; i++)
        {
            qualificationValues[i] = toBeQualified[i].getImportance() * urgencyValues[i];
        }

        boolean changed = true;
        while (changed)
        {
            changed = false;
            for (int j = 1; j < qualificationValues.length; j++)
            {
                if(qualificationValues[j - 1] > qualificationValues[j])
                {
                    swapInt(qualificationValues, j - 1, j);
                    swapHomeworktask(toBeQualified, j - 1, j);
                    changed = true;
                }
            }
        }
        for (int k = qualificationValues.length - 1; k < qualificationValues.length - 3; k--)
        {
            finalTasks.add(toBeQualified[k]);
        }
        return finalTasks;
    }
    public int[] urgencyOfATask() {
        HomeworkTask[] toBeEvaluated = tasks.toArray(new HomeworkTask[tasks.size()]);
        LocalDate[] localDates = new LocalDate[toBeEvaluated.length];
        int[] urgencyValues = new int[tasks.size()];

        for (int i = 0; i < toBeEvaluated.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(toBeEvaluated[i].getDeadline(), formatter);
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
        HomeworkTask[] toBeSorted = tasks.toArray(new HomeworkTask[tasks.size()]);
        LocalDate[] localDates = new LocalDate[toBeSorted.length];
        List<HomeworkTask> tasksByDeadline = new ArrayList<>();

        for (int i = 0; i < toBeSorted.length; i++)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDates[i] = LocalDate.parse(toBeSorted[i].getDeadline(), formatter);
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

    public List<HomeworkTask> longTermProjects () {
        HomeworkTask[] toBeChecked = tasks.toArray(new HomeworkTask[tasks.size()]);
        List<HomeworkTask> dontForgetThese = new ArrayList<>();

        for (int i = 0; i < toBeChecked.length; i++)
        {
            if (toBeChecked[i].getType().equals("IA") || toBeChecked[i].getType().equals("EE"))
            {
                dontForgetThese.add(toBeChecked[i]);
            }
        }
        return dontForgetThese;
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
