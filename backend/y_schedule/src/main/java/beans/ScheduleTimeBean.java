package beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ScheduleTime")
public class ScheduleTimeBean {
	
	@Id
	@Column
	private Integer Schedule_id;
	@Column
	private Timestamp start;
	@Column
	private Timestamp end;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserBean user;
	public ScheduleTimeBean(Integer schedule_id, Timestamp start, Timestamp end, UserBean user) {
		super();
		this.Schedule_id = schedule_id;
		this.start = start;
		this.end = end;
		this.user = user;
	}
	public ScheduleTimeBean() {
		super();
	}
	public Integer getSchedule_id() {
		return Schedule_id;
	}
	public void setSchedule_id(Integer schedule_id) {
		this.Schedule_id = schedule_id;
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
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
}
