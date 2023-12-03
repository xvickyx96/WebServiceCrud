package WebServiceCRUDSec.Controller;

import WebServiceCRUDSec.Models.Book;
import WebServiceCRUDSec.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auth/books")
@RequiredArgsConstructor
public class BookController {

    final private BookService bookService;


    // Add book
    @PostMapping("")
    public ResponseEntity<Book> addBook(
            @RequestBody Book book
    ){
        Book newBook = bookService.saveBook(book);

        return ResponseEntity.ok(newBook);
    }

    // Get all books
    @GetMapping("")
    public ResponseEntity<List<Book>> getBooks () {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get book with ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(
            @PathVariable Long id
    ) {

        Optional<Book> book = bookService.getBookById(id);

        return ResponseEntity.ok(book.orElse(null));
    }



}

