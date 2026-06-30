package Task1.ru.itis.shop.user.infrastructure.persistence;

import Task1.ru.itis.shop.user.domain.User;
import Task1.ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split("\\|");
                if (data[0].equals(id)){
                    User user = new User(id, data[1], data[2], data[3]);
                    return user;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
