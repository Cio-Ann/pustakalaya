package cgr.cgfsdam.pustakalaya.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.utility.Mail;

@Service
public class MailService {

	@Autowired
	JavaMailSender emailSender;
	
	public void sendSimpleMessage(final Mail mail) {
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setFrom(mail.getFrom());
		smm.setTo(mail.getTo());
		smm.setSubject(mail.getSubject());
		smm.setText(mail.getBody());
		
		emailSender.send(smm);
	}

}
