package Task1.ru.itis.shop.app;

import Task1.ru.itis.shop.user.api.UserConsoleOperations;
import Task1.ru.itis.shop.user.infrastructure.persistence.UserDatabaseRepository;
import Task1.ru.itis.shop.user.infrastructure.persistence.UserFileRepository;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("user.txt");
        UserDatabaseRepository userDatabaseRepository = new UserDatabaseRepository();
        UserConsoleOperations operations = new UserConsoleOperations(userFileRepository);

        while (true) {
            operations.showMenu();
        }
    }
}
