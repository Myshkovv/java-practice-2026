package Task4.ru.itis.shop.user.infrastructure.persistence;

import Task4.ru.itis.shop.user.domain.User;
import Task4.ru.itis.shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final UserMapper userMapper;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty2007";

    public UserRepositoryJdbcImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void updateProfileDescription(String email, String profileDescription) {

    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")){
                    while (resultSet.next()){
                        User user = userMapper.fromLine(resultSet.getString("id") +
                                "|" + resultSet.getString("name") +
                                "|" + resultSet.getString("email") +
                                "|" + resultSet.getString("password") +
                                "|" + resultSet.getString("profiledescription"));
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return userList;
    }
}
