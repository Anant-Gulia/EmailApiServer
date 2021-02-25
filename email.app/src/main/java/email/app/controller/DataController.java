package email.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CheckOutboxResponse;
import com.app.model.GetEmailRequest;
import com.app.model.GetEmailResponse;
import com.app.model.LoginRequest;
import com.app.model.LoginResponse;
import com.app.model.LogoutRequest;
import com.app.model.LogoutResponse;
import com.app.model.SendEmailRequest;
import com.app.model.SendEmailResponse;

import email.app.service.DataService;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;

//	@RequestMapping(path = "/credentials", method = RequestMethod.GET)
//	public List<EmailCredentials> listCredentials() {
//		System.out.println("Email_Credentials called");
//		List<EmailCredentials> resultSet = dataService.listAllCredentials();
//		return resultSet;
//	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody LoginRequest request) {
		return dataService.loginUser(request);
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public LogoutResponse logout(@RequestBody LogoutRequest request) {
		System.out.println("Logout called");
		return dataService.logoutUser(request);
	}
	
//	@RequestMapping(path = "/inbox", method = RequestMethod.GET)
//	public List<EmailInbox> listInbox() {
//		System.out.println("Email_Inbox called");
//		List<EmailInbox> resultSet = dataService.listAllInbox();
//		return resultSet;
//	}
//
//	@RequestMapping(path = "/outbox", method = RequestMethod.GET)
//	public List<EmailOutbox> listOutbox() {
//		System.out.println("Email_Outbox called");
//		List<EmailOutbox> resultSet = dataService.listAllOutbox();
//		return resultSet;
//	}
	
	@RequestMapping(path = "/sendEmail", method = RequestMethod.POST)
	public SendEmailResponse sendEmail(@RequestBody SendEmailRequest request) {
		System.out.println("SendEmail called");
		return dataService.sendEmail(request);
	}
	
	@RequestMapping(path = "/checkInbox", method = RequestMethod.POST)
	public GetEmailResponse getEmail(@RequestBody GetEmailRequest request) {
		System.out.println("GetEmail called");
		return dataService.checkInbox(request);
	}
	
	@RequestMapping(path = "/checkOutbox", method = RequestMethod.POST)
	public CheckOutboxResponse checkOutbox(@RequestBody GetEmailRequest request) {
		System.out.println("checkOutbox called");
		return dataService.checkOutbox(request);
	}
}