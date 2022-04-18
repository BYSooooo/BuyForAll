package com.example.Member.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SqlSession session;

    public int checkSameEmail(String memberEmail) {
        return this.session.selectOne("Member.checkEmail", memberEmail);
    }
    
}
