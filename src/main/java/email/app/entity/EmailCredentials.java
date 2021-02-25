package email.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_CREDENTIALS")
public class EmailCredentials {

	@Id
	@Column(name = "EMAIL_CREDENTIALS_ID")
	private Integer emailCredentialsId;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "APIKEY")
	private String apikey;

	public Integer getEmailCredentialsId() {
		return emailCredentialsId;
	}

	public void setEmailCredentialsId(Integer emailCredentialsId) {
		this.emailCredentialsId = emailCredentialsId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public EmailCredentials(Integer emailCredentialsId, String email, String password, String apikey) {
		super();
		this.emailCredentialsId = emailCredentialsId;
		this.email = email;
		this.password = password;
		this.apikey = apikey;
	}

	public EmailCredentials() {
		super();
	}

}
