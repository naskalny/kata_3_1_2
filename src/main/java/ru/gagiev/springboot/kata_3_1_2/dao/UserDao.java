package ru.gagiev.springboot.kata_3_1_2.dao;

import ru.gagiev.springboot.kata_3_1_2.model.User;

import java.util.List;

public interface UserDao {
    User getUser(long id);
    List<User> listUsers();
    void addUser(User user);
    void editUser(User user);
    void removeUser(long id);
}
