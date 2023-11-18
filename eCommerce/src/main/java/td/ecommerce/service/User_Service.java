package td.ecommerce.service;

import td.ecommerce.model.User;
import java.util.List;

public interface User_Service {
    public List<User> getAllUsers();

    public User persistUser(User user);

    public User getUserById(Long id);

    public User updateUser(User user);

    public void deleteUser(Long id);
}