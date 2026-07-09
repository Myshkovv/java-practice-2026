package Task4.ru.itis.shop.app;

import Task4.ru.itis.shop.user.api.UserConsoleOperations;
import Task4.ru.itis.shop.user.application.UserService;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserMapper;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {
        UserRepositoryJdbcImpl userRepositoryJdbc = new UserRepositoryJdbcImpl(new UserMapper());
        UserService userService = new UserService(userRepositoryJdbc);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
