package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AllTasks extends JFrame {
    private HomeworkModel model;

    public AllTasks(HomeworkModel model) {
        this.model = model;

        setTitle("All tasks:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 625);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel taskPanel = new JPanel(new GridLayout(5, 5));

        JLabel urgency = new JLabel("                                                                                                                             Urgency");
        JLabel importance = new JLabel ("Importance");

        JTextArea onefive = new JTextArea(15,15);
        onefive.setEditable(false);
        onefive.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea twofive = new JTextArea(15,15);
        twofive.setEditable(false);
        twofive.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea threefive = new JTextArea(15,15);
        threefive.setEditable(false);
        threefive.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fourfive = new JTextArea(15,15);
        fourfive.setEditable(false);
        fourfive.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fivefive = new JTextArea(15,15);
        fivefive.setEditable(false);
        fivefive.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea onefour = new JTextArea(15,15);
        onefour.setEditable(false);
        onefour.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea twofour = new JTextArea(15,15);
        twofour.setEditable(false);
        twofour.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea threefour = new JTextArea(15,15);
        threefour.setEditable(false);
        threefour.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fourfour = new JTextArea(15,15);
        fourfour.setEditable(false);
        fourfour.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fivefour = new JTextArea(15,15);
        fivefour.setEditable(false);
        fivefour.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea onethree = new JTextArea(15,15);
        onethree.setEditable(false);
        onethree.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea twothree = new JTextArea(15,15);
        twothree.setEditable(false);
        twothree.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea threethree = new JTextArea(15,15);
        threethree.setEditable(false);
        threethree.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fourthree = new JTextArea(15,15);
        fourthree.setEditable(false);
        fourthree.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fivethree = new JTextArea(15,15);
        fivethree.setEditable(false);
        fivethree.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea onetwo = new JTextArea(15,15);
        onetwo.setEditable(false);
        onetwo.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea twotwo = new JTextArea(15,15);
        twotwo.setEditable(false);
        twotwo.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea threetwo = new JTextArea(15,15);
        threetwo.setEditable(false);
        threetwo.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fourtwo = new JTextArea(15,15);
        fourtwo.setEditable(false);
        fourtwo.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fivetwo = new JTextArea(15,15);
        fivetwo.setEditable(false);
        fivetwo.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea oneone = new JTextArea(15,15);
        oneone.setEditable(false);
        oneone.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea twoone = new JTextArea(15,15);
        twoone.setEditable(false);
        twoone.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea threeone = new JTextArea(15,15);
        threeone.setEditable(false);
        threeone.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fourone = new JTextArea(15,15);
        fourone.setEditable(false);
        fourone.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JTextArea fiveone = new JTextArea(15,15);
        fiveone.setEditable(false);
        fiveone.setBorder(BorderFactory.createLineBorder(Color.black,1));

        taskPanel.add(onefive);
        taskPanel.add(twofive);
        taskPanel.add(threefive);
        taskPanel.add(fourfive);
        taskPanel.add(fivefive);
        taskPanel.add(onefour);
        taskPanel.add(twofour);
        taskPanel.add(threefour);
        taskPanel.add(fourfour);
        taskPanel.add(fivefour);
        taskPanel.add(onethree);
        taskPanel.add(twothree);
        taskPanel.add(threethree);
        taskPanel.add(fourthree);
        taskPanel.add(fivethree);
        taskPanel.add(onetwo);
        taskPanel.add(twotwo);
        taskPanel.add(threetwo);
        taskPanel.add(fourtwo);
        taskPanel.add(fivetwo);
        taskPanel.add(oneone);
        taskPanel.add(twoone);
        taskPanel.add(threeone);
        taskPanel.add(fourone);
        taskPanel.add(fiveone);

        panel.add(taskPanel, BorderLayout.CENTER);
        panel.add(urgency, BorderLayout.SOUTH);
        panel.add(importance, BorderLayout.WEST);
        add(panel);
        pack();
        setVisible(true);
        updateTasks();
    }
    public void updateTasks() {
        HomeworkTask[] tasks = model.getTasks().toArray(new HomeworkTask[model.getTasks().size()]);
        int[] urgencies = Arrays.stream(model.urgencyOfATask()).toArray();
        for (int i = 0; i < tasks.length; i++)
        {
            StringBuilder s15 = new StringBuilder();
            StringBuilder s25 = new StringBuilder();
            StringBuilder s35 = new StringBuilder();
            StringBuilder s45 = new StringBuilder();
            StringBuilder s55 = new StringBuilder();
            StringBuilder s14 = new StringBuilder();
            StringBuilder s24 = new StringBuilder();
            StringBuilder s34 = new StringBuilder();
            StringBuilder s44 = new StringBuilder();
            StringBuilder s54 = new StringBuilder();
            StringBuilder s13 = new StringBuilder();
            StringBuilder s23 = new StringBuilder();
            StringBuilder s33 = new StringBuilder();
            StringBuilder s43 = new StringBuilder();
            StringBuilder s53 = new StringBuilder();
            StringBuilder s12 = new StringBuilder();
            StringBuilder s22 = new StringBuilder();
            StringBuilder s32 = new StringBuilder();
            StringBuilder s42 = new StringBuilder();
            StringBuilder s52 = new StringBuilder();
            StringBuilder s11 = new StringBuilder();
            StringBuilder s21 = new StringBuilder();
            StringBuilder s31 = new StringBuilder();
            StringBuilder s41 = new StringBuilder();
            StringBuilder s51 = new StringBuilder();

            if(urgencies[i] == 5 && tasks[i].getImportance() == 5)
                s55.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 4 && tasks[i].getImportance() == 5)
                s45.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 3 && tasks[i].getImportance() == 5)
                s35.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 2 && tasks[i].getImportance() == 5)
                s25.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 1 && tasks[i].getImportance() == 5)
                s15.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 5 && tasks[i].getImportance() == 4)
                s54.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 4 && tasks[i].getImportance() == 4)
                s44.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 3 && tasks[i].getImportance() == 4)
                s34.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 2 && tasks[i].getImportance() == 4)
                s24.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 1 && tasks[i].getImportance() == 4)
                s14.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 5 && tasks[i].getImportance() == 3)
                s53.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 4 && tasks[i].getImportance() == 3)
                s43.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 3 && tasks[i].getImportance() == 3)
                s33.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 2 && tasks[i].getImportance() == 3)
                s23.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 1 && tasks[i].getImportance() == 3)
                s13.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 5 && tasks[i].getImportance() == 2)
                s52.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 4 && tasks[i].getImportance() == 2)
                s42.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 3 && tasks[i].getImportance() == 2)
                s32.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 2 && tasks[i].getImportance() == 2)
                s22.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 1 && tasks[i].getImportance() == 2)
                s12.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 5 && tasks[i].getImportance() == 1)
                s51.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 4 && tasks[i].getImportance() == 1)
                s41.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 3 && tasks[i].getImportance() == 1)
                s31.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 2 && tasks[i].getImportance() == 1)
                s21.append(tasks[i].getTask()).append("\n");

            if(urgencies[i] == 1 && tasks[i].getImportance() == 1)
                s11.append(tasks[i].getTask()).append("\n");
        }
    }
}

