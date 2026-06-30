package Task1.ru.itis.shop.user.repository;

import Task1.ru.itis.shop.user.domain.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
