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
	@Column
	private Integer uID;
	@Column
	private String message;
	@Column
	private Timestamp sentTime;
	@ManyToOne
	private MessageListBean message_list_id;
	
	public MessageBean(Integer uID, String message, Timestamp sentTime) {
		this.uID = uID;
		this.message = message;
		this.sentTime = sentTime;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getuID() {
		return uID;
	}
	
	public void setuID(Integer uID) {
		this.uID = uID;
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
