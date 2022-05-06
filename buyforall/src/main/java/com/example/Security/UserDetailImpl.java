package com.example.Security;

import java.util.ArrayList;
import java.util.Collection;

import com.example.Member.Dao.MemberDao;
import com.example.Member.Vo.MemberVo;
import com.example.Member.Vo.SecMemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailImpl implements UserDetailsService {

    @Autowired
    public MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //회원 아이디로 검색해 일치하는 회원 정보를 Vo 객체로 가져옴
        MemberVo selectedMember = this.memberDao.getMemberByID(username);
        //인증 정보 중 권한을 가져와 담기 위한 새로운 Array 생성
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(selectedMember.getMemberGrade()));

        //MemberVo 객체 정보와 인증 정보를 모두 가진 새로운 Vo 객체 생성
        SecMemberVo member = new SecMemberVo(selectedMember,roles);
        return member;
        
    }
    
}
