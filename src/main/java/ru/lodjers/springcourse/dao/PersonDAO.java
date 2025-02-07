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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }
    public Person show(int id) throws SQLException {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                        new PersonMapper()).stream().findAny().orElse(null);
    }
    public List<Book> booksOfPerson(int id) throws SQLException {
        return jdbcTemplate.query("SELECT * " +
                        "FROM Person JOIN book on person.person_id = book.person_id WHERE person.person_id = ?",
                new Object[]{id}, new BookMapper());
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(FIO, yearOfBirth) VALUES(?, ?)",
                person.getFIO(), person.getYearOfBirth());
    }
    public void update(int id, Person updatedPerson) throws SQLException {
        jdbcTemplate.update("UPDATE Person SET FIO=?, yearOfBirth=? WHERE person_id=?",
                updatedPerson.getFIO(), updatedPerson.getYearOfBirth(), id);
    }
    public void delete(int id) throws SQLException {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }


}
