package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProfileDescription());
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

    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
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

    public List<User> findAllByProfileDescription(String profileDescription){
        return userRepository.findAllByProfileDescription(profileDescription);
    }

}
