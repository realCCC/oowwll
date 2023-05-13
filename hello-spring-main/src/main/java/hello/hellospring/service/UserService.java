package hello.hellospring.service;

import hello.hellospring.domain.user.User;
import hello.hellospring.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(User user){
        return userRepository.save(user).getId();
    }
}

// private final UserRepository userRepository : 생성자 주입을 받기 위해 @RequiredArgsConstructor 어노테이션을 썼습니다.