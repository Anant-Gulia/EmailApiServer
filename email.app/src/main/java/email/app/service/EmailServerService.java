package email.app.service;

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
import email.app.respository.EmailCredentialsRepository;
import email.app.respository.InboxRepository;
import email.app.respository.OutboxRepository;

@Service
@Transactional
public class EmailServerService {

	@Autowired
	private EmailCredentialsRepository emailCredentialsRepository;

	@Autowired
	private InboxRepository inboxRepository;

	@Autowired
	private OutboxRepository outboxRepository;

	public LoginResponse loginUser(LoginRequest request) {
		EmailCredentials userCredentialsDb = emailCredentialsRepository.getCredentialsUsingEmail(request.getEmail());
		LoginResponse response = new LoginResponse();

		if (userCredentialsDb == null) {
			response.setError(true);
			response.setErrorMessage("Email not found.");
			return response;
		}

		if (userCredentialsDb.getPassword().equals(request.getPassword())) {
			String apiKey = Utils.createApiKey(10);

			emailCredentialsRepository.saveApiKey(apiKey, userCredentialsDb.getEmailCredentialsId());

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
		EmailCredentials userCredentialsDb = emailCredentialsRepository.getCredentialsUsingEmail(request.getEmail());
		LogoutResponse response = new LogoutResponse();

		if (!userCredentialsDb.getApikey().equals(request.getApikey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API Key");
			return response;
		} else if (userCredentialsDb.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email not found");
			return response;
		} else {
			emailCredentialsRepository.deleteApiKey(userCredentialsDb.getEmailCredentialsId());
			response.setError(false);
			return response;
		}
	}

	public SendEmailResponse sendEmail(SendEmailRequest request) {
		EmailCredentials senderCredentialsDb = emailCredentialsRepository.getCredentialsUsingEmail(request.getSendersEmail());
		EmailCredentials receiverCredentialsDb = emailCredentialsRepository.getCredentialsUsingEmail(request.getReceiversEmail());
		SendEmailResponse response = new SendEmailResponse();

		if (senderCredentialsDb.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (!senderCredentialsDb.getApikey().equals(request.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API Key");
			return response;
		} else if (senderCredentialsDb.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Incorrect Sender's Email");
			return response;
		} else if (receiverCredentialsDb.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Incorrect Receiver's Email");
			return response;
		}

		outboxRepository.saveEmail(receiverCredentialsDb.getEmailCredentialsId(),
				senderCredentialsDb.getEmailCredentialsId(), request.getBody(), Utils.getTime());
		inboxRepository.saveEmail(receiverCredentialsDb.getEmailCredentialsId(),
				senderCredentialsDb.getEmailCredentialsId(), request.getBody(), Utils.getTime());
		response.setError(false);
		return response;
	}

	public GetEmailResponse checkInbox(GetEmailRequest request) {
		
		EmailCredentials userCredentialsDb = emailCredentialsRepository.getCredentialsUsingEmail(request.getEmail());
		GetEmailResponse response = new GetEmailResponse();
		
		if (userCredentialsDb.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (!userCredentialsDb.getApikey().equals(request.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (userCredentialsDb.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email Not Found");
			return response;
		}
		
		response.setError(false);
		response.setEmails(inboxRepository.getEmails(userCredentialsDb.getEmailCredentialsId()));
		return response;
	}

	public CheckOutboxResponse checkOutbox(GetEmailRequest request) {
		EmailCredentials userDbCredentials = emailCredentialsRepository.getCredentialsUsingEmail(request.getEmail());
		CheckOutboxResponse response = new CheckOutboxResponse();
		
		if (userDbCredentials.getApikey() == null) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (!userDbCredentials.getApikey().equals(request.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("User Not Logged In");
			return response;
		} else if (userDbCredentials.getEmail() == null) {
			response.setError(true);
			response.setErrorMessage("Email Not Found");
			return response;
		}
		
		response.setError(false);
		response.setEmails(outboxRepository.getEmails(userDbCredentials.getEmailCredentialsId()));
		return response;
	}
}
