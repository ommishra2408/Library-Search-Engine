package library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

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