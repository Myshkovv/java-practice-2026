package Task4.ru.itis.shop.user.infrastructure.persistence;

import Task4.ru.itis.shop.user.domain.User;
import Task4.ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.fileName = fileName;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            String line = reader.readLine();

            while (line != null) {

                User user = userMapper.fromLine(line);

                if (user.getEmail().equals(email)) {
                    return Optional.of(user);
                }

                line = reader.readLine();
            }

            return Optional.empty();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                User user = userMapper.fromLine(line);

                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }

                line = reader.readLine();
            }

            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateProfileDescription(String email, String profileDescription) {
        Path path = Paths.get(fileName);

        try {
            List<String> updatedLines = Files.lines(path)
                    .map(line -> {
                        User user = userMapper.fromLine(line);
                        if (user.getEmail().equals(email)) {
                            user.setProfileDescription(profileDescription);
                            return userMapper.toLine(user);
                        }
                        return line;
                    })
                    .collect(Collectors.toList());

            Files.write(path, updatedLines);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }


}
