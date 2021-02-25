package email.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CheckOutboxResponse;
import com.app.model.GetEmailRequest;
import com.app.model.GetEmailResponse;
import com.app.model.LoginRequest;
import com.app.model.LoginResponse;
import com.app.model.LogoutRequest;
import com.app.model.LogoutResponse;
import com.app.model.SendEmailRequest;
import com.app.model.SendEmailResponse;

import email.app.entity.EmailCredentials;
import email.app.entity.EmailInbox;
import email.app.entity.EmailOutbox;
import email.app.respository.CredentialsRepository;
import email.app.respository.InboxRepository;
import email.app.respository.OutboxRepository;

@Service
@Transactional 
public class DataService {

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Autowired
	private InboxRepository inboxRepository;

	@Autowired
	private OutboxRepository outboxRepository;

	public LoginResponse loginUser(LoginRequest request) {
		EmailCredentials userDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getEmail());
		LoginResponse response = new LoginResponse();
		
		if (userDbCredentials == null) {
			response.setError(true);
			response.setErrorMessage("Email not found.");
			return response;
		}
		
		if (userDbCredentials.getPassword().equals(request.getPassword())) {
			String apiKey = Utils.createApiKey(10);
			
			credentialsRepository.saveApiKey(apiKey, userDbCredentials.getEmailCredentialsId());
			
			response.setApikey(apiKey);
			response.setError(false);
			return response;
		} else {
			response.setError(true);
			response.setErrorMessage("Password incorrect.");
			return response;
		}
	}
	
	public LogoutResponse logoutUser(LogoutRequest request) {
		EmailCredentials userDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getEmail());
		LogoutResponse response = new LogoutResponse();
		if(!userDbCredentials.getApikey().equals(request.getApikey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API Key");
			return response;
		}else if(userDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email not found");
			return response;
		} else {
			credentialsRepository.deleteApiKey(userDbCredentials.getEmailCredentialsId());
			response.setError(false);
			return response;
		}
	}
	
	public SendEmailResponse sendEmail(SendEmailRequest request) {
		EmailCredentials senderDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getSendersEmail());
		EmailCredentials receiverDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getReceiversEmail());
		SendEmailResponse response = new SendEmailResponse();
		if(senderDbCredentials.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		}else if(!senderDbCredentials.getApikey().equals(request.getApiKey())){
			response.setError(true);
			response.setErrorMessage("Incorrect API Key");
			return response;
		} else if(senderDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Incorrect Sender's Email");
			return response;
		} else if(receiverDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Incorrect Receiver's Email");
			return response;
		} else {
			outboxRepository.saveEmail(receiverDbCredentials.getEmailCredentialsId(), senderDbCredentials.getEmailCredentialsId(), request.getBody(), Utils.getTime());
			inboxRepository.saveEmail(receiverDbCredentials.getEmailCredentialsId(), senderDbCredentials.getEmailCredentialsId(), request.getBody(), Utils.getTime());
			response.setError(false);
			return response;
		}
	}
	
	public GetEmailResponse checkInbox(GetEmailRequest request) {
		EmailCredentials userDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getEmail());
		GetEmailResponse response = new GetEmailResponse();
		if (userDbCredentials.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if(!userDbCredentials.getApikey().equals(request.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (userDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email Not Found");
			return response;
		} else {
			response.setError(false);
			response.setEmails(inboxRepository.getEmails(userDbCredentials.getEmailCredentialsId()));
			return response;
		}
	}
	
	public CheckOutboxResponse checkOutbox(GetEmailRequest request) {
		EmailCredentials userDbCredentials = credentialsRepository.getCredentialsUsingEmail(request.getEmail());
		CheckOutboxResponse response = new CheckOutboxResponse();
		if (userDbCredentials.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if(!userDbCredentials.getApikey().equals(request.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (userDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email Not Found");
			return response;
		} else {
			response.setError(false);
			response.setEmails(outboxRepository.getEmails(userDbCredentials.getEmailCredentialsId()));
			return response;
		}
	}

	public List<EmailInbox> listAllInbox() {
		return inboxRepository.getAllRows();
	}

	public List<EmailOutbox> listAllOutbox() {
		return outboxRepository.getAllRows();
	}
}
