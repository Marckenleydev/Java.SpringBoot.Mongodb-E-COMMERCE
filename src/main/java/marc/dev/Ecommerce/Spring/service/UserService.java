package marc.dev.Ecommerce.Spring.service;

import lombok.Data;
import marc.dev.Ecommerce.Spring.entities.User;
import marc.dev.Ecommerce.Spring.exception.AuthenticationException;
import marc.dev.Ecommerce.Spring.repository.USerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Data
@Service
public class UserService {
    @Autowired
    private USerRepository userRepository;

    public User createUser(User user){
        String email = user.getEmail();
        User existingUser = userRepository.findByEmail(email);

        if(existingUser !=null)
            throw new IllegalArgumentException("email already exist");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.insert(user);
    }

    public User authUser(String email, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(email);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        throw new AuthenticationException("Wrong Email/Password");

    }
    public User updateUser(ObjectId userId, User userInfo){
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            if(userInfo.getName() != null){
                user.setName(userInfo.getName());
            }
            if(userInfo.getEmail() != null){
                user.setEmail(userInfo.getEmail());
            }

            if(userInfo.getPhone() != null){
                user.setPhone(userInfo.getPhone());
            }
            if(userInfo.getRole() != null){
                user.setRole(userInfo.getRole());
            }
            return userRepository.save(user);
        }
        return null;
    }

    public User getUser(ObjectId userId){
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
}
