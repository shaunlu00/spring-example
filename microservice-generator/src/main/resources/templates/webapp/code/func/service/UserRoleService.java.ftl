package ${package}.service;

import ${package}.domain.User;
import ${package}.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserRoleService {

    private final UserRepository userRepository;

    public UserRoleService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
