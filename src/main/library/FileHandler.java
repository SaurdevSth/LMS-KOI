package main.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	public static final String BOOK_PATH = "src/resources/data/books.txt";
	public static final String RECORDS_PATH = "src/resources/data/records.txt";

	public static void saveBooks(List<Book> books) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_PATH))) {
			for (Book book : books) {
				writer.write(book.toString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean saveBooksToRecordsFile(List<Book> books) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_PATH))) {
			for (Book book : books) {
				writer.write("Title : " + book.getTitle() + "\n");
				writer.write("Author name : " + book.getAuthor() + "\n");
				writer.write("Genre: " + book.getGenre() + "\n");
				writer.write("ISBN : " + book.getIsbn() + "\n");
				writer.write("Year : " + book.getYear() + "\n");
				writer.write("----------------------------------------\n");
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false; 
		}	
	}

	public static List<Book> loadBooks() {
		List<Book> books = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 5) {
					books.add(new Book(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	public static void deleteBook(String identifier) {
		List<Book> books = loadBooks();
		books.removeIf(book -> book.getTitle().equalsIgnoreCase(identifier) || book.getIsbn().equals(identifier));
		saveBooks(books);
	}
	
	

}
