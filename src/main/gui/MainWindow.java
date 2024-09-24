package main.gui;

import javax.swing.*;
import main.library.FileHandler;
import main.library.Library;

import java.awt.*;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainWindow() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to Library Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28)); 
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        add(welcomeLabel, BorderLayout.NORTH); 
     
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        JButton addButton = new JButton("Add Record");
        JButton deleteButton = new JButton("Delete Record");
        JButton queryButton = new JButton("Query Records");
        JButton saveButton = new JButton("Save Records");

        Dimension buttonSize = new Dimension(200, 50); 
        addButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        queryButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(queryButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(saveButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);
    
        addButton.addActionListener(e -> new AddRecordForm(this));
        deleteButton.addActionListener(e -> new DeleteRecordForm(this));
        queryButton.addActionListener(e -> new QueryInterface(this));
        saveButton.addActionListener(e -> {
            boolean success = FileHandler.saveBooksToRecordsFile(Library.getBooks());
            if (success) {
                JOptionPane.showMessageDialog(this, "Records have been updated.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update records.", "Update Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
