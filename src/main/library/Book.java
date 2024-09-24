package main.library;

public class Book {
    private String title, author, isbn, genre;
    private int year;

    public Book(String title, String author, String isbn, String genre, int year) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setGenre(genre);
        this.setYear(year);
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
	    return String.format("%s,%s,%s,%s,%d", title, author, isbn, genre, year);
	}

  
}
