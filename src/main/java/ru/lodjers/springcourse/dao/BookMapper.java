package ru.lodjers.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.lodjers.springcourse.models.Book;
import ru.lodjers.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setNameOfBook(resultSet.getString("nameOfBook"));
        book.setAutor(resultSet.getString("autor"));
        book.setYear(resultSet.getInt("year"));
        return book;
    }
}
