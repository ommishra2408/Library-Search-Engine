package library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private LibraryDAO dao;

    @GetMapping
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) String genre) {
        return dao.searchBooks(title, author, genre, null);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        dao.addBook(book);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        dao.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        dao.deleteBook(id);
    }
}