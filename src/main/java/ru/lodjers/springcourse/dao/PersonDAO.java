package ru.lodjers.springcourse.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.lodjers.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", "tom@gmail.com", 24));
        people.add(new Person(++PEOPLE_COUNT, "Bob", "bob@gmail.com", 13));
        people.add(new Person(++PEOPLE_COUNT, "Mike", "mike@gmail.com", 56));
        people.add(new Person(++PEOPLE_COUNT, "Katy", "katy@gmail.com", 18));
    }
    public List<Person> index() {
        return people;
    }
    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAge(updatedPerson.getAge());
    }
    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
