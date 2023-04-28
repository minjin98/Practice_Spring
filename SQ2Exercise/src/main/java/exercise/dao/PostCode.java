package exercise.dao;

public class PostCode {
	private String postcd;
	private String area;
	
	public PostCode(String postcd, String area) {
		this.postcd = postcd;
		this.area = area;
	}
	public String getPostcd() {
		return postcd;
	}
	public void setPostcd(String postcd) {
		this.postcd = postcd;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
