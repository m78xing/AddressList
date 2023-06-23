package com.Dao;

import com.entity.Person;
import com.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl extends BaseDao implements PersonDao{
    @Override
    public int delPerson(int id) throws SQLException {
        String sql="delete from person where id=?";
        return this.modifyData(sql,new Object[] {id});
    }

    @Override
    public Person getPerson(int id) throws SQLException {
        String sql="select * from person where id=?";
        ResultSet rs=this.getDataByAny(sql,new Object[] {id});
        Person p=new Person();
        while(rs.next()){
            p.setName(rs.getString(1));
            p.setGender(rs.getString(2));
            p.setAge(rs.getInt(3));
            p.setTel(rs.getString(4));
            p.setId(rs.getInt(5));
        }
        rs.close();
        return p;
    }

    @Override
    public int updatePerson(Person p) throws SQLException {
        String sql="update person set name=?,gender=?,age=?,tel=? where id=?";
        Object[] obj={p.getName(),p.getGender(),p.getAge(),p.getTel(),p.getId()};
        return this.modifyData(sql,obj);
    }

    @Override
    public List<Person> getAllPerson() throws SQLException {
        String sql="select * from person";
        ResultSet rs=this.getDataByAny(sql,new Object[] {});
        List<Person> personList=new ArrayList<Person>();
        while(rs.next()){
            Person p=new Person();
            p.setName(rs.getString(1));
            p.setGender(rs.getString(2));
            p.setAge(rs.getInt(3));
            p.setTel(rs.getString(4));
            p.setId(rs.getInt(5));
            personList.add(p);
        }
        rs.close();
        return personList;
    }

    @Override
    public int insertPerson(Person p) throws SQLException {
        String sql="insert into person(name,gender,age,tel) value(?,?,?,?)";
        Object[] obj={p.getName(),p.getGender(),p.getAge(),p.getTel()};
        return this.modifyData(sql,obj);
    }

    @Override
    public List<Person> getPersonFuzzy(String fuzzyName) throws SQLException {
        String sql="select * from person where name like ?";
        ResultSet rs=this.getDataByAny(sql,new Object[]{"%"+fuzzyName+"%"});
        System.out.println(fuzzyName);
        List<Person> personList=new ArrayList<Person>();
        while(rs.next()){
            Person p=new Person();
            p.setName(rs.getString(1));
            p.setGender(rs.getString(2));
            p.setAge(rs.getInt(3));
            p.setTel(rs.getString(4));
            p.setId(rs.getInt(5));
            personList.add(p);
        }
        rs.close();
        return personList;
    }
}
