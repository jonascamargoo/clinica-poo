package controllers;


import java.util.List;

import Model.User;
import View.UserView;
import enums.Type;
import exceptions.UsuarioNaoEncontradoException;
import repositories.IUser;

public class UserController {

    private UserView userView;
    private IUser userRepository;

    // Faz sentido injecao de dependencia utilizando apenas o IUser? Isolando o user repository apenas nos metodos

    public UserController(IUser userRepository) {
        userView = new UserView();
        this.userRepository = userRepository;
        this.init();
    }

    public void add() {
        User user = userView.userRead();
        userRepository.add(user);
    }

    public void remove() {
        long id = userView.userDelete();
        try {
            userRepository.delete(id);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }  
    }

    public User findUser(long id) {
        return userRepository.findById(id);
    }

    public void update() {
        User updatedUser = userView.userUpdate();
        try {
            userRepository.update(updatedUser);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }
    }

    public List<User> list() {
        return userRepository.list();
    }

    public boolean userExists(long id) {
        return userRepository.userExists(id);
    }

    public boolean delete() {
        long id = userView.userDelete();
        try {
            userRepository.delete(id);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void init() {
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Date data = new Date();

        // USUARIOS
        // Usuario 1 vladvostok - médico

        User u1 = User.getInstance("vladvostok v", "vlad123", "vladSenha",
                Type.DOCTOR).orElseThrow();
        userRepository.add(u1);

        // Usuario 2 salim - assistente

        User u2 = User.getInstance("Salim s", "salim123", "salimSenha",
                Type.ASSISTANT).orElseThrow();
        userRepository.add(u2);
    }
}
