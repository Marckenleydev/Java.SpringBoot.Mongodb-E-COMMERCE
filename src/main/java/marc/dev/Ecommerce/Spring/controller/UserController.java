package marc.dev.Ecommerce.Spring.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import marc.dev.Ecommerce.Spring.entities.User;
import marc.dev.Ecommerce.Spring.service.UserService;
import marc.dev.Ecommerce.Spring.utils.constants;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(generateJWTToken(newUser), HttpStatus.OK);
    }
    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestBody Map<String,String> userMap){
        String email = userMap.get("email");
        String password = userMap.get("password");

        User authUser = userService.authUser(email,password);
        if(authUser != null){
            return new ResponseEntity<>(generateJWTToken(authUser), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable ObjectId userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity <List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);

    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId userId, @RequestBody User userUpdate){
        User user = userService.updateUser(userId, userUpdate);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, constants.API_SECRET_KEY)
                .setIssuedAt(new Date((timestamp)))
                .setExpiration(new Date(timestamp + constants.TOKEN_VALIDITY))
                .claim("userId",user.getUserId())
                .claim("email",user.getEmail())
                .claim("name",user.getName())
                .claim("role",user.getRole())
                .claim("phone",user.getPhone())
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token:",token);
        return map;

    }
}
