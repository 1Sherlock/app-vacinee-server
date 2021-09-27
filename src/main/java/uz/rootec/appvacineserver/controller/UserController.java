package uz.rootec.appvacineserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.rootec.appvacineserver.entity.User;
import uz.rootec.appvacineserver.payload.ApiResponse;
import uz.rootec.appvacineserver.payload.ReqSignUpWithRole;
import uz.rootec.appvacineserver.payload.ResUser;
import uz.rootec.appvacineserver.repository.RoleRepository;
import uz.rootec.appvacineserver.repository.UserRepository;
import uz.rootec.appvacineserver.security.CurrentUser;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sherlock on 14.07.2021.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser User currentUser) {
        try {
            return ResponseEntity.ok(new ResUser(
                    currentUser.getId(),
                    currentUser.getPhoneNumber(),
                    currentUser.getLastName(),
                    currentUser.getFirstName(),
                    currentUser.getRoles()
//                    currentUser.getEmail()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
        try {
            List<User> all = userRepository.findAll();
            return ResponseEntity.ok(new ApiResponse(true, "success", all));
        } catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }


//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_DIRECTOR')")
    @PostMapping("/createWorker")
    public ResponseEntity<?> createWorker(@Valid @RequestBody ReqSignUpWithRole reqSignUp){
        try {
            Optional<User> optionalUser = userRepository.findByPhoneNumber(reqSignUp.getPhoneNumber());

            if (optionalUser.isPresent()) {
                return ResponseEntity.ok(new ApiResponse(false, "Bunday ishchi ro'yxatdan o'tgan!"));
            } else {
                userRepository.save(
                        new User(
                                reqSignUp.getPhoneNumber(),
                                passwordEncoder.encode(reqSignUp.getPassword()),
                                reqSignUp.getLastName(),
                                reqSignUp.getFirstName(),
                                roleRepository.findAllByName(reqSignUp.getRole())
//                                reqSignUp.getEmail()
                        ));
                return ResponseEntity.ok(new ApiResponse(true, "Ishchi muvaffaqqiyatli saqlandi"));
            }

        } catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

}