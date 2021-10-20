package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.Member.MemoryMemberRepository;
import hello.hello.spring.repository.Member.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//따른데에서 갖다 쓸게 아니니까 굳이 public을 쓸 필요 없음
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    //각각 테스트가 끝날때 마다 한번씩 실행되는 메서드

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member  result = repository.findById(member.getId()).get();
        //Optional로 쌓여있는건 get()으로 꺼낼 수 있음

        //Assertions.assertEquals(member,result);
        //두개가 같은지 확인 하는 방법으로 Assertions.assertEquals가 있다. 두개 중 기대되는 값이 앞에 오고 만약 기대되는 값과
        //뒤에 값이 같다면 실행 되면 다르다면 오류가 난다.

        assertThat(member).isEqualTo(result);
        //Assertions.assertThat은 alt + Enter을 통해서 위와 같이 간단하게 변화 시킬 수 있다.
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    //데이터 클리어 없이 그냥 돌리면 에러남 findByName에서 왜냐하면 위 메서드 들은 순서 보장 없이 무작위 순서로 돌아가기 때문에
    //만약 findAll()이 끝나면 그 안에 spring1과 spring2가 남아있기 때문에 다음 메서드에서 오류가 발생 그렇기 때문에 데이터 클리어 필요


}
