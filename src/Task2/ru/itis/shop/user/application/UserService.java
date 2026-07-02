package Task2.ru.itis.shop.user.application;

import Task2.ru.itis.shop.user.domain.User;
import Task2.ru.itis.shop.user.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }

    public void signUp(String email, String password, String profileDescription) {
        User user = new User(email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public boolean emailFound(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.isPresent();
    }

    public void updateProfileDescription(String email, String profileDescription){
        userRepository.updateProfileDescription(email, profileDescription);
    }
}
