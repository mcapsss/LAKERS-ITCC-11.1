package lakers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUI extends JFrame {
   
    private JTextField taskTitleField, taskDescriptionField; 
    private JList<String> taskDisplayList; 
    private DefaultListModel<String> taskListModel;
    private List<Task> taskData = new ArrayList<>(); 


    class Task { 
        String title;
        String description;
        boolean isComplete; 

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
            this.isComplete = false; 
        }
    }

    public TaskManagerGUI() {
        super("Task Manager (Lakers Edition)"); 

        taskTitleField = new JTextField(20);
        taskDescriptionField = new JTextField(20);
        JButton addTaskButton = new JButton("Add Task"); 
        taskListModel = new DefaultListModel<>();
        taskDisplayList = new JList<>(taskListModel);
        JButton markCompleteButton = new JButton("Mark as Done");


        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Task Title:"));
        inputPanel.add(taskTitleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(taskDescriptionField);
        inputPanel.add(addTaskButton);

        JScrollPane listPane = new JScrollPane(taskDisplayList); 

        add(inputPanel, BorderLayout.NORTH);
        add(listPane, BorderLayout.CENTER);
        add(markCompleteButton, BorderLayout.SOUTH);


        addTaskButton.addActionListener(e -> createTask()); 
        markCompleteButton.addActionListener(e -> markTaskComplete());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void createTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        taskData.add(new Task(title, description));
        taskListModel.addElement(title + " - " + description);


        taskTitleField.setText(""); 
        taskDescriptionField.setText("");
    }

    private void markTaskComplete() {
        int selectedIndex = taskDisplayList.getSelectedIndex();
        if (selectedIndex >= 0) {
            taskData.get(selectedIndex).isComplete = true;
            String updatedTaskText = taskListModel.get(selectedIndex) + " (Done!)";
            taskListModel.set(selectedIndex, updatedTaskText); 
        }
    }
   
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }
}