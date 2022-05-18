package com.example.Mail.Service;

public interface MailService {
    
    // 회원 가입 과정 중 인증 메일 발송을 위한 MailVo 객체 내용 작성
    public void createSendMail(String memberEmail, String veriNum);
    // 가임 중 인증에 필요한 랜덤 번호 생성
    public String createRandomNumber();
    // 임시 비밀번호 발급 과정 중 이메일 발송을 위한 MailVo 객체 내용 작성
    public void createMailForPwd(String memberEmail, String veriNum);
}
