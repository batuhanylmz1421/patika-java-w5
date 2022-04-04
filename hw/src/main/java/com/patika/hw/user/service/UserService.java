package com.patika.hw.user.service;

import com.patika.hw.user.model.User;
import com.patika.hw.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newUser) {
        return userDao.updateUserById(id, newUser);
    }

    public int updateUserBalance(UUID id, Double newBalance) {
        return userDao.updateUserBalanceById(id, newBalance);
    }

    public int sendUserMoney(UUID senderId, UUID receiverId, Double amount) {
        return userDao.sendUserMoney(senderId, receiverId, amount);
    }
}
