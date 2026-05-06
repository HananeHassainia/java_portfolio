package fr.adaming.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fr.adaming.entities.Client;

public class Mail {

//	// Test méthodes dans console
//	public static void main(String[] args) {
//
//		sendMail();
//
//	}

	// Méthode envoi de mail de confirmation suite à inscription client
	public static void sendMailVerif(Client client) {

		// Recipient's email ID needs to be mentioned.
		String to = client.getMail();

		// Sender's email ID needs to be mentioned
		String from = "hananehananehanane46@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("hananehananehanane46@gmail.com", "avionProjet3306");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Confirmation d'inscription");

			// Now set the actual message
			message.setText("Votre compte a bien été créé sur notre site Java&Rien. \n -Ce message est généré automatiquement, merci de ne pas y répondre-");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	// Méthode envoi de mail avec pdf suite à commande client
	public static void sendMail(Client client) {

		// Recipient's email ID needs to be mentioned.
		String to = client.getMail();
		System.out.println("mail :"+to);
		// Sender's email ID needs to be mentioned
		String from = "hananehananehanane46@gmail.com";

		// Assuming you are sending email from through gmails smtp (SIMPLE MAIL TRANSFERT PROTOCOL)
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("hananehananehanane46@gmail.com", "avionProjet3306");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set randomizer
//			Random rand = new Random();
//			int upperbound = 999999;
//			int int_random = rand.nextInt(upperbound);
			
			int val = Math.abs(client.getId().hashCode() + client.getNom().hashCode() + client.getPrenom().hashCode());

			// Set Subject: header field
			message.setSubject("Devis n°" + val);

			// Paramétrer la pièce jointe
			BodyPart pdf = new MimeBodyPart();

			// Paramétrer le texte du corps du mail
			pdf.setText("Merci pour votre commande. Vous trouverez les détails dans le PDF joint à ce mail. \n -Ce message est généré automatiquement, merci de ne pas y répondre-");

			//
			Multipart mp = new MimeMultipart();

			// Ajouter le BodyPart à l'objet Multipart
			mp.addBodyPart(pdf);

			// Attacher pdf au mail
			pdf = new MimeBodyPart();

			// Nom du fichier		
			String s = System.getProperty("user.home")+"\\"+"Desktop"+"\\"+"pdf"+ "\\"+"Facture"+ client.getNom() + client.getPrenom()+".pdf";
			System.out.println("s : "+s);
//			File filetest = new File("./src/main/webapp/assets/pdf/Facture"+client.getNom()+client.getPrenom()+".pdf");
//			String filename = filetest.getCanonicalPath();

			
			DataSource source = new FileDataSource(s);

			pdf.setDataHandler(new DataHandler(source));
			pdf.setFileName(s);

			mp.addBodyPart(pdf);
			message.setContent(mp);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
	public static void main(String[] args) {
		String s = System.getProperty("user.home")+"\\"+"Desktop"+"\\"+"pdf";
		System.out.println("s : "+s);
	}

}
