package beans;

import java.sql.Timestamp;

import javax.persistence.Column;

public class AvailableBean {
	
	@Column
	private Timestamp start;
	@Column
	private Timestamp end;
	@Column
	private Integer userid;
	
	public AvailableBean(String start, String end, Integer userid) {
		this.start  = Timestamp.valueOf(start);
		this.end    = Timestamp.valueOf(end);
		this.userid = userid;
	}

	public AvailableBean() {
		super();
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
}
