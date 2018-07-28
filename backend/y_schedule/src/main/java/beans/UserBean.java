package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="user_table") 
public class UserBean {

	@Id
	@Column
	@SequenceGenerator(initialValue= 1 , allocationSize = 1, sequenceName="userIdSeq", name="userIdSeq")
	@GeneratedValue(generator="userIdSeq", strategy=GenerationType.SEQUENCE)
	private Integer user_id;
	@Column
	private String  user_fname;
	@Column
	private String  user_lname;
	@Column
	private String  user_username;
	@Column
	private String  user_password;
	@Column
	private Integer sec_lvl;
	@Column
	private Integer store_Id;
	
	public UserBean(String firstname, String Lastname, String password, String username) {
		this.user_fname = firstname;
		this.user_lname = Lastname;
		this.user_password = password;
		this.user_username = username;
		this.sec_lvl = 1;
		this.store_Id = 2367;
	}
	
	public UserBean() {
		super();
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_fname() {
		return user_fname;
	}
	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}
	public String getUser_lname() {
		return user_lname;
	}
	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}
	public String getUser_username() {
		return user_username;
	}
	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Integer getSec_lvl() {
		return sec_lvl;
	}
	public void setSec_lvl(Integer sec_lvl) {
		this.sec_lvl = sec_lvl;
	}

	public Integer getStoreId() {
		return store_Id;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {this.store_Id = storeId;}
		else {this.store_Id = (Integer)2367;}
	}
	
	
}
