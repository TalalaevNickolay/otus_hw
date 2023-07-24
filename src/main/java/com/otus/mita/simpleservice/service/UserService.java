package com.otus.mita.simpleservice.service;

import com.otus.mita.simpleservice.dto.UserDTO;
import com.otus.mita.simpleservice.model.User;
import com.otus.mita.simpleservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@Transactional(readOnly = true)
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUser(UserDTO userDto, int id) throws DataFormatException {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhone(userDto.getPhone());
            user.setUsername(userDto.getUserName());
            userRepository.flush();
        } else throw new DataFormatException();
    }

    @Transactional
    public void createUser(UserDTO userDto){
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPhone(userDto.getPhone());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id) throws DataFormatException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
        else throw new DataFormatException();
    }

}
