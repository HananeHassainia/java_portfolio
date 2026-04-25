package fr.adaming.utils;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import fr.adaming.entities.Client;
import fr.adaming.entities.Compte;
import fr.adaming.entities.Conseiller;

public class CreatePdf {

	public static void newPdf(String typeCpt, Client clt, Compte cpt, Conseiller cons) {
		// Chemin du dossier dans lequel on veut stoquer le fichier
//		String nameFile = clt.getNom()+clt.getPrenom()+String.valueOf(clt.getId())+typeCpt;
		String filePath = "c:/USERS/IN-TR-019/Desktop/Formation/07_Autres/01_projetJSPservlet/"+typeCpt+".pdf";

		try {
			// Déclaration et instanciation de l'objet PdfWriter : permet d'écrire dans le
			// pdf
			PdfWriter writer = new PdfWriter(filePath);

			// Déclaration et instanciation de l'objet PdfDocument
			PdfDocument pdfDoc = new PdfDocument(writer);

			//Ajouter une page vide
			pdfDoc.addNewPage(); 
			
			//Créer  un document 
			Document doc = new Document(pdfDoc); 
			
			//adding paragraphs to the PDF  
			doc.add(new Paragraph("Nouveau client")); 
			doc.add(new Paragraph("------------------------------------")); 
			doc.add(new Paragraph(clt.getNom()+" "+clt.getPrenom()));
			doc.add(new Paragraph("Numéro client : "+clt.getId())); 
			doc.add(new Paragraph("                           ")); 
			doc.add(new Paragraph("Pour vous connecter à votre espace perso : "));
			doc.add(new Paragraph("------------------------------------")); 

			doc.add(new Paragraph("votre mail :"+clt.getMail()));
			doc.add(new Paragraph("mot de passe :"+clt.getMdp()+" ce mot de passe pourra être changé dans votre espace client"));
			doc.add(new Paragraph("                           ")); 
			
			doc.add(new Paragraph("Information de votre compte : "));
			doc.add(new Paragraph("------------------------------------")); 

			doc.add(new Paragraph("Numéro de compte :"+cpt.getId()));

			//Ajouter des paragraphs
	 
			//closes the document  
			doc.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
	}
}
