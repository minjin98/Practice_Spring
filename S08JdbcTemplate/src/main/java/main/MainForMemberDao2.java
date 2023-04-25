package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.dao.Member;
import spring.dao.MemberDao;

public class MainForMemberDao2 {
	private static MemberDao memberDao;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		memberDao = ctx.getBean(MemberDao.class);
		
		insertMember();

		selectAll();
		updateMember();
		Long memberid = insertMember();
		
		selectAll();

		deleteMember(memberid);

		selectAll();

		ctx.close();
	}

	private static void selectAll() {
		System.out.println("----- selectAll");
		int total = memberDao.count();
		System.out.println("전체 데이터: " + total);
		List<Member> members = memberDao.selectAll();
		for (Member m : members) {
			System.out.println(m.getId() + ":" + m.getEmail() + ":" + m.getName());
		}
	}

	private static void updateMember() {
		System.out.println("----- updateMember");
		Member member = memberDao.selectByEmail("madvirus@madvirus.net");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);

		memberDao.update(member);
		System.out.println("암호 변경: " + oldPw + " > " + newPw);
	}

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	private static Long insertMember() {
		System.out.println("----- insertMember");

		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + " 데이터 추가");
		
		return member.getId();
	}
	
	private static void deleteMember(Long memberid) {
		System.out.println("----- deleteMember : memberid=" + memberid);

		int delcnt = memberDao.delete(memberid);
		System.out.printf("[데이터 삭제] 멤버 ID(%d) : 삭제 %s\n", memberid, ((delcnt > 0) ? "성공" : "실패"));
	}

}
