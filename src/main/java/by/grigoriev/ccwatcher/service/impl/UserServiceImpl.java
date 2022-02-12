package by.grigoriev.ccwatcher.service.impl;

import by.grigoriev.ccwatcher.service.UserService;
import by.grigoriev.ccwatcher.service.notify.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();

    public void addUser(String name, String symbol, double price) {
        users.add(new User(name, symbol, price));
        log.info("User {} has been added, fixed price: {}", name, price);
    }

    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public boolean removeUser(User user) {
        return users.remove(user);
    }

}
