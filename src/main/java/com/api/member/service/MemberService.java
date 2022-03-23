package com.api.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.api.member.dto.Member;

public interface MemberService {
	
	/**
	 * ȸ������
	 * @param member
	 * @throws Exception
	 */
	public void registMember(Member member) throws Exception;
	
	/**
	 * �α��� ó��
	 * @param member
	 * @param request
	 * @throws Exception
	 */
	public void getLogin(Member member, HttpServletRequest request) throws Exception;
	
	/**
	 * �� ���� ����
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMemberInfo(int mbrNo) throws Exception;
	
	/**
	 * ��ȭ��ȣ ����
	 * @param telNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> confirmTelNo(String telNo, HttpServletRequest request) throws Exception;
	
	/**
	 * ��й�ȣ ����
	 * @param member
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public int changeMyPw(Member member, HttpSession session) throws Exception;
}
