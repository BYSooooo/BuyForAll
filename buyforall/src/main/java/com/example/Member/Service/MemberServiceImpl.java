package com.example.Member.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.example.Member.Dao.MemberDao;
import com.example.Member.Vo.MemberVo;
import com.example.Member.Vo.SecMemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberDao memberDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    
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
    @Override
    public void updateLastLogin(SecMemberVo member) {
        // 현재 시간을 클라이언트 기준으로 측정해서 변수 선언
        LocalDateTime loginTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = loginTime.format(formatter);

        //아이디와 로그인 일자를 담은 Map 객체 선언
        Map<String, String> map = new HashMap<String,String>();
        map.put("memberId", member.getMember().getMemberId());
        map.put("lastLogin", formattedTime);
        memberDao.updateLoginTime(map);
    }
    @Override
    public String selectEmailForId(String memberEmail) {
        String searchId = memberDao.getIdbyEmail(memberEmail);
        String searchResult = "";
        if(searchId != "null") {
            searchResult = searchId;
        } else {
            searchResult = "일치하는 이메일이 없습니다.";
        }
        return searchResult;
    }
    @Override
    public int checkIdAndEmail(String memberId, String memberEmail) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("memberId", memberId);
        map.put("memberEmail", memberEmail);
        //DB에 쿼리 전송 후 일치하면 정보가 있으면 1, 없으면 0
        return memberDao.checkMemberInfoForPwd(map);
    }
    @Override
    public void modifyPwd(String memberEmail, String tempPwd) {
        String cryptoTempPwd = passwordEncoder.encode(tempPwd);
        Map<String,String> map = new HashMap<>();
        map.put("memberEmail", memberEmail);
        map.put("memberPwd", cryptoTempPwd);
        memberDao.updateTempPwd(map);
    }

    @Override
    @Transactional
    public void updateMember(MemberVo updateMember) {
        //Dirty Checking을 통한 자동 commit, update를 위해 Repository를 통해 DB에서 수정 전 정보를 가져옴
        MemberVo modifiedMember = memberDao.getMemberByID(updateMember.getMemberId());

        
        //새로운 정보를 MemberVo 객체에 적용
        String encodePwd = passwordEncoder.encode(updateMember.getPassword());
        modifiedMember.setPassword(encodePwd);
        modifiedMember.setMemberName(updateMember.getMemberName());
        modifiedMember.setMemberEmail(updateMember.getMemberEmail());
        modifiedMember.setMemberAddress(updateMember.getMemberAddress());
        modifiedMember.setMemberAddress2(updateMember.getMemberAddress2());
        modifiedMember.setPostNumber(updateMember.getPostNumber());
        //Transaction을 통해 Vo 객체의 변경이 발생하면 DirtyChecking 작동 -> 자동 Commit

        //회원 정보 Dao에 전달
        memberDao.reWriteMemberInfo(modifiedMember);

        // 로그아웃 없이 인증정보를 변경하기 위해 새로운 인증 Token 생성
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(updateMember.getMemberId(),updateMember.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
