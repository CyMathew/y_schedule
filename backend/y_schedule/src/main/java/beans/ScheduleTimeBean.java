package beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
				name="showUserIdAndTime",
				query="SELECT user_table.user_id, user_table.store_Id, ScheduleTime.start, ScheduleTime.end"
						+ "FROM user_table INNER JOIN ScheduleTime "
						+ "ON user_table.user_id = ScheduleTime.user_id"
						+ "WHERE store_Id = :id AND start = :startDate AND end =endDate"
			),
	@NamedNativeQuery(
			name="showEmployee",
			query="SELECT user_table.user_id, user_table.store_Id, ScheduleTime.start, ScheduleTime.end"
					+ "FROM user_table INNER JOIN ScheduleTime "
					+ "ON user_table.user_id = ScheduleTime.user_id"
					+ "WHERE user_id = :id "
			)
})


@Entity
@Table(name = "ScheduleTime")
public class ScheduleTimeBean {
	
	@Id
	@Column
	@SequenceGenerator(initialValue= 1 , allocationSize = 1, sequenceName="ScheduleTimeBeanSEQ", name="ScheduleTimeBeanSEQ")
	@GeneratedValue(generator="ScheduleTimeBeanSEQ", strategy=GenerationType.SEQUENCE)
	private Integer Schedule_id;
	@Column
	private Timestamp startTime;
	@Column
	private Timestamp endTime;
	@ManyToOne
	private UserBean users;
	
	public ScheduleTimeBean(Integer schedule_id, Timestamp start, Timestamp end, UserBean user) {
		super();
		this.Schedule_id = schedule_id;
		this.startTime = start;
		this.endTime = end;
		this.users = user;
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
		return startTime;
	}
	public void setStart(Timestamp start) {
		this.startTime = start;
	}
	public Timestamp getEnd() {
		return endTime;
	}
	public void setEnd(Timestamp end) {
		this.endTime = end;
	}
	public UserBean getUser() {
		return users;
	}
	public void setUser(UserBean user) {
		this.users = user;
	}
	
	
}
