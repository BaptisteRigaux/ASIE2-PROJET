package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.User;
import td.ecommerce.repository.User_Repository;
import td.ecommerce.service.User_Service;

import java.util.List;

@Service
public class userServiceImpl implements User_Service {
    private User_Repository userRepository;

    @Autowired
    public userServiceImpl(User_Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User persistUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
}
