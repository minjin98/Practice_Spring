package list;

import java.time.LocalDateTime;

public class IssueBean{

	private int arm_seq;
	private String planID;
	private String issueNo;
	private String issueName;
	private String issueInfo;
	private String timestamp;
	public int getArm_seq() {
		return arm_seq;
	}
	public void setArm_seq(int arm_seq) {
		this.arm_seq = arm_seq;
	}
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getIssueInfo() {
		return issueInfo;
	}
	public void setIssueInfo(String issueInfo) {
		this.issueInfo = issueInfo;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}