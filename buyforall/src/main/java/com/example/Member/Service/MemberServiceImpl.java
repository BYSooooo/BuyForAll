package com.example.Member.Service;

import com.example.Member.Dao.MemberDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberDao memberDao;

    public int checkEmail(String memberEmail) {
        int checkResult = memberDao.checkSameEmail(memberEmail);
        return checkResult;
    }
    
}
