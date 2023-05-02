package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) { //Member.java에서 가져온 정보 save하는 함수
        member.setId(++sequence); //id 세팅
        store.put(member.getId(), member); //store에 저장
        return member;
    }
    //사용자가 회원가입을 하면 store에 담겨 저장
    //id는 자동으로 증가하는 sequence로 설정
    //id는 setter로 설정 // 담을때는 put으로 map에 담아줌




    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public Optional<Member> fineByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }



    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearSore(){
        store.clear();
    }
}
