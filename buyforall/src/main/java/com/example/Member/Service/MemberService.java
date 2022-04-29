package com.example.Member.Service;

import java.util.Map;

import com.example.Member.Vo.MemberVo;

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
}
