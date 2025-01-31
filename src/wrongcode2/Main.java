package wrongcode2;

import wrongcode2.modules.Book;
import wrongcode2.modules.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "135790", "library");
		LibraryManager manager = new LibraryManager(db);

		while (true) {
			System.out.println("\nMenu:");
			System.out.println("1. Show all books");
			System.out.println("2. Take a book");
			System.out.println("3. Return a book");
			System.out.println("4. Add a new book");
			System.out.println("5. Show all users");
			System.out.println("0. Exit");
			System.out.print("Choose an option: ");
			int choice = getIntInput(scanner);

			try {
				switch (choice) {
					case 1:
						List<Book> books = manager.getAllBooks();
						for (Book book : books) {
							System.out.println(book);
						}
						break;
					case 2:
						System.out.print("Enter your name: ");
						String name = scanner.nextLine();
						System.out.print("Enter your surname: ");
						String surname = scanner.nextLine();
						System.out.print("Enter the book title: ");
						String bookTitle = scanner.nextLine();
						manager.takeBook(name, surname, bookTitle);
						break;
					case 3:
						System.out.print("Enter your name: ");
						name = scanner.nextLine();
						System.out.print("Enter your surname: ");
						surname = scanner.nextLine();
						System.out.print("Enter the book title: ");
						bookTitle = scanner.nextLine();
						manager.returnBook(name, surname, bookTitle);
						break;
					case 4:
						System.out.print("Enter the book title: ");
						String title = scanner.nextLine();
						System.out.print("Enter the author: ");
						String author = scanner.nextLine();
						System.out.print("Enter the year: ");
						int year = getIntInput(scanner);
						System.out.print("Enter the quantity: ");
						int quantity = getIntInput(scanner);
						manager.addNewBook(title, author, year, quantity);
						break;
					case 5:
						List<User> users = manager.getAllUsers();
						for (User user : users) {
							System.out.println(user);
						}
						break;
					case 0:
						System.out.println("Exiting...");
						db.close();
						return;
					default:
						System.out.println("Invalid option. Please try again.");
				}
			} catch (SQLException e) {
				System.out.println("Database error: " + e.getMessage());
			}
		}
	}

	private static int getIntInput(Scanner scanner) {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a number: ");
			}
		}
	}
}
