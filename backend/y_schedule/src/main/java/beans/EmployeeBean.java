package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeInfo")
public class EmployeeBean {

	@Id 
	@Column
	private Integer user_id;
	@Column
	private String department;
	@Column
	private String tittle;
	@Column
	private Integer workableHours;
	@Column
	private Integer hourlyWage;
	@OneToOne 
	@MapsId 
	UserBean user;
	
	
	public EmployeeBean(Integer user_id, String department, String tittle, Integer workableHours, Integer hourlyWage) {
		super();
		this.user_id = user_id;
		this.department = department;
		this.tittle = tittle;
		this.workableHours = workableHours;
		this.hourlyWage = hourlyWage;
	}

	public EmployeeBean() {
		super();
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Integer getWorkableHours() {
		return workableHours;
	}

	public void setWorkableHours(Integer workableHours) {
		this.workableHours = workableHours;
	}

	public Integer getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(Integer hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

}
