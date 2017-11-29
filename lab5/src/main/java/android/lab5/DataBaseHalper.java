package android.lab5;

import java.util.List;

public interface DataBaseHalper {

    void addUser(User user);
    User getUserByUsername(String username);
    void updateUser(User user);
    List<User> getUserList();
    void deleteUsers();
}
