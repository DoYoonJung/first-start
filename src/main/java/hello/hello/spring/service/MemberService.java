package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.Member.MemoryMemberRepository;
import hello.hello.spring.repository.Member.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


//서비스 클래스

public class MemberService {
    private final Repository memberRepository;

    public MemberService(Repository memberRepository){
        this.memberRepository=memberRepository;
    }
    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //만약 result에 값이 있으면 이미 존재하는 회원입니다 출력, ifPresent메서드는 Optional때문에 가능한 것
        //만약 null일 가능성이 있다면 Optional로 한번 감싸는 것이 효과적

        //ctrl + alt + shift + t 하면 메서드로 뽑아내는 것이 가능하다.
    }

    //전체회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
