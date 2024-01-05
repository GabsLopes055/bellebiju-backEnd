package com.belleBiju.service;

import com.belleBiju.DTOs.requests.UserRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.repository.UserRepository;
import com.belleBiju.security.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.belleBiju.service.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EncryptPassword encode;


    /* Metodo para salvar um usuario */
    public UserResponse SaveNewUser(UserRequest request) {

        User user = new User();

        user.setNome(request.getNome());
        user.setUsername(request.getUsername());
        user.setPassword(encode.EncryptPassword().encode(request.getPassword())); // criptografando a senha do usuario
        user.setRoles(request.getRoles());

        repository.save(user);

        return new UserResponse(user);

    }


    /*Listar todos os usuários*/
    public List<UserResponse> ListAllUsers() {

        /*lista de usuarios*/
        List<User> listUsers = repository.findAll();

//       for(User user: listUsers) {
//           UserResponse userResponse = new UserResponse(user);
//           listResponse.add(userResponse);
//       }

        return listUsers.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    /*Editar usuario*/
    public UserResponse editUser(UserRequest request, UUID uuid) {

        Optional<User> findUser = Optional.ofNullable(repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Usuário não encontrado para o ID: " + uuid)));

        findUser.get().setNome(request.getNome());
        findUser.get().setUsername(request.getUsername());
        findUser.get().setRoles(request.getRoles());

        repository.save(findUser.get());

        return new UserResponse(findUser.get());

    }

    /*Metodo para excluir um usuario*/
    public boolean deletarUsuario(UUID idUser) {
        Optional<User> findUser = Optional.ofNullable(repository.findById(idUser).orElseThrow(
                () -> new EntityNotFoundException("Usuário não encontrado para o ID: " + idUser)));

        if(findUser.isPresent()){
            repository.deleteById(idUser);
            return true;
        } else {
            return false;
        }
    }

}
