package library;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    private static LibraryDAO dao = new LibraryDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MODE SELECTION ---");
            System.out.println("1. Launch Graphical UI (Recommended)");
            System.out.println("2. Continue in Terminal/CLI");
            System.out.println("3. Exit");
            System.out.print("Selection: ");
            
            int mode = readInt();
            if (mode == 1) {
                launchGUI();
                break; 
            } else if (mode == 2) {
                runCLI();
            } else {
                System.exit(0);
            }
        }
    }

    private static void launchGUI() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new LibraryUI().setVisible(true));
    }

    private static void runCLI() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Search Books");
            System.out.println("2. Add New Book");
            System.out.println("3. View All Books");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Check Availability");
            System.out.println("7. Export to CSV");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

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
                    exportBooks();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }

    private static void searchBooks() {
        System.out.print("Enter title (or leave blank): ");
        String title = scanner.nextLine();
        System.out.print("Enter author (or leave blank): ");
        String author = scanner.nextLine();
        System.out.print("Enter genre (or leave blank): ");
        String genre = scanner.nextLine();
        System.out.print("Enter year (or 0 to skip): ");
        int year = readInt();

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
        int id = readInt();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = readInt();
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
        int id = readInt();
        System.out.print("Enter new Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new Year: ");
        int year = readInt();
        System.out.print("Is Available (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Book book = new Book(id, title, author, genre, year, available);
        dao.updateBook(book);
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = readInt();
        dao.deleteBook(id);
    }

    private static void checkAvailability() {
        System.out.print("Enter Book ID: ");
        int id = readInt();
        boolean available = dao.checkAvailability(id);
        System.out.println("Book " + id + " is " + (available ? "Available" : "Issued"));
    }

    private static void exportBooks() {
        System.out.print("Enter filename to save (e.g., library_report.csv): ");
        String filename = scanner.nextLine();
        try {
            ExportUtility.exportToCSV(dao.getAllBooks(), filename);
            System.out.println("Successfully exported to " + filename);
        } catch (IOException e) {
            System.out.println("Error during export: " + e.getMessage());
        }
    }
}