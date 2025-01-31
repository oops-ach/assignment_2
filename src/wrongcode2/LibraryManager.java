package wrongcode2;

import wrongcode2.modules.Book;
import wrongcode2.modules.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager implements LibraryService{
	private IDB db;

	public LibraryManager(IDB db) {
		this.db = db;
	}

	@Override
	public List<Book> getAllBooks() throws SQLException {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM librarydb ORDER BY id ASC";
		try (Connection conn = db.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getInt("year"),
						rs.getInt("quantity")
				));
			}
		}catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		return books;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM \"user\" ORDER BY id ASC";
		try (Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				users.add(new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("booktitle")
				));
			}
		}catch(SQLException e){
			System.out.println("SQLException: " + e.getMessage());
		}
		return users;
	}

	@Override
	public void takeBook(String name, String surname, String bookTitle) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String checkQuery = "SELECT quantity FROM librarydb WHERE title = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
				pstmt.setString(1, bookTitle);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next() && rs.getInt("quantity") > 0) {
					String insertQuery = "INSERT INTO \"user\" (name, surname, booktitle) VALUES (?, ?, ?)";
					try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
						insertStmt.setString(1, name);
						insertStmt.setString(2, surname);
						insertStmt.setString(3, bookTitle);
						insertStmt.executeUpdate();
					}

					String updateQuery = "UPDATE librarydb SET quantity = quantity - 1 WHERE title = ?";
					try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
						updateStmt.setString(1, bookTitle);
						updateStmt.executeUpdate();
					}

					System.out.println("Book taken successfully!");
				} else {
					System.out.println("Book not available or out of stock.");
				}
			}
		}
	}

	@Override
	public void returnBook(String name, String surname, String bookTitle) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String checkQuery = "SELECT * FROM \"user\" WHERE name = ? AND surname = ? AND booktitle = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
				pstmt.setString(1, name);
				pstmt.setString(2, surname);
				pstmt.setString(3, bookTitle);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String deleteQuery = "DELETE FROM \"user\" WHERE name = ? AND surname = ? AND booktitle = ?";
					try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
						deleteStmt.setString(1, name);
						deleteStmt.setString(2, surname);
						deleteStmt.setString(3, bookTitle);
						deleteStmt.executeUpdate();
					}

					String updateQuery = "UPDATE librarydb SET quantity = quantity + 1 WHERE title = ?";
					try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
						updateStmt.setString(1, bookTitle);
						updateStmt.executeUpdate();
					}

					System.out.println("Book returned successfully!");
				} else {
					System.out.println("No matching record found.");
				}
			}
		}
	}

	@Override
	public void addNewBook(String title, String author, int year, int quantity) throws SQLException {
		String query = "INSERT INTO librarydb (title, author, year, quantity) VALUES (?, ?, ?, ?)";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, year);
			pstmt.setInt(4, quantity);
			pstmt.executeUpdate();
			System.out.println("New book added successfully!");
		}
	}
}