package com.example.Member.Dao;

import java.util.Map;

import com.example.Member.Vo.MemberVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SqlSession session;

    @Override
    public int checkSameEmail(String memberEmail) {
        return this.session.selectOne("Member.checkEmail", memberEmail);
    }

    @Override
    public int checkSameId(String memberId) {
        return this.session.selectOne("Member.checkId",memberId);
    }

    @Override
    public void insertMemberDB(MemberVo member) {
        this.session.selectOne("Member.insertMember", member);
    }

    @Override
    public MemberVo getMemberByID (String memberId) {
        return this.session.selectOne("Member.getMemberInfoById", memberId);
    }
    
    @Override
    public void updateLoginTime (Map<String,String> map) {
        this.session.update("Member.insertLastLogin",map);
    }

    @Override
    public String getIdbyEmail(String memberEmail) {        
        var result = this.session.selectOne("Member.getIdByEmail",memberEmail);
        return result == null ? "null" : result.toString();
    }

    
    
    
}
