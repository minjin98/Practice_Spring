package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import spring.beans.Member;

public class MemberDao {

	private static long nextId = 0;	// 자동발생 맴버ID
	
	// 키 : 문자열(이메일)
	// 값 : Member
	private Map<String, Member> map = new HashMap<>();

	public Member selectByEmail(String email) {
		return map.get(email);
	}

	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}

	public void update(Member member) {
		map.put(member.getEmail(), member);
	}

	public Collection<Member> selectAll() {
		return map.values();
	}
}
