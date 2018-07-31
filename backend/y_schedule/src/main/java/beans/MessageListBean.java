package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column
	private Integer user1;
	@Column
	private Integer user2;
	
	public MessageListBean(Integer user1, Integer user2) {
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public Integer getMessage_list_id() {
		return message_list_id;
	}
	
	public void setMessage_list_id(Integer message_list_id) {
		this.message_list_id = message_list_id;
	}
	
	public Integer getUser1() {
		return user1;
	}
	
	public void setUser1(Integer user1) {
		this.user1 = user1;
	}
	
	public Integer getUser2() {
		return user2;
	}
	
	public void setUser2(Integer user2) {
		this.user2 = user2;
	}
}
