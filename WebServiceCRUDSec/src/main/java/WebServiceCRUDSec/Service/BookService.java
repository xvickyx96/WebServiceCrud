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

    //Save to DB
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }
    // Get all books from DB
    public List<Book> getAllBooks()
    {

        return bookRepository.findAll();
    }

    // Get a book with ID
    public Optional<Book> getBookById(Long id)
    {

        return bookRepository.findById(id);
    }


    // Update book with ID
    public Book updateBook(Long id, Book updatedBook) {
        // Control if a book exist with ID
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            // Update book with new info
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            // The book does not exist
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