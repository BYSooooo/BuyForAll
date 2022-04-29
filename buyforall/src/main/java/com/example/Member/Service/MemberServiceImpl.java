package com.example.Member.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.example.Member.Dao.MemberDao;
import com.example.Member.Vo.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberDao memberDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public int checkEmail(String memberEmail) {
        int checkResult = memberDao.checkSameEmail(memberEmail);
        return checkResult;
    }

    @Override
    public int checkId(String memberId) {
        int checkIdResult = memberDao.checkSameId(memberId);
        return checkIdResult;
    }

    @Override
    public Map<String, String> validate(BindingResult bindingResult) {
        Map<String,String> map = new HashMap<String, String>();
        for(FieldError error : bindingResult.getFieldErrors()) {
            map.put(String.format("valid_%s",error.getField()),error.getDefaultMessage());
        }
        return map;
    }

    @Override
    public void insertMember(MemberVo member) {
        // 비밀 번호를 암호화해서 재 저장
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        // 회원 등급 입력
        member.setMemberGrade("ROLE_MEMBER");
        // 가입 날짜 입력 (클라이언트 기준으로 일자 조정 "연-월-일 시:분:초" 포맷으로)
        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = nowTime.format(formatter);
        member.setJoinDate(formattedTime);
        memberDao.insertMemberDB(member);
    }

}
