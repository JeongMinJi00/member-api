package com.api.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.member.dto.Member;


@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 기 가입여부 확인
	 * @param email
	 * @return
	 */
	public int isExistMember(String email) {
		return sqlSession.selectOne("Member.isExistMember", email);
	}
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public int registMember(Member member) {
        return sqlSession.insert("Member.registMember", member);
	}
	
	/**
	 * 로그인
	 * @param member
	 * @return
	 */
	public Member getLogin(Member member) {
        return sqlSession.selectOne("Member.getLogin", member);
	}
	
	/**
	 * 비밀번호 salt조회
	 * @param member
	 * @return
	 */
	public String getSaltPw(Member member) {
		return sqlSession.selectOne("Member.getSaltPw", member);
	}
	
	/**
	 * 내 정보 보기
	 * @param mbrNo
	 * @return
	 */
	public Map<String, Object> getMemberInfo(int mbrNo) {
        return sqlSession.selectOne("Member.getMemberInfo", mbrNo);
	}
	
	/**
	 * 전화번호 인증
	 * @param telNo
	 * @return
	 */
	public Map<String, Object> getMemberFromTel(String telNo) {
		return sqlSession.selectOne("Member.getMemberFromTel", telNo);
	}
	
	/**
	 * 비밀번호 변경
	 * @param member
	 * @return
	 */
	public int changeMyPw(Member member) {
		return sqlSession.update("Member.changeMyPw", member);
	}
}
