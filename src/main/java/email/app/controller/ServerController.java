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

import email.app.service.EmailServerService;

@RestController
public class ServerController {

	@Autowired
	private EmailServerService emailServerService;

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody LoginRequest request) {
		return emailServerService.loginUser(request);
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public LogoutResponse logout(@RequestBody LogoutRequest request) {
		System.out.println("Logout called");
		return emailServerService.logoutUser(request);
	}
	
	@RequestMapping(path = "/sendEmail", method = RequestMethod.POST)
	public SendEmailResponse sendEmail(@RequestBody SendEmailRequest request) {
		System.out.println("SendEmail called");
		return emailServerService.sendEmail(request);
	}
	
	@RequestMapping(path = "/checkInbox", method = RequestMethod.POST)
	public GetEmailResponse getEmail(@RequestBody GetEmailRequest request) {
		System.out.println("GetEmail called");
		return emailServerService.checkInbox(request);
	}
	
	@RequestMapping(path = "/checkOutbox", method = RequestMethod.POST)
	public CheckOutboxResponse checkOutbox(@RequestBody GetEmailRequest request) {
		System.out.println("checkOutbox called");
		return emailServerService.checkOutbox(request);
	}
}