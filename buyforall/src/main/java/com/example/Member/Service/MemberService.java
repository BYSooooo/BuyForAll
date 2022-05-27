package com.example.Member.Service;

import java.util.Collection;
import java.util.Map;

import com.example.Member.Vo.MemberVo;
import com.example.Member.Vo.SecMemberVo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;

public interface MemberService {

    //가입 과정에서 중복 Email 있는지 체크
    public int checkEmail(String memberEmail);
    //가입 과정에서 중복 Id 있는지 체크
    public int checkId(String memberId);
    //가입 과정에서 입력된 값에 대한 Vo Class의 Validation체크
    public Map <String,String> validate(BindingResult bindingResult);
    //가입 과정에서 입력된 회원 정보를 DB에 입력
    public void insertMember(MemberVo member);
    //로그인 과정에서 최종 로그인 일자를 수정
    public void updateLastLogin(SecMemberVo member);
    //아이디 찾기 과정에서 이메일 값으로 아이디 반환
    public String selectEmailForId(String memberEmail);
    //비밀번호 찾기 과정에서 아이디, 이메일로 일치 여부 체크
    public int checkIdAndEmail(String memberId, String memberEmail);
    //비밀번호 찾기 과정에서 임시 비밀번호로 DB 업데이트
    public void modifyPwd(String memberEmail, String tempPwd);
    //회원 정보 수정
    public void updateMember(MemberVo updateMember);
}
