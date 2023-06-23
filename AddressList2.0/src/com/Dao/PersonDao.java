package com.Dao;

import com.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    public int delPerson(int id) throws SQLException;
    public Person getPerson(int id) throws SQLException;
    public int updatePerson(Person p) throws SQLException;
    public List<Person> getAllPerson() throws SQLException;
    public int insertPerson(Person p) throws SQLException;
    public List<Person> getPersonFuzzy(String condition) throws SQLException;
}
