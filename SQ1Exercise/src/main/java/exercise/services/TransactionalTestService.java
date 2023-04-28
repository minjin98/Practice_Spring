// @Transactional 테스트 서비스
package exercise.services;

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import spring.exceptions.MemberNotFoundException;
*/
import org.springframework.transaction.annotation.Transactional;

import exercise.dao.Member;
import exercise.dao.MemberDao;

public class TransactionalTestService {
	private MemberDao memberDao;

	public TransactionalTestService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void tranactionOne(Member member) {
		System.out.println("[트랜잭션] 시작");

		System.out.println("[트랜잭션] 입력");
		this.memberDao.insert(member);
		System.out.println("입력데이터: " + member);
			
		System.out.println("[트랜잭션] 수정");
		this.memberDao.update(member);
			
		System.out.println("[트랜잭션] 삭제");
		this.memberDao.delete(member.getId());
			
		System.out.println("[트랜잭션] 종료");
	}

	@Transactional
	public void tranactionTwo(Member member) {
		System.out.println("[트랜잭션] Two");

		System.out.println("[트랜잭션] 입력");
		this.memberDao.insert(member);
		System.out.println("입력데이터: " + member);
			
		System.out.println("[트랜잭션] 수정");
		member.setPassword("4321");
		this.memberDao.update(member);
			
		System.out.println("[트랜잭션] 종료");
	}

}
