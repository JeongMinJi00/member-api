package com.api.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.api.member.dto.Member;

public interface MemberService {
	
	/**
	 * 회원가입
	 * @param member
	 * @throws Exception
	 */
	public void registMember(Member member) throws Exception;
	
	/**
	 * 로그인 처리
	 * @param member
	 * @param request
	 * @throws Exception
	 */
	public void getLogin(Member member, HttpServletRequest request) throws Exception;
	
	/**
	 * 내 정보 보기
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMemberInfo(int mbrNo) throws Exception;
	
	/**
	 * 전화번호 인증
	 * @param telNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> confirmTelNo(String telNo, HttpServletRequest request) throws Exception;
	
	/**
	 * 비밀번호 변경
	 * @param member
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public int changeMyPw(Member member, HttpSession session) throws Exception;
}
