package utils;

import javax.swing.JOptionPane;

public class FormValidator {

    public static boolean validateBook(String title, String author, String isbn, String genre, String year) {
        if (!isValidTitle(title)) {
            showError("Invalid Title", "The title field cannot be empty.");
            return false;
        }

        if (!isValidAuthor(author)) {
            showError("Invalid Author", "The author field cannot be empty.");
            return false;
        }

        if (!isValidIsbn(isbn)) {
            showError("Invalid ISBN", "Please enter valid ISBN");
            return false;
        }

        if (!isValidGenre(genre)) {
            showError("Invalid Genre", "The genre field cannot be empty.");
            return false;
        }

        if (!isValidYear(year)) {
            showError("Invalid Year", "The year must be a valid 4-digit number.");
            return false;
        }

        return true;
    }
    
    public static boolean validateSearchQuery(String value) {
    	return !isNullOrEmpty(value);    	
    }
    
    public static boolean validateDeleteQuery(String value) {
    	return !isNullOrEmpty(value);   
    }
 
    private static boolean isValidTitle(String title) {
        return !isNullOrEmpty(title);
    }


    private static boolean isValidAuthor(String author) {
        return !isNullOrEmpty(author);
    }

    private static boolean isValidIsbn(String isbn) {
       return !isNullOrEmpty(isbn);
    }

    private static boolean isValidGenre(String genre) {
        return !isNullOrEmpty(genre);
    }

    private static boolean isValidYear(String year) {
        if (isNullOrEmpty(year)) {
            return false;
        }
        return year.matches("\\d{4}");
    }


    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

  
    private static void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}