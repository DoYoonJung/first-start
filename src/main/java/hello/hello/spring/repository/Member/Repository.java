package hello.hello.spring.repository.Member;

import hello.hello.spring.domain.Member;


import java.util.List;
import java.util.Optional;

public interface Repository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
