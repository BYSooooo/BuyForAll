package com.example.buyforall.Member.Vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//회원 정보를 담는 Vo Class
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo {
    
    @NotNull(message = "아이디는 필수 항목입니다.")
    String memberId;    //회원 아이디
    String password;    //회원 비밀번호
    String memberName;  //회원 이름
    @Email
    String memberEmail; //회원 이메일
    String memberGrade; //회원 등급(Security Role)
    String memberAddress;   //회원 주소
    
    String lastLogin;   //마지막 로그인
    int amountBuyingPrice;  //총 구매 금액
    String joinDate;    //가입일

}
