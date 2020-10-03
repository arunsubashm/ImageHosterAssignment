package ImageHoster.service;

import ImageHoster.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void registerUser(User newUser) {
        return;
    }

    public boolean login(User user) {
        if (user.getUsername().equals("upgrad") && user.getPassword().equals("password"))
            return true;
        else
            return false;
    }
}
