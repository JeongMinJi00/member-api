package com.api.member.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.member.dao.MemberDao;
import com.api.member.dto.Member;
import com.api.member.frm.exception.ApiException;
import com.api.member.frm.exception.ExceptionEnum;
import com.api.member.service.MemberService;
import com.api.member.util.Utility;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 회원가입
	 */
	@Override
	public void registMember(Member member) throws Exception {
		
		if(memberDao.isExistMember(member.getEmail()) < 1) {
			//비밀번호 암호화
			String saltPw = Utility.SaltPassword();
			String encryptPw = Utility.SHA512Password(saltPw, member.getPassword());
			member.setPassword(encryptPw);
			member.setSalt(saltPw);
			
			int result = memberDao.registMember(member);
			if(result < 1) {
				throw new ApiException(ExceptionEnum.REGISTER_MEMBER_ERROR);
			} 
		} else {
			throw new ApiException(ExceptionEnum.ALREADY_EXIST_USERID);
		}
	} 
	
	
	/**
	 * 로그인 처리
	 */
	@Override
	public void getLogin(Member member, HttpServletRequest request) throws Exception {
		//비밀번호 암호화
		HttpSession session = request.getSession();
		String saltPw = memberDao.getSaltPw(member);
		String encryptPw = Utility.SHA512Password(saltPw, member.getPassword());
		member.setPassword(encryptPw);
		
		Member result = Optional.ofNullable(memberDao.getLogin(member)).orElseGet(() -> null);

		if(result != null) {
			session.setAttribute("login_check", true);
			session.setAttribute("mbr_no", result.getMbr_no());
		} else {
			session.invalidate();
			throw new ApiException(ExceptionEnum.NOT_FOUND_MEMBER);
		}
	} 
	
	
	/**
	 * 내 정보 보기
	 */
	@Override
	public Map<String, Object> getMemberInfo(int mbrNo) throws Exception {
		return memberDao.getMemberInfo(mbrNo);
	}
	
	
	@Override
	public Map<String, String> confirmTelNo(String telNo, HttpServletRequest request) throws Exception {
		Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        int randomNum = generator.nextInt(1000000) % 1000000;
        
        HttpSession session = request.getSession();
        session.setAttribute(telNo, String.valueOf(randomNum));
        session.setMaxInactiveInterval(3*60);
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("certify_num",  String.valueOf(randomNum));

        return map;
	}
	
	/**
	 * 비밀번호 재설정
	 */
	@Override
	public int changeMyPw(Member member, HttpSession session) throws Exception {
		if(Utility.nvl(member.getNew_password()).isEmpty()) {
			throw new ApiException(ExceptionEnum.REQUIRED_NEW_PW);
		}
		
		String telNo = (String)session.getAttribute("tel_no");
		Map<String, Object> memberInfo = memberDao.getMemberFromTel(telNo);
		
		String saltPw = Utility.SaltPassword();
		String encryptPw = Utility.SHA512Password(saltPw, member.getNew_password());
		
		member.setSalt(saltPw);
		member.setPassword(encryptPw);
		member.setMbr_no((Integer)memberInfo.get("mbr_no"));
		
		int result = memberDao.changeMyPw(member);
		
		if(result < 1) {
			throw new ApiException(ExceptionEnum.CHANGE_PASSWORD_ERROR);
		}
		
		return result;
	}
}
