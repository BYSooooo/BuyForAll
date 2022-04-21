package com.example.Mail.Service;

import java.util.Random;

import com.example.Mail.Dao.MailDao;
import com.example.Mail.Vo.MailVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailDao mailDao;

    // 회원 가입 과정 중 인증 메일 발송을 위한 MailVo 객체 내용 작성
    @Override
    public void createSendMail(String memberEmail, String veriNum) {
        MailVo sendMail = new MailVo();
        sendMail.setMailAddress(memberEmail);
        sendMail.setMailTitle("BuyForAll의 회원 가입 인증 이메일입니다.");
        sendMail.setMailContext("회원 가입 인증 번호는" + veriNum + "입니다.");

        this.mailDao.sendVerifyMail(sendMail);
    }
    // 가임 중 인증에 필요한 랜덤 번호 생성
    @Override
    public String createRandomNumber() {
        
        String createdVerifyNum = "";
        Random randomNum = new Random();

        // 하위 과정을 6번 반복
        for (int i=0; i<6; i++) {
            //알파벳 A~Z 범위 중 랜덤으로 선택
            int index = randomNum.nextInt(25) + 65;
            // 선택한 알파벳을 인증 번호 변수에 추가
            createdVerifyNum += (char)index;
        }
        // 생성될 랜덤 숫자의 자릿수
        int numIndex = randomNum.nextInt(99999)+10000;
        //생성한 숫자를 인증 번호 변수에 추가
        createdVerifyNum += numIndex;
        // 반환
        return createdVerifyNum;
    }


    

    


    
}
