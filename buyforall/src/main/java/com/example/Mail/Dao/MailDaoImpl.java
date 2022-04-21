package com.example.Mail.Dao;

import java.io.Console;

import com.example.Mail.Vo.MailVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class MailDaoImpl implements MailDao {
    @Autowired
    private JavaMailSender javaMailSender;

    //SimpleMailMessage를 통해 매개 변수로 받은 MailVo 내용을 채워넣어 전송
    @Override
    public void sendVerifyMail(MailVo mail) throws MailAuthenticationException{
        try {
            SimpleMailMessage verifyMail = new SimpleMailMessage();
            verifyMail.setTo(mail.getMailAddress());
            verifyMail.setFrom("bg7006091@gmail.com");
            verifyMail.setSubject(mail.getMailTitle());
            verifyMail.setText(mail.getMailContext());
    
            javaMailSender.send(verifyMail);
            System.out.println("메일 발송 성공");
            System.out.println(mail.getMailContext());

        } catch(MailAuthenticationException e) {
            e.printStackTrace();
        } 
        

        
    }




    
}
