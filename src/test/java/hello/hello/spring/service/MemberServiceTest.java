package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.Member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
// MemberService 클래스 에서 ctrl + shift+ t를 하면 자동으로 test 클래스를 생성해 준다!! 꿀팁~

   //test는 과감하게 한글로 적어도 됨, 직관적으로 알기 위해, 그리고 이건 실제 코드에 포함되지 않기 때문에

    MemberService memberService ;
    MemoryMemberRepository memberRepository ;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given

        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);

        //then(검증)
        //Optional<Member> one = memberService.findOne(saveId);
        //=의 뒷 부분만 적은 다음에 alt + enter + enter 누르면 앞에 부분이 자동으로 써짐

        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
        //alt + enter하면 위에 코드처럼 전체 코드를 줄일 수 있다. 원래 코드는 Assertions.asserThat(member.getName()).isEqualTo(findMember.getName());
        //이었다.
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");



        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //두개 똑같은 spring을 넣을 경우 joing 메서드의 예외가 터지는지 확인하기 위한 코드다.
        //member2를 joing하면 spring으로 같음으로 정상적으로 녹색 실행된다.

        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}