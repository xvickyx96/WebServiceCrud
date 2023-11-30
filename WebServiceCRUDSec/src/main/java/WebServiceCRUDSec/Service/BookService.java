package WebServiceCRUDSec.Service;


import WebServiceCRUDSec.Models.Book;
import WebServiceCRUDSec.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        //Spara book till DB
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book updateBook(Long id, Book updatedBook) {
        // Kontrollera om boken med angivet id existerar
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            // Uppdatera befintlig bok med nya uppgifter
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            // Boken finns inte
            throw new IllegalArgumentException("Book with id " + id + " not found");
        }
    }

    public boolean deleteBook(Long id) {
        // Kontrollera om boken med angivet id existerar
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            // Ta bort boken
            bookRepository.deleteById(id);
            return true;
        } else {
            // Boken finns inte
            return false;
        }
    }
}