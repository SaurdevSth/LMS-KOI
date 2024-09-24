package main.gui;

import java.awt.*;
import javax.swing.*;
import main.library.Library;
import utils.FormValidator;

public class DeleteRecordForm extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField deleteField;

    public DeleteRecordForm(JFrame parent) {
        super(parent, "Delete Book", true);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  
        JLabel label = new JLabel("Enter Title or ISBN:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  
        add(label, gbc);

        // Text field for user input
        deleteField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(deleteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  
        add(new JLabel(""), gbc);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  
        gbc.anchor = GridBagConstraints.CENTER; 
        add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
        	if(FormValidator.validateDeleteQuery(deleteField.getText())) {
        		String input = deleteField.getText();
                if (Library.deleteBook(input)) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                }
                dispose();
        	} else {
        		JOptionPane.showMessageDialog(this, "Nothing to delete");
        	}
            
        });


        
        setSize(400, 200);  
        setLocationRelativeTo(parent); 
        setVisible(true);
    }
}
