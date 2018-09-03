import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


import com.sun.mail.smtp.SMTPMessage;

import javax.activation.*;

public class MessageSender {

		String username, password, recipient, senderName, companyName, emailIntro, sponsorReason, emailSubject;
		String emailHTML = "<html ><head><meta charset= \"utf-8\"><title>Spartan Robotics Sponsorship </title></head><body><img src=\"https://keyonjerome.ca/images/image3.jpg\" height=\"200\" width=\"600\"><p>" + emailIntro + "</p>	        <p>I am a high school student at Mother Teresa Secondary School and team member of Spartan Robotics. We are a robotics team of students from grades 9 through 12 based here in London, Ontario.  Each year we participate in an international competition known as the FIRST Robotics Competition, for which we design and build a large mechanical robot with only a six-week period at our disposal. We dive deep into programming, innovation, and engineering all to give our team success at competitions. At the end of each robot build period, we, along with thousands of other schools from around the world, compete as a team in a complex new game using our robot.</p><p>Participation in this program teaches mechanical, mathematical, programming, engineering, and business skills. FIRST Robotics Competition (FRC) is a valuable program for young people such as myself to participate in. Unfortunately, participation as a team is neither free, nor cheap.</p>	        <p>Expenses include registration fees ($7500), the cost of tools, transportation fees, and the many miscellaneous costs for mechanical parts which go into the design and build of our robot.  The past success of our team would not have been possible without our community sponsors.  Currently, we are reaching out to local businesses in hopes of finding new sponsors for the 2019 FRC season, which begins this January. I feel that your company, " + companyName + ", is a perfect fit for our team&#39;s sponsorship because " + sponsorReason + ". While our expenses are large we do appreciate every contribution, monetary or otherwise, as everything helps.</p><p>Please feel free to contact me by replying to this email or emailing our lead teacher Richard Reaume at <a href=\"mailto:rreaume@ldcsb.ca\">rreaume@ldcsb.ca</a>. For more details about sponsorship and our Spartan Robotics team you can download our <a href=\"http://spartanrobotics.ca/wp-content/uploads/2017/09/Team-5288-Spartan-Robotics-Sponsorship-Proposal.pdf\">sponsorship package</a> or head to <a href=\"http://spartanrobotics.ca\">www.spartanrobotics.ca</a> to check out our website. I look forward to hearing from you soon. </p><p>Thank you for your time,</p><p>"+ senderName + "</p><p>Spartan Robotics</p><p>Mother Teresa Secondary School</p><p></p><img alt=\"robot-driver\" src=\"https://keyonjerome.ca/images/image1.jpg\" height=\"266\" width=\"400\"><img alt=\"robot\" src=\"https://keyonjerome.ca/images/image2.jpg\" height=\"266\" width=\"400\"></body></html>";
		String host = "smtp.ipage.com";
		
		public MessageSender(String username, String password, String senderName,  String recipient, String companyName, String sponsorReason, String emailIntro, String emailSubject) {
			this.username = username;
			this.password = password;
			this.senderName = senderName;
			this.companyName = companyName;
			this.sponsorReason = sponsorReason;
			this.recipient = recipient;
			this.emailIntro = emailIntro;
			this.emailSubject = emailSubject;
		}
		
		
		public String getHTML() {
			return emailHTML;
		}	
		public void sendEmail() {
		
		// email server handling
		 Properties props = new Properties();
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", host);
	     props.put("mail.smtp.port", "587");
		
		
		
	     // Get the Session object.
	     Session session = Session.getInstance(props,
	        new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(username, password);
	           }
		});
	     
	     try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(username));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	              InternetAddress.parse(recipient));

		   // Set Subject: header field
		   message.setSubject("Testing Subject");

		   // Send the actual HTML message, as big as you like
		   message.setContent(
	              getHTML(),
	             "text/html");

		   // Send message
		   Transport.send(message);

		   System.out.println("Email sent successfully to " + recipient + ".");

	      } catch (MessagingException e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
	      }
	    
	}
}
		  

