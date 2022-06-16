package com.example.Member.Dao;

import java.util.Map;

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
    // 로그인 과정에서 최종 로그인 일자 변경
    public void updateLoginTime (Map<String,String> map);
    // 아이디 찾기 과정에서 이메일로 아이디 찾기
    public String getIdbyEmail(String memberEmail);
    // 임시 비밀번호 발급 과정에서 아이디, 이메일로 회원 여부 찾기
    public int checkMemberInfoForPwd(Map<String,String> map);
    // 임시 비밀번호 발급 과정에서 암호화된 Pwd로 DB업데이트
    public void updateTempPwd(Map<String,String> map);
    // 회원 정보 수정
    public void reWriteMemberInfo(MemberVo member);
}
