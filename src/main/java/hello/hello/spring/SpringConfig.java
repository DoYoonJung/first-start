package hello.hello.spring;

import hello.hello.spring.repository.Member.JdbcMemberRepository;
import hello.hello.spring.repository.Member.MemoryMemberRepository;
import hello.hello.spring.repository.Member.Repository;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberService memberService(){

        return new MemberService(Repository());
    }

    @Bean
    public Repository memberRepository(){
        return new JdbcMemberRepository(dataSource);
       // return new MemoryMemberRepository();
    }


}
