package com.mycompany.bookservice.service;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.entity.BookEntity;
import com.mycompany.bookservice.repository.BookRepository;
import com.mycompany.bookservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension .class)
public class BookServiceImplTest {
    @ExtendWith(MockitoExtension .class)

    @InjectMocks
        private BookServiceImpl bookServiceImpl;

        @Mock
        private BookRepository bookRepository;

    @Test
    void testAddBook(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(1L);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        BookDTO dto= bookServiceImpl.addBook(bookDTO);
        Assertions.assertEquals(1L,dto.getBookId());


    }


    @Test
    void testUpdateBookPrice(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);
        BookDTO bookDTO=new BookDTO();
        bookDTO.setPricePerQty(565.0);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(bookEntity));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        bookDTO=bookServiceImpl.updateBookPrice(bookDTO,1L);
        Assertions.assertEquals(1L,bookDTO.getBookId());

    }

    @Test
    void testGetAllBook(){
        List<BookDTO> bookDTOList=new ArrayList<>();
        BookDTO bookDtO=new BookDTO();
        bookDTOList.add(bookDtO);
        BookEntity be=new BookEntity();
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(be));
        bookDTOList=bookServiceImpl.getAllBook();
        Assertions.assertEquals(1,bookDTOList.size());

    }
    @Test
    void testGetBook(){
        BookEntity be=new BookEntity();
        be.setBookId(1L);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(be));
        BookDTO bookDTO=bookServiceImpl.getBook(1L);
        Assertions.assertEquals(1L,bookDTO.getBookId());
    }
    @Test
    void testUpdateBookAvailableQuantity(){
        Long bookId=1L;
        BookEntity be=new BookEntity();
        be.setAvailableQty(435.0);
        BookEntity newbe=new BookEntity();
        newbe.setAvailableQty(be.getAvailableQty()-1);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(be));
        Mockito.when(bookRepository.save(be)).thenReturn(newbe);
        String result=bookServiceImpl.updateBookAvailableQuantity(bookId);
        Assertions.assertEquals("updated",result);
    }

    @Test
    void testUpdateBook(){
        Long bookId=1L;
        BookEntity be=new BookEntity();
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setName("Rocky");
        bookDTO.setAvailableQty(45.0);
        bookDTO.setPricePerQty(323.3);
        bookDTO.setAuthorEmail("bbbb");
        bookDTO.setAuthorName("ads");
        bookDTO.setDescription("SFASDF");
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(be));
        Mockito.when(bookRepository.save(be)).thenReturn(be);
        bookDTO=bookServiceImpl.updateBook(bookDTO,bookId);
        Assertions.assertEquals("Rocky",bookDTO.getName());


    }

}

