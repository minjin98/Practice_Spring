package controller.process;

import java.time.LocalDateTime;

public class Process{

	private String prodName;
	private String good_count;
	private String bad_count;
	private String issue_count;
	
	public Process(String prodName, String good_count, String bad_count, String issue_count) 
	{
		this.prodName = prodName;
		this.good_count = good_count;
		this.bad_count = bad_count;
		this.issue_count = issue_count;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getGood_count() {
		return good_count;
	}
	public void setGood_count(String good_count) {
		this.good_count = good_count;
	}
	public String getBad_count() {
		return bad_count;
	}
	public void setBad_count(String bad_count) {
		this.bad_count = bad_count;
	}
	public String getIssue_count() {
		return issue_count;
	}
	public void setIssue_count(String issue_count) {
		this.issue_count = issue_count;
	}
	
}