package Task4.ru.itis.shop.user.application;

import Task4.ru.itis.shop.user.domain.User;
import Task4.ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
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

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
