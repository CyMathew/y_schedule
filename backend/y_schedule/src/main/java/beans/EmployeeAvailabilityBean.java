package beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
@NamedQueries({
	@NamedQuery(name="getTimes", query="FROM EmployeeAvailability WHERE day = :day"),
	@NamedQuery(name="getAvail", query="FROM EmployeeAvailability WHERE user_id = :id")
})
*/


@Entity 
@Table(name="EmployeeAvailability") 

	
public class EmployeeAvailabilityBean {

	@Column
	private float starttime;
	@Column
	private float endtime;
	@ManyToOne
	private UserBean user;
	@Column
	private String day;
	@Id
	@Column
	@SequenceGenerator(initialValue= 1 , allocationSize = 1, sequenceName="shiftId", name="shiftidseq")
	@GeneratedValue(generator="shiftidseq", strategy=GenerationType.SEQUENCE)
	private Integer shift_id;
	
	@Column
	private int active = 1;

	
	public EmployeeAvailabilityBean(float starttime, float endtime, UserBean userid, String day) {
		this.starttime  = starttime;
		this.endtime    = endtime;
		this.user = userid;
		this.day = day;
	}

	public EmployeeAvailabilityBean() {
		super();
	}

	public float getStart() {
		return starttime;
	}

	public void setStart(float starttime) {
		this.starttime = starttime;
	}

	public float getEnd() {
		return endtime;
	}

	public void setEnd(float endtime) {
		this.endtime = endtime;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EmployeeAvailabilityBean [starttime=" + starttime + ", endtime=" + endtime + ", user=" + user + ", day="
				+ day + ", shift_id=" + shift_id + ", active=" + active + "]";
	}
	
	
	
}
