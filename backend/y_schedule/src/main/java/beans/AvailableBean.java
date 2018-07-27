package beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
			/*
			 * Within a NativeQuery you are writing the ACTUAL
			 * SQL that your engine is using. NOT HQL.
			 */
				name="removeRequests",
				query="DELETE * FROM availabletimes WHERE userid = :id",
				resultClass=AvailableBean.class
			)
})

@Entity 
@Table(name="availabletimes") 
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
