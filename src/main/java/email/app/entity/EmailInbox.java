package email.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_INBOX")
public class EmailInbox {

	@Id
	@Column(name = "EMAIL_INBOX_ID")
	private int emailInboxId;
	@Column(name = "RECIEVER_ID")
	private int receiverId;
	@Column(name = "SENDER_ID")
	private int senderId;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "TIME")
	private LocalDateTime time;

	public EmailInbox() {
		super();
	}

	public EmailInbox(int emailInboxId, int receiverId, int senderId, String message, LocalDateTime time) {
		super();
		this.emailInboxId = emailInboxId;
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.message = message;
		this.time = time;
	}

	public int getEmailInboxId() {
		return emailInboxId;
	}

	public void setEmailInboxId(int emailInboxId) {
		this.emailInboxId = emailInboxId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
