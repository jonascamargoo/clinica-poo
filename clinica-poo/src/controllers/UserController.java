package controllers;


import java.util.List;

import Model.User;
import View.UserView;
import enums.Type;
import repositories.IUser;

public class UserController {

    private UserView userView;
    private IUser userRepository;

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
        userRepository.delete(id);
       
    }

    public User findUser(long id) {
        return userRepository.findById(id);
    }

    public void update() {
        User updatedUser = userView.userUpdate();
        userRepository.update(updatedUser);
        
    }

    public List<User> list() {
        return userRepository.list();
    }

    public void delete() {
        long id = userView.userDelete();
        userRepository.delete(id);
        
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
