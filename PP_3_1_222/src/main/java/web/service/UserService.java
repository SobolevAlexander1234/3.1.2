package web.service;

import web.model.MyUser;

import java.util.List;

public interface UserService {
    List<MyUser> allUsers ();
    MyUser getUserById (int id);
    void addUser (MyUser myUser);
    void deleteUserById (int id);
    void updateUser (MyUser myUser);
    MyUser passwordCoder(MyUser myUser);
}