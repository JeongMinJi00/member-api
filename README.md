## 회원정보 API

#1. 테이블 생성
  CREATE TABLE member(
    mbr_no INT(15) NOT NULL COMMENT '회원번호' PRIMARY KEY AUTO_INCREMENT,

    email VARCHAR(60) NOT NULL COMMENT '이메일' UNIQUE KEY,

    user_nm VARCHAR(50) NOT NULL COMMENT '회원명',

    nick_nm VARCHAR(50) DEFAULT NULL COMMENT '닉네임', 

    password VARCHAR(200) NOT NULL COMMENT '비밀번호',

    salt VARCHAR(50) NOT NULL COMMENT '비밀번호 솔트',

    tel_no VARCHAR(20) NOT NULL COMMENT '전화번호',
    
    reg_dtime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    
    upt_dtime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시'
  
  );
  
2. 구현범위
  - 회원가입 기능
  - 로그인 기능
  - 내 정보 보기 기능
  - 비밀번호 재설정 기능
  
3. 기술 스택
  - java 11
  - spring-boot 2.6.4.RELEASE
  - lombok 1.18.22
