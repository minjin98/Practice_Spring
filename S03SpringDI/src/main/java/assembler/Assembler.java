package assembler;

import spring.dao.MemberDao;
import spring.services.ChangePasswordService;
import spring.services.MemberRegisterService;

public class Assembler {

	private MemberDao memberDao;			// 맴버 처리
	private MemberRegisterService regSvc; 	// 맴버 등록
	private ChangePasswordService pwdSvc;	// 맴버 변경

	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}

	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
