package WebServiceCRUDSec.Connection;

import WebServiceCRUDSec.Models.Book;
import WebServiceCRUDSec.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class Endpoints {

    private final BookService bookService;

    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }


// Update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookService.getBookById(id);

        if (existingBook.isPresent()) {
            Book savedBook = bookService.updateBook(id, updatedBook);


            return ResponseEntity.ok(savedBook);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    // Delete book

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

