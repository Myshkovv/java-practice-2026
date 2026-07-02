package Task2.ru.itis.shop.user.repository;

import Task2.ru.itis.shop.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    void updateProfileDescription(String email, String profileDescription);
}
