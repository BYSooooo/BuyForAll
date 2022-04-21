package com.example.Mail.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVo {  

    //메일 주소(수신자)
    String mailAddress;
    //메일 제목
    String mailTitle;
    //메일 내용
    String mailContext;
    
}
