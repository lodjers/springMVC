package ru.lodjers.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lodjers.springcourse.models.Book;
import ru.lodjers.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }
    public Book show(int id) throws SQLException {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                        new BookMapper()).stream().findAny().orElse(null);
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(autor, year, nameOfBook) VALUES(?, ?, ?)",
                book.getAutor(), book.getYear(), book.getNameOfBook());
    }
    public void update(int id, Book updatedBook) throws SQLException {
        jdbcTemplate.update("UPDATE Book SET autor=?, year=?, nameOfBook=? WHERE book_id=?",
                updatedBook.getAutor(), updatedBook.getYear(), updatedBook.getNameOfBook(), id);
    }
    public void delete(int id) throws SQLException {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
    public Person checkBookToPerson(int id) throws SQLException {
        return jdbcTemplate.query("SELECT * " +
                "FROM Person JOIN book on person.person_id = book.person_id WHERE book.book_id = ?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }
    public void add(int person_id, int book_id) throws SQLException {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person_id, book_id);
    }
    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE book_id=?", id);
    }
}
