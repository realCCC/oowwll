package hello.hellospring.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입_테스트(){
        //given
        String username = "test";
        String nickname = "babo";

        userRepository.save(User.builder()
                .username(username)
                .password("1234")
                .email("test@naver.com")
                .nickname(nickname)
                .role(Role.USER)
                .build());
        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getNickname()).isEqualTo(nickname);
    }
}

//    JUnit5에서는 @AfterEach로 사용해야 합니다.
//        단위 테스트가 끝날 때마다 수행되는 메소드를 지정합니다.
//        여러 테스트 진행시 데이터가 남아있어서 실패의 원인이 됩니다.
//        userRepository.save를 실행하게 되면 insert 혹은 update 쿼리가 실행됩니다.
//        userRepository.findAll() : 모든 데이터를 조회
