package spring.services;

import java.time.LocalDateTime;

import spring.RegisterRequest;
import spring.beans.Member;
import spring.dao.MemberDao;
import spring.exceptions.DuplicateMemberException;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	/*
	 * 동일한 이메일이 존재하는지 검사 
	 */
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("동일한 email로 등록된 멤버가 있습니다." + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
