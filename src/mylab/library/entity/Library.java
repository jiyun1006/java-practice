package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
	// 도서관 이름
	private String name;
	// 도서 목록
	private List<Book> books;

	// 기본 생성자
	public Library() {
		this.books = new ArrayList<>();

	}

	public Library(String name) {
		this();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	// 도서 추가
	public void addBook(Book book) {
		this.books.add(book);
		System.out.println("도서가 추가되었습니다: " + book.getTitle());
	}

	// 도서 검색
	public Book findBookByTitle(String title) {
		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				return b;
			}
		}
		return null;
	}

	public List<Book> findBooksByAuthor(String author) {
		List<Book> tmpBook = new ArrayList<>();
		for (Book b : books) {
			if (b.getAuthor().equals(author)) {
				tmpBook.add(b);
			}
		}
		return tmpBook;
	}

	public Book findBookByISBN(String isbn) {
		for (Book b : books) {
			if (b.getIsbn().equals(isbn)) {
				return b;
			}
		}
		return null;
	}

	// ISBN으로 도서 대출, 반납
	public boolean checkOutBook(String isbn) {
		Book book = findBookByISBN(isbn);
		if (book == null) {
			return false;
		}
		return book.checkOut();

	}

	public boolean returnBook(String isbn) {
		Book book = findBookByISBN(isbn);
		if(book == null) return false;
		if(!book.isAvailable()) {
			book.returnBook();
			return true;
		}
		return false;
	}

	// 대출 가능 도서목록
	public List<Book> getAvailableBooks() {
		List<Book> availableBooks = new ArrayList<>();
		for(Book b : books) {
			if(b.isAvailable()) {
				availableBooks.add(b);
			}
		}
		return availableBooks;
	}

	// 전체 도서 목록
	public List<Book> getAllBooks() {
		return new ArrayList<>(books);
	}

	// 도서 수량 정보
	public int getTotalBooks() {
		return books.size();
	}

	public int getAvailableBooksCount() {
		return getAvailableBooks().size();
	}

	public int getBorrowedBooksCount() {
		return getTotalBooks() - getAvailableBooksCount();
	}

}
