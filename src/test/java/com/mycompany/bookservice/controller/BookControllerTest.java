package com.mycompany.bookservice.controller;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController; //Mockito is going to create a proxy object of BookController and inject it to BookController test file

    @Mock
    private BookService bookService;


    @Test
    @DisplayName("Test Success Scenario for add new Book")
    void testAddBook(){

        BookDTO dto =new BookDTO();
        dto.setName("Dummy");
        BookDTO addsBook = new BookDTO();
        addsBook.setBookId(1L);


        Mockito.when(bookService.addBook(dto)).thenReturn(addsBook);
        ResponseEntity<BookDTO> responseEntity = bookController.addBook(dto);
        Assertions.assertNotNull(responseEntity.getBody().getBookId());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());

    }
    @Test
    @DisplayName("test scenario to get a book")
    public void getABookTest()
    {
        BookDTO book=new BookDTO();
        book.setBookId(1L);
        Long bookId=1L;

        Mockito.when(bookService.getBook(bookId)).thenReturn(book);
        ResponseEntity<BookDTO> responseEntity=bookController.getBook(bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1L,responseEntity.getBody().getBookId());

    }


    @Test
    @DisplayName("Test Success Scenario for fetching all Book")
    void testGetAllBooks(){
        List<BookDTO> bookList = new ArrayList<>();
        BookDTO dto = new BookDTO();
        dto.setBookId(1L);
        dto.setName("Dummy Book");
        bookList.add(dto);

        //Do not make the actual call for propertyService.saveProperty() inside controller rather return dummy List<propertyDTO> in the controller
        Mockito.when(bookService.getAllBook()).thenReturn(bookList);

        ResponseEntity<List<BookDTO>> responseEntity = bookController.getAllBook();
        assertEquals(1,responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName("Test Success Scenario for Updating only price of the Property")

    void testUpdateBookPrice(){

        BookDTO dto = new BookDTO();
        dto.setPricePerQty(67675.55);
        Mockito.when(bookService.updateBookPrice(Mockito.any(), Mockito.anyLong())).thenReturn(dto);
        ResponseEntity<BookDTO> responseEntity = bookController.updateBookPrice(dto, 1L);
        assertEquals(67675.55,responseEntity.getBody().getPricePerQty());
        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName("test scenario to update a book")
    public void testUpdateABook()
    {
        BookDTO updatebook=new BookDTO();
        updatebook.setBookId(1L);
        Long bookId=1L;

        Mockito.when(bookService.updateBook(Mockito.any(), Mockito.any())).thenReturn(updatebook);
        ResponseEntity<BookDTO> responseEntity=bookController.updateBook(updatebook, bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1L,responseEntity.getBody().getBookId());

    }
    @Test
    @DisplayName("test scenario to update a book Available quantity")
    public void testUpdateBookAvailableQuantity()
    {
        BookDTO updatebook=new BookDTO();
        updatebook.setBookId(1L);

        updatebook.setAvailableQty(67.9);
        Long bookId=1L;

        Mockito.when(bookService.updateBookAvailableQuantity(Mockito.any())).thenReturn("sucess");
        ResponseEntity<String> responseEntity=bookController.updateBookAvailableQuantity(updatebook,bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

        Assertions.assertEquals("sucess",responseEntity.getBody());

    }

}
