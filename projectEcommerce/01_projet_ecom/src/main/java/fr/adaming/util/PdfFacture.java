package fr.adaming.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import fr.adaming.entities.Client;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;

public class PdfFacture {
	// generer la facture du client

	public static void newPdf(Client clt, Panier panier, boolean promo) {
		// specifier le le chemin et le nom du pdf
		String nomPdf = System.getProperty("user.home") + "\\Desktop\\pdf\\Facture" + clt.getNom() + clt.getPrenom()
				+ ".pdf";
		File fileName = new File(nomPdf);
		String filePath = fileName.getAbsolutePath();

		try {

			// Déclaration et instanciation de l'objet PdfWriter : permet d'écrire dans le
			// pdf
			PdfWriter writer = new PdfWriter(filePath);

			// Déclaration et instanciation de l'objet PdfDocument
			PdfDocument pdfDoc = new PdfDocument(writer);

			// Ajouter une page vide
			pdfDoc.addNewPage();
			// Créer un document
			Document document = new Document(pdfDoc);
			// Ajout du contenu au document

			// Ajouter le logo de notre site:
			String nomImg = System.getProperty("user.home") + "\\Desktop\\images\\log2.png";

			File fileImage = new File(nomImg);
			String imageFile = fileImage.getAbsolutePath();
			ImageData data = ImageDataFactory.create(imageFile);
			Image img = new Image(data);
			img.scaleAbsolute(150f, 150f);
			img.setFixedPosition(390, 670);
			document.add(img);

			// ajout une paragraphe au pdf
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("JAVA&RIEN"));
			document.add(new Paragraph("PARIS, LA DEFENSE"));
			document.add(new Paragraph("Avenue de Paris,92060, Paris"));
			document.add(new Paragraph("contact@java&rien.com "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			// ajouter un titre
			int val = Math.abs(clt.getId().hashCode() + clt.getNom().hashCode() + clt.getPrenom().hashCode());
			document.add(new Paragraph("Facture n°:" + val));

			document.add(new Paragraph("Facture pour le client/serie : " + clt.getId()));
			document.add(new Paragraph(clt.getNom() + " " + clt.getPrenom()));
			document.add(new Paragraph(clt.getAdresse()));
			document.add(new Paragraph("Tél : " + clt.getTel()));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			document.add(new Paragraph("Cher/e " + clt.getNom() + " " + clt.getPrenom()));
			document.add(new Paragraph(
					"Nous vous remercions de votre confiance et nous espérons vous retrouver bientôt chez javaaerien. Selon les"
							+ " informations de votre commande nous vous facturons la somme suivante:"));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			// Ajout de table de facture

			int col=0;
			
			if(promo==true) {
				col=5;
			}
			else {
				col=4;
			}
			
			float[] f = new float[col];
			Table table = new Table(f);
			
			if(promo==false) {
				float[] pointColumnWidths = { 150F, 150F, 150F, 150F};
				Table t = new Table(pointColumnWidths);
				table = t;
			}
			else {
				float[] pointColumnWidths = { 150F, 150F, 150F, 150F, 150F};
				Table t = new Table(pointColumnWidths);
				table = t;
			}
		

			// creer les cellules
			table.addCell(new Cell().add(new Paragraph("ID produit")));
			table.addCell(new Cell().add(new Paragraph("Quantité")));
			table.addCell(new Cell().add(new Paragraph("Prix Unitaire")));
			table.addCell(new Cell().add(new Paragraph("Total (€)")));

			if (promo == true) {
				table.addCell(new Cell().add(new Paragraph("Total avec réduction (€)")));

			}

			double somme = 0;
			for (int i = 0; i < panier.getLignesCommandes().size(); i++) {
				String elem1 = Long.toString(panier.getLignesCommandes().get(i).getProduit().getId());
				String elem2 = Integer.toString(panier.getLignesCommandes().get(i).getQuantite());
				String elem3 = Double.toString(panier.getLignesCommandes().get(i).getPrix());
				double elem4 = (panier.getLignesCommandes().get(i).getPrix()
						/ panier.getLignesCommandes().get(i).getQuantite());
				String elem = Double.toString(elem4);
				table.addCell(new Cell().add(new Paragraph(elem1)));
				table.addCell(new Cell().add(new Paragraph(elem2)));
				table.addCell(new Cell().add(new Paragraph(elem)));
				table.addCell(new Cell().add(new Paragraph(elem3)));
				if(promo==true) {
					table.addCell(new Cell().add(new Paragraph(" ")));
				}

				somme = somme + panier.getLignesCommandes().get(i).getPrix();
			}

			table.addCell(new Cell().add(new Paragraph(" ")));
			table.addCell(new Cell().add(new Paragraph(" ")));
			table.addCell(new Cell().add(new Paragraph(" ")));
			String total = Double.toString(somme);
			table.addCell(new Cell().add(new Paragraph(total)));

			if (promo == true) {
				double totalPromo = somme-(somme * 0.3);
				String totalFinal = Double.toString(totalPromo);

				table.addCell(new Cell().add(new Paragraph(totalFinal)));
			}

			document.add(table);

			// fermeture de document pdf
			document.close();

		} catch (FileNotFoundException | MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws MalformedURLException {

		// tester la fonction
		LigneCommande ls1 = new LigneCommande(2, 1500);
		LigneCommande ls2 = new LigneCommande(5, 2000);
		LigneCommande ls3 = new LigneCommande(10, 5000);

		Produit p1 = new Produit();
		p1.setId((long) 1);
		ls1.setProduit(p1);

		Produit p2 = new Produit();
		p2.setId((long) 2);
		ls2.setProduit(p1);

		Produit p3 = new Produit();
		p3.setId((long) 3);
		ls3.setProduit(p3);

		List<LigneCommande> liste = new ArrayList<LigneCommande>();
		liste.add(ls1);
		liste.add(ls2);
		liste.add(ls3);

		Panier panier = new Panier();
		panier.setLignesCommandes(liste);

		Client cl1 = new Client(new Long(5), "toto", "titi", "t@t", "tt", true, "adresset", "0645643219");

		System.out.println(cl1.getNom());
		newPdf(cl1, panier, true);

	}

}