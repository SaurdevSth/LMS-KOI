package main.gui;

import javax.swing.*;
import main.library.Book;
import main.library.Library;
import utils.FormValidator;
import java.awt.*;

public class AddRecordForm extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private JTextField titleField, authorField, isbnField, genreField, yearField;

    public AddRecordForm(JFrame parent) {
        super(parent, "Add New Book", true);
        
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        titleField = new JTextField();
        authorField = new JTextField();
        isbnField = new JTextField();
        genreField = new JTextField();
        yearField = new JTextField();
        
     
        addLabelAndField(formPanel, gbc, "Title:", titleField, 0);
        addLabelAndField(formPanel, gbc, "Author:", authorField, 1);
        addLabelAndField(formPanel, gbc, "ISBN:", isbnField, 2);
        addLabelAndField(formPanel, gbc, "Genre:", genreField, 3);
        addLabelAndField(formPanel, gbc, "Year:", yearField, 4);

        add(formPanel, BorderLayout.CENTER);
        
   
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 40)); // Bigger button for better UX
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            if (FormValidator.validateBook(titleField.getText(), authorField.getText(), isbnField.getText(), genreField.getText(), yearField.getText())) {
                Library.addBook(new Book(titleField.getText(), authorField.getText(), isbnField.getText(), genreField.getText(), Integer.parseInt(yearField.getText())));
                JOptionPane.showMessageDialog(this, "Book added successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
            }
        });


       
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.weightx = 0.2;
        panel.add(new JLabel(labelText), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(textField, gbc);
    }
}
