package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     *
     * @param loginId
     * @param password
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password){

//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); //optional 안의 내용을 꺼내는 메서드
//        if(member.getPassword().equals(password)){
//            return member;
//        }else{
//            return null;
//        }
        // optinal + filter + 화살표함수 (Stream 익혀야해)
        return memberRepository.findByLoginId(loginId).filter(m->m.getPassword().equals(password)).orElse(null);
    }


}
