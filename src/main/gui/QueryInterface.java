package main.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.library.Library;
import utils.FormValidator;

import java.awt.*;

public class QueryInterface extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField queryField;
    private JTable resultTable;

    public QueryInterface(JFrame parent) {
        super(parent, "Query Books", true);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel queryLabel = new JLabel("Enter Title, Author, Genre, or ISBN:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(queryLabel, gbc);

        queryField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(queryField, gbc);

        JButton queryButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(queryButton, gbc);

        // Create a non-editable table model
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Title", "Author", "ISBN", "Genre", "Year"}, 0) {
            private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing
            }
        };

        resultTable = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(tableScrollPane, gbc);

        queryButton.addActionListener(e -> {
            String query = queryField.getText().trim();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid search: Please enter a value to search.");
                return;
            }

            if (FormValidator.validateSearchQuery(query)) {
                String[][] data = Library.queryBooks(query); // Query from file
                if (data.length == 0) {
                    JOptionPane.showMessageDialog(this, "No results found for your query.");
                } else {
                    model.setRowCount(0); // Clear previous results
                    for (String[] row : data) {
                        model.addRow(row); // Add new results
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid search: Please enter a valid query.");
            }
        });

        setSize(800, 600);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}