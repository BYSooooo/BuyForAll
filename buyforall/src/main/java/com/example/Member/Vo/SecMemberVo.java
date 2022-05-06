package com.example.Member.Vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SecMemberVo extends User{
    // 회원 정보를 담은 객체 새로 생성
    private MemberVo member;
    
    //UserDetail에서 인증된 사용자에게 요구하는 username, password, roles 에 맞춰 매개변수를 지정한 후 자기 자신 반환
    public SecMemberVo(MemberVo member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberId(), member.getPassword(), authorities);
        this.member = member;
    }
}
