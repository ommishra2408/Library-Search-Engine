public class Book {
    private int id;
    private String title, author, genre, isbn;

    public Book(int id, String title, String author, String genre, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Book(String title, String author, String genre, String isbn) {
        this(0, title, author, genre, isbn);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getIsbn() { return isbn; }
}