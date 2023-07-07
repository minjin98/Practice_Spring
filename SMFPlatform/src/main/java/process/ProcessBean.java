package process;

public class ProcessBean{

	private String prodName;
	private String good_count;
	private String bad_count;
	private String issue_count;
	private String process_gauge;
	private String goodprod_rate;
	private String badprod_rate;
	
	public ProcessBean(String prodName, String good_count, String bad_count, String issue_count) 
	{
		this.prodName = prodName;
		this.good_count = good_count;
		this.bad_count = bad_count;
		this.issue_count = issue_count;
	}
	public ProcessBean(String process_gauge) 
	{
		this.process_gauge = process_gauge;
	}
	public ProcessBean(String goodprod_rate, String badprod_rate)
	{
		this.goodprod_rate = goodprod_rate;
		this.badprod_rate = badprod_rate;
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

	public String getProcess_gauge() {
		return process_gauge;
	}

	public void setProcess_gauge(String process_gauge) {
		this.process_gauge = process_gauge;
	}
	public String getGoodprod_rate() {
		return goodprod_rate;
	}
	public void setGoodprod_rate(String goodprod_rate) {
		this.goodprod_rate = goodprod_rate;
	}
	public String getBadprod_rate() {
		return badprod_rate;
	}
	public void setBadprod_rate(String badprod_rate) {
		this.badprod_rate = badprod_rate;
	}
	
	
	
}