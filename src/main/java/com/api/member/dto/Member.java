package com.api.member.dto;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member implements Serializable {
	
	private static final long serialVersionUID = -3318830342497624767L;
	
	public int mbr_no;		//ȸ����ȣ
	public String email;	//�̸���
	public String user_nm;	//ȸ����
	public String nick_nm;	//�г���
	public String password;	//��й�ȣ
	public String salt;		//��й�ȣ ��Ʈ
	public String tel_no;	//��ȭ��ȣ
	public Date reg_dtime;	//����Ͻ�
	public Date upt_dtime;	//�����Ͻ�
	
	public String new_password; //������ ��й�ȣ

	@JsonCreator
	@Builder
	public Member(
			@JsonProperty("mbr_no") int mbr_no, 
			@JsonProperty("email") String email,
			@JsonProperty("user_nm") String user_nm,
			@JsonProperty("nick_nm") String nick_nm,
			@JsonProperty("password") String password,
			@JsonProperty("salt") String salt, 
			@JsonProperty("tel_no") String tel_no,
			@JsonProperty("reg_dtime") Date reg_dtime,
			@JsonProperty("upt_dtime") Date upt_dtime ) {
		this.setMbr_no(mbr_no);
		this.setEmail(email);
		this.setUser_nm(user_nm);
		this.setNick_nm(nick_nm);
		this.setPassword(password);
		this.setSalt(salt);
		this.setTel_no(tel_no);
		this.setReg_dtime(reg_dtime);
		this.setUpt_dtime(upt_dtime);
	}
}
