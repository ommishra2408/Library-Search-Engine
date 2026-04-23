package library;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibraryUI extends JFrame {
    private LibraryDAO dao = new LibraryDAO();
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField idField, titleField, authorField, genreField, yearField;
    private JCheckBox availableBox;

    public LibraryUI() {
        setTitle("Library Management Pro");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout
        JPanel contentPane = new JPanel(new BorderLayout(20, 20));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(245, 246, 250));
        setContentPane(contentPane);

        // --- Search Panel ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JComboBox<String> searchBy = new JComboBox<>(new String[]{"Title", "Author", "Genre"});
        JButton btnSearch = new JButton("Search");
        JButton btnShowAll = new JButton("Show All");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBy);
        searchPanel.add(btnSearch);
        searchPanel.add(btnShowAll);
        contentPane.add(searchPanel, BorderLayout.NORTH);

        // --- Sidebar (Form) ---
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setOpaque(false);

        JPanel form = new JPanel(new GridLayout(0, 1, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Book Management"));
        form.setOpaque(false);

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        genreField = new JTextField();
        yearField = new JTextField();
        availableBox = new JCheckBox("Available for Issue", true);

        form.add(new JLabel("Book ID:")); form.add(idField);
        form.add(new JLabel("Title:")); form.add(titleField);
        form.add(new JLabel("Author:")); form.add(authorField);
        form.add(new JLabel("Genre:")); form.add(genreField);
        form.add(new JLabel("Year:")); form.add(yearField);
        form.add(availableBox);

        // Buttons
        JButton btnAdd = new JButton("Add Book");
        JButton btnUpdate = new JButton("Update Selected");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnExport = new JButton("Export to CSV");
        JButton btnClear = new JButton("Clear Form");
        
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        sidebar.add(form);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(btnAdd);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnUpdate);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnDelete);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnExport);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnClear);

        // --- Center (Table) ---
        String[] cols = {"ID", "Title", "Author", "Genre", "Year", "Status"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        bookTable = new JTable(tableModel);
        bookTable.setRowHeight(30);
        bookTable.setSelectionBackground(new Color(52, 152, 219));
        bookTable.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(sidebar, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // --- Listeners ---
        btnAdd.addActionListener(e -> {
            Book book = getFromFields();
            if (book != null) {
                dao.addBook(book);
                refreshData(dao.getAllBooks());
                clearForm();
            }
        });

        btnUpdate.addActionListener(e -> {
            dao.updateBook(getFromFields());
            refreshData(dao.getAllBooks());
        });

        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            dao.deleteBook(id);
            refreshData(dao.getAllBooks());
        });

        btnExport.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    ExportUtility.exportToCSV(dao.getAllBooks(), chooser.getSelectedFile().getAbsolutePath() + ".csv");
                    JOptionPane.showMessageDialog(this, "Export Successful!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Export Failed: " + ex.getMessage());
                }
            }
        });

        btnSearch.addActionListener(e -> {
            String query = searchField.getText().trim();
            String category = (String) searchBy.getSelectedItem();
            List<Book> results;
            if (query.isEmpty()) {
                results = dao.getAllBooks();
            } else {
                if ("Title".equals(category)) {
                    results = dao.searchBooks(query, null, null, null);
                } else if ("Author".equals(category)) {
                    results = dao.searchBooks(null, query, null, null);
                } else {
                    results = dao.searchBooks(null, null, query, null);
                }
            }
            refreshData(results);
        });

        btnShowAll.addActionListener(e -> refreshData(dao.getAllBooks()));

        btnClear.addActionListener(e -> clearForm());

        refreshData(dao.getAllBooks());
        dao.addSampleBooks();
        refreshData(dao.getAllBooks());
    }

    private void refreshData(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book b : books) {
            tableModel.addRow(new Object[]{
                b.getBookId(), b.getTitle(), b.getAuthor(), 
                b.getGenre(), b.getYear(), b.isAvailability() ? "Available" : "Issued"
            });
        }
    }

    private void fillFormFromTable() {
        int row = bookTable.getSelectedRow();
        if (row != -1) {
            idField.setText(tableModel.getValueAt(row, 0).toString());
            titleField.setText(tableModel.getValueAt(row, 1).toString());
            authorField.setText(tableModel.getValueAt(row, 2).toString());
            genreField.setText(tableModel.getValueAt(row, 3).toString());
            yearField.setText(tableModel.getValueAt(row, 4).toString());
            availableBox.setSelected(tableModel.getValueAt(row, 5).equals("Available"));
            idField.setEditable(false);
        }
    }

    private Book getFromFields() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String genre = genreField.getText().trim();
        String idText = idField.getText().trim();
        String yearText = yearField.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty.");
            return null;
        }
        if (author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Author cannot be empty.");
            return null;
        }
        if (genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Genre cannot be empty.");
            return null;
        }
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Book ID cannot be empty.");
            return null;
        }
        if (yearText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Year cannot be empty.");
            return null;
        }

        try {
            int id = Integer.parseInt(idText);
            int year = Integer.parseInt(yearText);
            return new Book(id, title, author, genre, year, availableBox.isSelected());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID and Year must be valid numbers.");
            return null;
        }
    }

    private void clearForm() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        yearField.setText("");
        availableBox.setSelected(true);
        idField.setEditable(true);
        bookTable.clearSelection();
    }
}