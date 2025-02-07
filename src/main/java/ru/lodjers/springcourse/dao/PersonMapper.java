package ru.lodjers.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.lodjers.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("person_id"));
        person.setFIO(resultSet.getString("FIO"));
        person.setYearOfBirth(resultSet.getInt("YearOfBirth"));
        return person;
    }
}
