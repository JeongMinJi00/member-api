package com.api.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.member.dto.Member;
import com.api.member.frm.exception.ApiException;
import com.api.member.frm.exception.ExceptionEnum;
import com.api.member.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * ������ȣ �߱�
	 * @param telNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/confirmTelNo")
	public Map<String, Object> confirmTelNo(@RequestBody Map<String,String> param, HttpServletRequest request) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String telNo = param.get("tel_no").toString();
		
		retMap.put("code", "0000");
		retMap.put("message", "������ȣ�� �߱޵Ǿ����ϴ�.");
		retMap.put("data", memberService.confirmTelNo(telNo, request));
		
		return retMap;
	}
	
	/**
	 * ��ȭ��ȣ ����
	 * @param telNo
	 * @param certifyNum
	 * @throws Exception
	 */
	@PostMapping("/certifyMember")
	public Map<String, Object> certifyMember(@RequestBody Map<String,String> param, HttpSession session) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String telNo = param.get("tel_no").toString();
		String certifyNum = param.get("certify_num").toString();
		
		String sessionNum = (String) session.getAttribute(telNo);
		session.setAttribute("certi_yn", "N");
		
		if(sessionNum == null) {
			throw new ApiException(ExceptionEnum.REQUIRED_CERTIFICATION_NUM);
		}
		
		if(certifyNum == null || telNo == null) {
			throw new ApiException(ExceptionEnum.REQUIRED_INPUT_BOX);
		}
		
		if(certifyNum.equals(sessionNum)) {
			session.setAttribute("tel_no", telNo);
			session.setAttribute("certi_yn", "Y");

			retMap.put("code", "0000");
			retMap.put("message", "��ȭ��ȣ ������ �Ϸ�Ǿ����ϴ�.");
		} else {
			throw new ApiException(ExceptionEnum.NOT_VALID_CERTIFICATION);
		}
		
		return retMap;
	}

	/**
	 * ȸ������
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/registMember")
	public Map<String, Object> registMember(@RequestBody Member member, HttpSession session) throws Exception {
		String certiYn = (String)session.getAttribute("certi_yn");
		
		if("Y".equals(certiYn)) {
			member.setTel_no((String)session.getAttribute("tel_no"));
			memberService.registMember(member);
		} else {
			throw new ApiException(ExceptionEnum.REQUIRED_CERTIFICATION);
		}
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", "0000");
		retMap.put("message", "���� ó���Ǿ����ϴ�.");
		return retMap;
	}
	
	/**
	 * �α���
	 * @param member
	 * @throws Exception
	 */
	@PostMapping("/getLogin")
	public Map<String, Object> getLogin(@RequestBody Member member, HttpServletRequest request) throws Exception {
		memberService.getLogin(member, request);

		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", "0000");
		retMap.put("message", "���� ó���Ǿ����ϴ�.");
		return retMap;
	}
	
	/**
	 * �� ���� ��������
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getMemberInfo")
	public Map<String, Object> getMemberInfo(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		Integer mbrNo = (Integer) session.getAttribute("mbr_no");
		
		if(mbrNo == null) {
			throw new ApiException(ExceptionEnum.REQUIRED_LOGIN);
		}
		
		retMap.put("code", "0000");
		retMap.put("message", "���� ó���Ǿ����ϴ�.");
		retMap.put("data", memberService.confirmTelNo("0", request));
		retMap.put("data", memberService.getMemberInfo(mbrNo));
		
		return retMap;
	}
	
	/**
	 * ��й�ȣ �缳��
	 * @param member
	 * @param session
	 * @throws Exception
	 */
	@PostMapping("/changeMyPw")
	public Map<String, Object> changeMyPw(@RequestBody Member member, HttpSession session) throws Exception {
		Boolean loginCheck = Optional.ofNullable((Boolean)session.getAttribute("login_check")).orElseGet(() -> Boolean.FALSE);
		String certiYn = (String)session.getAttribute("certi_yn");
				
		if(loginCheck) {
			throw new ApiException(ExceptionEnum.ALREADY_LOGIN);
		}
		
		if(!"Y".equals(certiYn)) {
			throw new ApiException(ExceptionEnum.REQUIRED_CERTIFICATION);
		} 
		
		memberService.changeMyPw(member, session);
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", "0000");
		retMap.put("message", "���� ó���Ǿ����ϴ�.");
		return retMap;
	}
}
