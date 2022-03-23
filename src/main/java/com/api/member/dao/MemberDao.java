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
	 * �� ���Կ��� Ȯ��
	 * @param email
	 * @return
	 */
	public int isExistMember(String email) {
		return sqlSession.selectOne("Member.isExistMember", email);
	}
	
	/**
	 * ȸ������
	 * @param member
	 * @return
	 */
	public int registMember(Member member) {
        return sqlSession.insert("Member.registMember", member);
	}
	
	/**
	 * �α���
	 * @param member
	 * @return
	 */
	public Member getLogin(Member member) {
        return sqlSession.selectOne("Member.getLogin", member);
	}
	
	/**
	 * ��й�ȣ salt��ȸ
	 * @param member
	 * @return
	 */
	public String getSaltPw(Member member) {
		return sqlSession.selectOne("Member.getSaltPw", member);
	}
	
	/**
	 * �� ���� ����
	 * @param mbrNo
	 * @return
	 */
	public Map<String, Object> getMemberInfo(int mbrNo) {
        return sqlSession.selectOne("Member.getMemberInfo", mbrNo);
	}
	
	/**
	 * ��ȭ��ȣ ����
	 * @param telNo
	 * @return
	 */
	public Map<String, Object> getMemberFromTel(String telNo) {
		return sqlSession.selectOne("Member.getMemberFromTel", telNo);
	}
	
	/**
	 * ��й�ȣ ����
	 * @param member
	 * @return
	 */
	public int changeMyPw(Member member) {
		return sqlSession.update("Member.changeMyPw", member);
	}
}
