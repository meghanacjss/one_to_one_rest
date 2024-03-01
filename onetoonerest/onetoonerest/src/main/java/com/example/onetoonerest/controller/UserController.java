package com.example.onetoonerest.controller;

import com.example.onetoonerest.entity.User;
import com.example.onetoonerest.entity.UserProfile;
import com.example.onetoonerest.repository.UserProfileRepository;
import com.example.onetoonerest.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
   private UserProfileRepository userProfileRepository;

    @RequestMapping("/")
    public String test(){
        return "welcome";
    }
//    @PostMapping("/userAdd")
//    public String Usersave(@Valid @RequestBody User user){
//        System.out.println(user);
//         userRepository.save(user);
//         return "usersaved";
//    }

//    @PostMapping("/userAdd")
//    public String addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "validationError";
//        }
//        userRepository.save(user);
//        return "userSaved";
//    }

    @PostMapping("/userAdd")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);

    }
    @GetMapping("/getAllUsers")
    public List<User>getAllUsers(){
           return userRepository.findAll();
    }

    @PutMapping("/updateUser/{userId}")
    public User updateUser(@PathVariable int userId,@RequestBody User user){
       User existingUser = userRepository.findById(userId).orElse(null);
       if(existingUser != null){
          existingUser.setName(user.getName());
          existingUser.setGender(user.getGender());
          existingUser.setEmail(user.getEmail());
          return userRepository.save(existingUser);
        }
       return null;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId){
         userRepository.deleteById(userId);
         return "deleted user";
    }


    @PostMapping("/addUserProfile/{userId}")

    public String saveProfile(@RequestBody UserProfile userProfile,@PathVariable("userId") int userId){
        User user=userRepository.getReferenceById(userId);
        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
                return "saved";
    }

    @GetMapping("/getAll")

    public List<UserProfile>getAll(){
        return userProfileRepository.findAll();
    }

    @PutMapping("/updateProfile/{id}")
    public UserProfile updateProfile(@PathVariable int id,@RequestBody UserProfile userProfile){
        UserProfile existingProfile = userProfileRepository.findById(id).orElse(null);
        if(existingProfile != null){
            existingProfile.setPhoneNumber(userProfile.getPhoneNumber());
            existingProfile.setAddress(userProfile.getAddress());
            existingProfile.setGender(userProfile.getGender());
            existingProfile.setDob(userProfile.getDob());

            return userProfileRepository.save(existingProfile);
        }
        return null;
    }

    @DeleteMapping("/deleteProfile/{id}")

    public String deleteProfile(@PathVariable int id){
        userProfileRepository.deleteById(id);
        return "deleted";
    }
}
