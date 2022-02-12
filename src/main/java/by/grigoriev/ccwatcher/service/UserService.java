package by.grigoriev.ccwatcher.service;

import by.grigoriev.ccwatcher.service.notify.User;

import java.util.List;

public interface UserService {
    void addUser(String name, String symbol, double price);
    List<User> getUsers();
    boolean removeUser(User user);




}
