package com.example.Member.Vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//회원 정보를 담는 Vo Class
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVo {
    
    @NotNull(message = "아이디는 필수 항목입니다.")
    @NotBlank(message = "아이디는 필수입력 항목입니다.")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9]).{10,15}",
            message = "아이디는 염문대소문자, 숫자를 포함한 10 ~ 15자로 입력해야 합니다.")
    String memberId;    //회원 아이디

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @NotNull(message = "비밀번호는 필수입력 항목입니다.")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[!@#$%^&*+-_])(?=.*[0-9]).{10,20}",
            message = "비밀번호는 영문대소문자, 숫자, 특수문자를 포함한 10 ~ 20자로 입력해야 합니다.")
    String password;    //회원 비밀번호

    @NotNull(message = "회원 이름은 필수입력 항목입니다.")
    @NotBlank(message = "회원 이름은 필수입력 항목입니다.")
    String memberName;  //회원 이름
    
    @Email
    String memberEmail; //회원 이메일
    String memberGrade; //회원 등급(Security Role)
    String postNumber;  // 회원 주소의 우편번호
    String memberAddress;   //회원 주소
    String memberPhoto; //회원 프로필 사진

    String lastLogin;   //마지막 로그인
    int amountBuyingPrice;  //총 구매 금액
    String joinDate;    //가입일

}
