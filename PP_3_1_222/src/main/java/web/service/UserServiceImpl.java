package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.model.MyUser;
import web.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MyUser passwordCoder(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUser;
    }

    public List<MyUser> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public MyUser getUserById(int id) {
        return userRepository.getById(id);
    }

    public void addUser(MyUser myUser) {
        userRepository.save(passwordCoder(myUser));
    }

    @Override
    public void updateUser(MyUser myUser) {
        MyUser myUserToBeUpdated = userRepository.getById(myUser.getId());
        myUserToBeUpdated.setUsername(myUser.getUsername());
        myUserToBeUpdated.setSurname(myUser.getSurname());
        myUserToBeUpdated.setAge(myUser.getAge());
        myUserToBeUpdated.setRoles(myUser.getRoles());
        userRepository.flush();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}