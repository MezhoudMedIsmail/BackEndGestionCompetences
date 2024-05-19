package org.example.backendamine.Service.Impl;


import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Auth.AuthenticationResponse;
import org.example.backendamine.Entities.Auth.RegisterRequest;
import org.example.backendamine.Entities.FileEntity;
import org.example.backendamine.Entities.Response.UserRequest;
import org.example.backendamine.Entities.Response.UserResponse;
import org.example.backendamine.Entities.Role;
import org.example.backendamine.Entities.TypeDepartement;
import org.example.backendamine.Entities.User;
import org.example.backendamine.Repository.FileRepository;
import org.example.backendamine.Repository.UserRepository;
import org.example.backendamine.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileRepository fileRepository;
    @Override
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(long id,User user) {
        User u = userRepository.findById(id).orElseThrow();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setRegion(user.getRegion());
        u.setDepartement(user.getDepartement());
        u.setMatricule(user.getMatricule());
        u.setPhone(user.getPhone());
        User userRes =  userRepository.save(u);
        return UserResponse.builder()
                .id(userRes.getId())
                .firstName(userRes.getFirstName())
                .lastName(userRes.getLastName())
                .email(userRes.getEmail())
                .region(userRes.getRegion())
                .departement(userRes.getDepartement().name())
                .matricule(userRes.getMatricule())
                .phone(userRes.getPhone())
                .build();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        var user = User.builder()
            .email(userRequest.getEmail())
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .region(userRequest.getRegion())
            .departement(userRequest.getDepartement())
            .matricule(userRequest.getMatricule())
            .phone(userRequest.getPhone())
            .status(true)
            .password(passwordEncoder.encode(userRequest.getPassword()))
            .role(Role.USER)
            .build();
        User u = userRepository.save(user);
        return UserResponse.builder()
                .id(u.getId())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .region(u.getRegion())
                .departement(u.getDepartement().name())
                .matricule(u.getMatricule())
                .phone(u.getPhone())
                .role(u.getRole())
                .build();
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public void deleteUser(Long id) {
        FileEntity fileEntity = fileRepository.findByUserId(id).orElseThrow();
        fileRepository.deleteById(fileEntity.getId());
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByMatricule(long matricule) {
        return userRepository.findByMatricule(matricule);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .region(user.getRegion())
                .departement(user.getDepartement().name())
                .matricule(user.getMatricule())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
