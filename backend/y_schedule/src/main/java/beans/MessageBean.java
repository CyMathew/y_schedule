package beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class MessageBean {

	@Id
	@Column
	@SequenceGenerator(initialValue= 1 , allocationSize = 1, sequenceName="MessageBeanSEQ", name="MessageBeanSEQ")
	@GeneratedValue(generator="MessageBeanSEQ", strategy=GenerationType.SEQUENCE)
	private Integer message_id;
	@ManyToOne
	private UserBean userID;
	@Column
	private String message;
	@Column
	private Timestamp sentTime;
	@ManyToOne
	private MessageListBean messageListID;
	
	public MessageBean() {
		super();
	}
	public MessageBean(UserBean userID, String message, Timestamp sentTime, MessageListBean messageListID) {
		this.userID = userID;
		this.message = message;
		this.sentTime = sentTime;
		this.messageListID = messageListID;
	}

	public MessageBean(Integer message_id, UserBean userID, String message, Timestamp sentTime,
			MessageListBean messageListID) {
		super();
		this.message_id = message_id;
		this.userID = userID;
		this.message = message;
		this.sentTime = sentTime;
		this.messageListID = messageListID;
	}
	public Integer getMessage_id() {
		return message_id;
	}

	public MessageListBean getMessageListID() {
		return messageListID;
	}

	public void setMessageListID(MessageListBean messageListID) {
		this.messageListID = messageListID;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public UserBean getuserID() {
		return userID;
	}
	
	public void setuserID(UserBean userID) {
		this.userID = userID;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Timestamp getSentTime() {
		return sentTime;
	}
	
	public void setSentTime(Timestamp sentTime) {
		this.sentTime = sentTime;
	}
}
