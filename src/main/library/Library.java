package main.library;

import java.util.List;

public class Library {
    private static List<Book> books = FileHandler.loadBooks(); 
    
    public static List<Book> getBooks() {
        return books;
    }
    
    public static void addBook(Book book) {
        books.add(book);
        FileHandler.saveBooks(books); 
    }
    
    public static boolean deleteBook(String identifier) {
        boolean removed = books.removeIf(book -> book.getTitle().equals(identifier) || book.getIsbn().equals(identifier));
        if (removed) {
            FileHandler.saveBooks(books); 
        }
        return removed;
    }

    public static String[][] queryBooks(String query) {
        String lowerCaseQuery = query.toLowerCase();
        List<Book> books = FileHandler.loadBooks();
        return books.stream()
                .filter(book -> containsIgnoreCase(book.getTitle(), lowerCaseQuery) ||
                                containsIgnoreCase(book.getAuthor(), lowerCaseQuery) ||
                                containsIgnoreCase(book.getIsbn(), lowerCaseQuery) ||
                                containsIgnoreCase(book.getGenre(), lowerCaseQuery))
                .map(book -> new String[]{
                    book.getTitle(), 
                    book.getAuthor(), 
                    book.getIsbn(), 
                    book.getGenre(), 
                    String.valueOf(book.getYear())})
                .toArray(String[][]::new);
    }

    private static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.toLowerCase().contains(searchStr);
    }

}
