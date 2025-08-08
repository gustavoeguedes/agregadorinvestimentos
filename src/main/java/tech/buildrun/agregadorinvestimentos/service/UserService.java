package tech.buildrun.agregadorinvestimentos.service;

import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import tech.buildrun.agregadorinvestimentos.controller.CreateUserDto;
import tech.buildrun.agregadorinvestimentos.controller.UpdateUserDto;
import tech.buildrun.agregadorinvestimentos.entity.User;
import tech.buildrun.agregadorinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

       var entity = new User(
               null,
               createUserDto.username(),
               createUserDto.email(),
               createUserDto.password(),
               null,
               null);


        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();

    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));

    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.findById(id);
        if(userExists.isEmpty()) {
            return;
        }
        userRepository.deleteById(id);
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.findById(id);
        if(userExists.isEmpty()) {
            return;
        }
        var user = userExists.get();

        if(updateUserDto.username() != null) {
            user.setUsername(updateUserDto.username());
        }

        if(updateUserDto.password() != null) {
            user.setPassword(updateUserDto.password());
        }

        userRepository.save(user);
    }
}
