package library;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static LibraryDAO dao = new LibraryDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Search Books");
            System.out.println("2. Add New Book");
            System.out.println("3. View All Books");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Check Availability");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    searchBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    viewAllBooks();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    checkAvailability();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void searchBooks() {
        System.out.print("Enter title (or leave blank): ");
        String title = scanner.nextLine();
        System.out.print("Enter author (or leave blank): ");
        String author = scanner.nextLine();
        System.out.print("Enter genre (or leave blank): ");
        String genre = scanner.nextLine();
        System.out.print("Enter year (or 0 to skip): ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Integer yearParam = year == 0 ? null : year;

        List<Book> results = dao.searchBooks(title, author, genre, yearParam);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Is Available (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Book book = new Book(id, title, author, genre, year, available);
        dao.addBook(book);
    }

    private static void viewAllBooks() {
        List<Book> books = dao.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new Year: ");
        int year = scanner.nextInt();
        System.out.print("Is Available (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Book book = new Book(id, title, author, genre, year, available);
        dao.updateBook(book);
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        dao.deleteBook(id);
    }

    private static void checkAvailability() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean available = dao.checkAvailability(id);
        System.out.println("Book " + id + " is " + (available ? "Available" : "Issued"));
    }
}