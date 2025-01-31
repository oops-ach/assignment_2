package wrongcode2;

import wrongcode2.modules.Book;
import wrongcode2.modules.User;

import java.sql.SQLException;
import java.util.List;

public interface LibraryService {
	List<Book> getAllBooks() throws SQLException, SQLException;
	List<User> getAllUsers() throws SQLException, SQLException;
	void takeBook(String name, String surname, String bookTitle) throws SQLException;
	void returnBook(String name, String surname, String bookTitle) throws SQLException;
	void addNewBook(String title, String author, int year, int quantity) throws SQLException;
}

