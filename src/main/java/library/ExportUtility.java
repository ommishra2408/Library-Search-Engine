package library;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportUtility {
    public static void exportToCSV(List<Book> books, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV Header
            writer.append("Book ID,Title,Author,Genre,Year,Status\n");

            // Write Book Data
            for (Book book : books) {
                writer.append(String.valueOf(book.getBookId())).append(",");
                writer.append(escapeCSV(book.getTitle())).append(",");
                writer.append(escapeCSV(book.getAuthor())).append(",");
                writer.append(escapeCSV(book.getGenre())).append(",");
                writer.append(String.valueOf(book.getYear())).append(",");
                writer.append(book.isAvailability() ? "Available" : "Issued").append("\n");
            }
        }
    }

    private static String escapeCSV(String value) {
        if (value == null) return "";
        // Wrap in quotes and escape existing quotes to handle commas within titles/names
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}