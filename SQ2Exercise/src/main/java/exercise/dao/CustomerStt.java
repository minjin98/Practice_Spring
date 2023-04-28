package exercise.dao;

import java.time.LocalDateTime;

import exercise.exceptions.WrongIdPasswordException;

public class CustomerStt {
	private String grade;
	private Long cnt;
	private Float avg;
	private Long max;
	private Long min;
	
	public CustomerStt(String grade, Long cnt, Float avg, Long max, Long min) {
		this.grade = grade;
		this.cnt = cnt;
		this.avg = avg;
		this.max = max;
		this.min = min;
	}

	@Override
	public String toString() {
		return "CustomerStt [grade=" + grade + ", cnt=" + cnt + ", avg=" + avg + ", max=" + max + ", min=" + min + "]";
	}

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	
	public Float getAvg() {
		return avg;
	}
	public void setAvg(Float avg) {
		this.avg = avg;
	}

	
	public Long getMax() {
		return max;
	}
	public void setMax(Long max) {
		this.max = max;
	}


	public Long getMin() {
		return min;
	}
	public void setMin(Long min) {
		this.min = min;
	}
}
