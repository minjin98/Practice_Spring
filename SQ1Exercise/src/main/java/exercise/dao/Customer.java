package exercise.dao;

public class Customer {
	private String id;
	private String name;
	private String tel;
	private String postcd;
	private String area;
	private String point;
	
	
	public Customer(String id, String name, String tel, String postcd, String area, String point) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.postcd = postcd;
		this.area = area;
		this.point = point;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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

	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	

}
