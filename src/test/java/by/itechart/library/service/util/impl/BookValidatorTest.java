package by.itechart.library.service.util.impl;

import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ValidatorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.MockitoAnnotations.initMocks;

public class BookValidatorTest {

    @InjectMocks
    BookValidatorImpl bookValidator;

    @Mock
    BookDAO bookDAO;

    Book book;

    @Before
    public void init() {
        initMocks(this);
        book = new Book();
    }

    @Test
    public void validateAdd() throws ValidatorException {
        book.setId(1);
        book.setTitle("Title");
        book.setAuthors("Пушкин     Лермонтов. Чехов");
        book.setPublisher("Народная");
        book.setPublishDate(LocalDate.now());
        book.setGenres("Драма     Комедия. Биография");
        book.setTotalAmount(1);
        book.setPageCount(1);
        book.setISBN("1234");
        bookValidator.validateAdd(book);
    }

    @Test
    public void validateUpdate() throws ValidatorException {
        book.setId(1);
        book.setTitle("Title");
        book.setAuthors("Пушкин     Лермонтов. Чехов");
        book.setPublisher("Народная");
        book.setPublishDate(LocalDate.now());
        book.setGenres("Драма     Комедия. Биография");
        book.setTotalAmount(1);
        book.setPageCount(1);
        book.setISBN("1234");
        bookValidator.validateUpdate(book);

    }

    @Test(expected = ValidatorException.class)
    public void validateTitle_isNull() throws ValidatorException {
        book.setTitle(null);
        bookValidator.validateTitle(book.getTitle());
    }

    @Test(expected = ValidatorException.class)
    public void validateTitle_isEmpty() throws ValidatorException {
        book.setTitle("");
        bookValidator.validateTitle(book.getTitle());
    }

    @Test(expected = ValidatorException.class)
    public void validateTitle_isLowCase() throws ValidatorException {
        book.setTitle("title");
        bookValidator.validateTitle(book.getTitle());
    }

    @Test
    public void validateTitle_validData() throws ValidatorException {
        book.setTitle("Title");
        bookValidator.validateTitle(book.getTitle());
    }

    @Test(expected = ValidatorException.class)
    public void validateAuthors_isNull() throws ValidatorException {
        book.setAuthors(null);
        bookValidator.validateAuthors(book.getAuthors());
    }

    @Test(expected = ValidatorException.class)
    public void validateAuthors_isEmpty() throws ValidatorException {
        book.setAuthors("");
        bookValidator.validateAuthors(book.getAuthors());
    }

    @Test(expected = ValidatorException.class)
    public void validateAuthors_isLowCase() throws ValidatorException {
        book.setAuthors("Пушкин лермонтов");
        bookValidator.validateAuthors(book.getAuthors());
    }



    @Test
    public void validateAuthors_validData() throws ValidatorException {
        book.setAuthors("Пушкин     Лермонтов. Чехов");
        bookValidator.validateAuthors(book.getAuthors());
    }


    @Test(expected = ValidatorException.class)
    public void validatePublisher_isNull() throws ValidatorException {
        book.setPublisher(null);
        bookValidator.validatePublisher(book.getPublisher());
    }

    @Test(expected = ValidatorException.class)
    public void validatePublisher_isEmpty() throws ValidatorException {
        book.setPublisher("");
        bookValidator.validatePublisher(book.getPublisher());
    }

    @Test(expected = ValidatorException.class)
    public void validatePublisher_isLowCase() throws ValidatorException {
        book.setPublisher("народная");
        bookValidator.validatePublisher(book.getPublisher());
    }

    @Test
    public void validatePublisher_validData() throws ValidatorException {
        book.setPublisher("Народная");
        bookValidator.validatePublisher(book.getPublisher());
    }

    @Test(expected = ValidatorException.class)
    public void validatePublishDate_isNull() throws ValidatorException {
        book.setPublishDate(null);
        bookValidator.validatePublishDate(book.getPublishDate());
    }

    @Test(expected = ValidatorException.class)
    public void validatePublishDate_isAfter() throws ValidatorException {
        book.setPublishDate(LocalDate.now()
                                     .plusDays(1));
        bookValidator.validatePublishDate(book.getPublishDate());
    }

    @Test
    public void validatePublishDate_validData() throws ValidatorException {
        book.setPublishDate(LocalDate.now());
        bookValidator.validatePublishDate(book.getPublishDate());
    }

    @Test(expected = ValidatorException.class)
    public void validateGenres_isNull() throws ValidatorException {
        book.setGenres(null);
        bookValidator.validateGenres(book.getGenres());
    }

    @Test(expected = ValidatorException.class)
    public void validateGenres_isEmpty() throws ValidatorException {
        book.setGenres("");
        bookValidator.validateGenres(book.getGenres());
    }



    @Test
    public void validateGenres_validData() throws ValidatorException {
        book.setGenres("Драма     Комедия. Биография");
        bookValidator.validateGenres(book.getGenres());
    }

    @Test(expected = ValidatorException.class)
    public void validateTotalAmount_isNotInitialized() throws ValidatorException {
        bookValidator.validateTotalAmount(book.getTotalAmount());
    }

    @Test(expected = ValidatorException.class)
    public void validateTotalAmount_isLessThan0() throws ValidatorException {
        book.setTotalAmount(-1);
        bookValidator.validateTotalAmount(book.getTotalAmount());
    }

    @Test
    public void validateTotalAmount_validData() throws ValidatorException {
        book.setTotalAmount(1);
        bookValidator.validateTotalAmount(book.getTotalAmount());
    }

    @Test(expected = ValidatorException.class)
    public void validatePageCount_isNotInitialized() throws ValidatorException {
        bookValidator.validateTotalAmount(book.getTotalAmount());
    }

    @Test(expected = ValidatorException.class)
    public void validatePageCount_isLessThan0() throws ValidatorException {
        book.setPageCount(-1);
        bookValidator.validatePageCount(book.getPageCount());
    }

    @Test
    public void validatePageCount_validData() throws ValidatorException {
        book.setPageCount(1);
        bookValidator.validatePageCount(book.getPageCount());
    }


    @Test(expected = ValidatorException.class)
    public void validateISBNToAdd_isNull() throws ValidatorException {
        book.setISBN(null);
        bookValidator.validateISBNtoAdd(book.getISBN());
    }

    @Test(expected = ValidatorException.class)
    public void validateISBNToAdd_isEmpty() throws ValidatorException {
        book.setISBN("");
        bookValidator.validateISBNtoAdd(book.getISBN());
    }

    @Test(expected = ValidatorException.class)
    public void validateISBNToAdd_isNotUnique() throws ValidatorException, DAOException {
        book.setISBN("1234");
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .checkISBNtoAdd(book.getISBN());
        bookValidator.validateISBNtoAdd(book.getISBN());
    }

    @Test
    public void validateISBNToAdd_validData() throws ValidatorException, DAOException {
        book.setISBN("1234");
        Mockito.doReturn(true)
               .when(bookDAO)
               .checkISBNtoAdd(book.getISBN());
        bookValidator.validateISBNtoAdd(book.getISBN());
    }














    @Test(expected = ValidatorException.class)
    public void validateISBNToUpdate_isNull() throws ValidatorException {
        book.setISBN(null);
        bookValidator.validateISBNtoUpdate(book.getISBN(), 1);
    }

    @Test(expected = ValidatorException.class)
    public void validateISBNToUpdate_isEmpty() throws ValidatorException {
        book.setISBN("");
        bookValidator.validateISBNtoUpdate(book.getISBN(), 1);
    }

    @Test(expected = ValidatorException.class)
    public void validateISBNToUpdate_isNotUnique() throws ValidatorException, DAOException {
        book.setISBN("1234");
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .checkISBNtoUpdate(book.getISBN(), 1);
        bookValidator.validateISBNtoUpdate(book.getISBN(), 1);
    }

    @Test
    public void validateISBNToUpdate_validData() throws ValidatorException, DAOException {
        book.setISBN("1234");
        Mockito.doReturn(true)
               .when(bookDAO)
               .checkISBNtoUpdate(book.getISBN(), 1);
        bookValidator.validateISBNtoUpdate(book.getISBN(), 1);
    }
}