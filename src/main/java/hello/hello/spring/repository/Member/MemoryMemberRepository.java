package hello.hello.spring.repository.Member;

import hello.hello.spring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements Repository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //Optional.ofNullable으로 감싸면 null값도 나올 수 있다는데 나중에 알려준다함
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    //store 안에 있는 value 값을 계속 돌리면서 매개변수로 들어온 name과 같은 값이 있으면 반환을 하고
    //없으면 null값을 반환한다.

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
//위 모든 내용들을 잘 되어있나 검증하는 방법이 테스트 케이스를 작성하는 것임