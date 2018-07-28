package beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name="getTimes", query="FROM EmployeeAvailability WHERE day = :day"),
	@NamedQuery(name="getAvail", query="FROM EmployeeAvailability WHERE user_id = :id")
})



@Entity 
@Table(name="EmployeeAvailability") 
public class EmployeeAvailabilityBean {
	
	@Column
	private String start;
	@Column
	private String end;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserBean user;
	@Column
	private String day;

	
	public EmployeeAvailabilityBean(String start, String end, UserBean userid, String day) {
		this.start  = start;
		this.end    = end;
		this.user = userid;
		this.day = day;
	}

	public EmployeeAvailabilityBean() {
		super();
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

    @ManyToOne(cascade=CascadeType.ALL)  
	public UserBean getUser() {
		return user;
	}

	public void setUserid(UserBean user) {
		this.user = user;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
}
