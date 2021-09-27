package uz.rootec.appvacineserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.rootec.appvacineserver.entity.User;
import uz.rootec.appvacineserver.repository.RoleRepository;
import uz.rootec.appvacineserver.repository.UserRepository;

/**
 * Created by Sherlock on 09.04.2020.
 */

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Value("${spring.datasource.initialization-mode}")
    String initialMode;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        if (initialMode.equals("always")) {
            User user = new User("3d4fj03KDO'oOq", passwordEncoder.encode("jkfjewiDW3(#df39oDK3(#*"), "admin", "admin", roleRepository.findAll());
            userRepository.save(user);
        }
    }
}
