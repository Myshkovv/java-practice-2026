package Task4.ru.itis.shop.user.api;

import Task4.ru.itis.shop.user.application.UserService;
import Task4.ru.itis.shop.user.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                findUserById();
            }
            break;
            case "4": {
                updateProfileDescription();
            }
            break;
            case "5": {
                printAll();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя по email");
        System.out.println("5. Показать всех пользователей");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void findUserById(){
        System.out.println("Сейчас будем искать пользователя по id");
        System.out.println("Введите id:");
        String id = scanner.nextLine();
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            System.out.println("email пользователя: " + user.getEmail());
        } else {
            System.out.println("Пользователь не найден");
        }

    }

    private void updateProfileDescription() {
        System.out.println("Сейчас будем менять данные пользователя");
        System.out.println("Введите email: ");
        String email = scanner.nextLine();
        if (userService.emailFound(email)) {
            System.out.println("Введите новые данные: ");
            String profileDescription = scanner.nextLine();
            userService.updateProfileDescription(email, profileDescription);
            System.out.println("Профиль пользователя успешно обновлен");
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void printAll(){
        System.out.println("Вывод всех пользователей: ");
        List<User> userList= userService.findAll();
        for (User user : userList){
            System.out.println(user.getName() + "|" + user.getEmail());
        }
    }

}
