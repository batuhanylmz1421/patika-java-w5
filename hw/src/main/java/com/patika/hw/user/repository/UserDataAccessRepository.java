package com.patika.hw.user.repository;

import com.patika.hw.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userDao")
public class UserDataAccessRepository implements UserDao {
    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getKimlikNo(), user.getName(), user.getPassword()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMaybe = selectUserById(id);
        if (userMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(userMaybe.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, User update) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        DB.set(indexOfUserToUpdate, new User(id, update.getKimlikNo(), update.getName(), update.getPassword()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int updateUserBalanceById(UUID id, Double newBalance) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        user.setBalance(newBalance);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int sendUserMoney(UUID senderId, UUID receiverId, Double amount) {
        List<User> userList = selectAllUsers();
        User senderUser = null;
        User receiverUser = null;

        // Getting users
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(senderId)) {
                senderUser = userList.get(i);
            } else if (userList.get(i).getId().equals(receiverId)) {
                receiverUser = userList.get(i);
            }
        }
        // Validation
        if (senderUser != null && receiverUser != null && senderUser.getBalance() >= amount) {
            updateUserBalanceById(senderId, senderUser.getBalance() - amount);
            updateUserBalanceById(receiverId, receiverUser.getBalance() + amount);
            return 1;
        }

        System.out.println("Money could not sended!");
        return 0;
    }
}
