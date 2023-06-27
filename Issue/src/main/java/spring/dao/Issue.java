package spring.dao;

import java.time.LocalDateTime;


public class Issue {

	private int arm_seq;
	private String planID;
	private String issueNo;
	private String issueName;
	private String issueInfo;
	private LocalDateTime timestamp;
	
	public Issue(String issueNo, String issueName, String issueInfo, LocalDateTime timestamp) {
		this.issueNo = issueNo;
		this.issueName = issueName;
		this.issueInfo = issueInfo;
		this.timestamp = timestamp;
	}
	

	public int getArm_seq() {
		return arm_seq;
	}
	
	public String getPlanID() {
		return planID;
	}
	public String getIssueNo() {
		return issueNo;
	}

	public String getIssueName() {
		return issueName;
	}

	public String getIssueInfo() {
		return issueInfo;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		String msg = String.format("planID(%d), issueNo(%s), issueName(%s), issueInfo(%s), timestamf(%tF)",
				this.planID, this.issueNo, this.issueName, this.issueInfo, this.timestamp);
		
		return msg;
	}

}
