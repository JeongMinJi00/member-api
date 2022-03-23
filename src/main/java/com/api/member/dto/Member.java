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
	
	public int mbr_no;		//회원번호
	public String email;	//이메일
	public String user_nm;	//회원명
	public String nick_nm;	//닉네임
	public String password;	//비밀번호
	public String salt;		//비밀번호 솔트
	public String tel_no;	//전화번호
	public Date reg_dtime;	//등록일시
	public Date upt_dtime;	//수정일시
	
	public String new_password; //변경할 비밀번호

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
