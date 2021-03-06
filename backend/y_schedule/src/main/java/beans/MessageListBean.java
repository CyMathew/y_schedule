package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MessageList")
public class MessageListBean {
	
	@Id
	@Column
	@SequenceGenerator(initialValue= 1 , allocationSize = 1, sequenceName="MessageListBeanSEQ", name="MessageListBeanSEQ")
	@GeneratedValue(generator="MessageListBeanSEQ", strategy=GenerationType.SEQUENCE)
	private Integer message_list_id;
	@ManyToOne
	private UserBean user1;
	@ManyToOne
	private UserBean user2;
	
	@Override
	public String toString() {
		return "MessageListBean [message_list_id=" + message_list_id + ", user1=" + user1 + ", user2=" + user2 + "]";
	}

	public MessageListBean() {
		super();
	}
	
	public MessageListBean(UserBean user1, UserBean user2) {
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public Integer getMessage_list_id() {
		return message_list_id;
	}
	
	public void setMessage_list_id(Integer message_list_id) {
		this.message_list_id = message_list_id;
	}
	
	public UserBean getUser1() {
		return user1;
	}
	
	public void setUser1(UserBean user1) {
		this.user1 = user1;
	}
	
	public UserBean getUser2() {
		return user2;
	}
	
	public void setUser2(UserBean user2) {
		this.user2 = user2;
	}
}
