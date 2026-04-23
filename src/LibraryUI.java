import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibraryUI extends JFrame {

    JTextField titleField, authorField, genreField, isbnField, searchField;
    JTable table;
    DefaultTableModel model;
    LibraryDAO dao = new LibraryDAO();

    public LibraryUI() {
        setTitle("Library Search Engine");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(2, 5));

        titleField = new JTextField();
        authorField = new JTextField();
        genreField = new JTextField();
        isbnField = new JTextField();

        JButton addBtn = new JButton("Add Book");

        top.add(new JLabel("Title"));
        top.add(new JLabel("Author"));
        top.add(new JLabel("Genre"));
        top.add(new JLabel("ISBN"));
        top.add(new JLabel(""));

        top.add(titleField);
        top.add(authorField);
        top.add(genreField);
        top.add(isbnField);
        top.add(addBtn);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID","Title","Author","Genre","ISBN"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");

        bottom.add(searchField);
        bottom.add(searchBtn);
        bottom.add(deleteBtn);
        bottom.add(refreshBtn);

        add(bottom, BorderLayout.SOUTH);

        // Actions
        addBtn.addActionListener(e -> {
            if (titleField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title is required!");
                return;
            }

            dao.addBook(new Book(
                titleField.getText(),
                authorField.getText(),
                genreField.getText(),
                isbnField.getText()
            ));

            JOptionPane.showMessageDialog(this, "Book Added!");
            loadTable(dao.getAllBooks());
        });

        searchBtn.addActionListener(e -> {
            loadTable(dao.searchBooks(searchField.getText()));
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = (int) model.getValueAt(row, 0);
                dao.deleteBook(id);
                JOptionPane.showMessageDialog(this, "Book Deleted!");
                loadTable(dao.getAllBooks());
            } else {
                JOptionPane.showMessageDialog(this, "Select a book first!");
            }
        });

        refreshBtn.addActionListener(e -> loadTable(dao.getAllBooks()));

        loadTable(dao.getAllBooks());
    }

    void loadTable(List<Book> books) {
        model.setRowCount(0);
        for (Book b : books) {
            model.addRow(new Object[]{
                b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getIsbn()
            });
        }
    }
}