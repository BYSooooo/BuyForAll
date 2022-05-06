package com.example.Member.Dao;

import com.example.Member.Vo.MemberVo;

public interface MemberDao {
    // 가입 과정에서 중복 Email 체크
    public int checkSameEmail(String memberEmail);
    // 가입 과정에서 중복 Id 체크
    public int checkSameId(String memberId);
    // 가입 과정에서 회원 정보 DB 입력
    public void insertMemberDB(MemberVo member);
    // 로그인 과정에서 회원 아이디로 DB에서 나머지 정보 요청
    public MemberVo getMemberByID (String memberId);
    
}
