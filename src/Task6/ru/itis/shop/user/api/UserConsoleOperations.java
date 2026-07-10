package Task6.ru.itis.shop.user.api;

import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.application.UserService;
import Task6.ru.itis.shop.user.domain.User;

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
            case "6": {
                printAllByProfileDescription();
            }
            break;
            case "7": {
                String email = scanner.nextLine();
                UserDto user = userService.getUserByEmail(email);
                System.out.println(user.getId() + " " + user.getProfileDescription() + " ");
            }

            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("7. Показать информацию о пользователя по email");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
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
        Integer id = scanner.nextInt();
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

    private void printAllByProfileDescription(){
        System.out.println("Cейчас будем искать пользователей по описанию профиля");
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();
        List<User> userList = userService.findAllByProfileDescription(profileDescription);
        System.out.println("Найденные пользователи:");
        for (User user : userList){
            System.out.println(user.getName() + " | " + user.getEmail());
        }
    }



}
