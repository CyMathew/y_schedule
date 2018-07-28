package beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name="getTimes", query="FROM EmployeeAvailability WHERE userid = :id")
})

@NamedNativeQueries({
	@NamedNativeQuery(
			/*
			 * Within a NativeQuery you are writing the ACTUAL
			 * SQL that your engine is using. NOT HQL.
			 */
				name="removeRequests",
				query="DELETE * FROM EmployeeAvailability WHERE userid = :id"
			)
})

@Entity 
@Table(name="EmployeeAvailability") 
public class EmployeeAvailabilityBean {
	
	@Column
	private Timestamp start;
	@Column
	private Timestamp end;
	@Column
	private Integer userid;
	@Column
	private String approved;
	
	public EmployeeAvailabilityBean(String start, String end, Integer userid) {
		this.start  = Timestamp.valueOf(start);
		this.end    = Timestamp.valueOf(end);
		this.userid = userid;
		this.approved = "false";
	}

	public EmployeeAvailabilityBean() {
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
