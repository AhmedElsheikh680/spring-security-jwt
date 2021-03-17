package com.spring.security.jwt.base;


import com.spring.security.jwt.repo.AuthoritiesRepo;
import com.spring.security.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataLoader implements CommandLineRunner {

    private UserRepo userRepo;
    private AuthoritiesRepo authoritiesRepo;

    @Autowired
    public DataLoader(UserRepo userRepo, AuthoritiesRepo authoritiesRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authoritiesRepo = authoritiesRepo;
        this.passwordEncoder = passwordEncoder;
    };
    private PasswordEncoder passwordEncoder;

    public DataLoader(UserRepo userRepo, AuthoritiesRepo authoritiesRepo) {
        this.userRepo = userRepo;

        this.authoritiesRepo = authoritiesRepo;

    }



    @Override
    public void run(String... args) throws Exception {

//       User user =  this.userRepo.findByUsername("Ahmed");
//        System.out.println(user.getAddress());
//        this.userRepo.deleteAll();
//        User admin = new User("ahmed",passwordEncoder.encode("ahmed123"), "30","Cairo",1);
//
//        admin.setAuthorities(this.authoritiesRepo.findAll());
//        userRepo.save(admin);
//        //////////////////////////////////////
//        User manager = new User("ali", passwordEncoder.encode("ali123"), "25","Giza",1);
//
//        Authorities managerAuthorities1 =  authoritiesRepo.findById(2L).get();
//        Authorities managerAuthorities2 =  authoritiesRepo.findById(3L).get();
//        Authorities managerAuthorities3 =  authoritiesRepo.findById(5L).get();
//        Authorities managerAuthorities4 =  authoritiesRepo.findById(6L).get();
//
//        manager.getAuthorities().add(managerAuthorities1);
//        manager.getAuthorities().add(managerAuthorities2);
//        manager.getAuthorities().add(managerAuthorities3);
//        manager.getAuthorities().add(managerAuthorities4);
//        userRepo.save(manager);
//        ///////////////////////////////////////
//        User user  = new User("mohamed", passwordEncoder.encode("mohamed123"),"35", "Shbra",1);
//
//        Authorities userAuthorities1 = authoritiesRepo.findById(3L).get();
//        Authorities userAuthorities2 = authoritiesRepo.findById(6L).get();
//
//
//        user.getAuthorities().add(userAuthorities2);
//        userRepo.save(user);
    }
}
