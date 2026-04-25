<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout page</title>
<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">
</head>
<body>
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navClt.html"%>


	<form class="inputAjout" method="post" action="addClt">
		<div class="titre">
			<p>Ajouter un client</p>
		</div>
		<div class="column1Input">
			<span class="mb-3"> <label class="form-label" for="idNom">Nom</label>

				<input type="text" class="form-control" id="idNom" placeholder="Nom"
				name="pNom" required>
			</span> <span class="mb-3"> <label class="form-label" for="idPrenom">Prenom</label>

				<input type="text" class="form-control" id="idPrenom"
				placeholder="prenom" name="pPrenom" required>
			</span> <span class="mb-3"> <label class="form-label" for="idBirth">Date
					de naissance</label> <input type="date" class="form-control" id="idBirth"
				name="pDate" required>
			</span> <span class="mb-3"> <label class="form-label" for="idAdresse">Adresse</label>

				<input type="text" class="form-control" id="idAdresse"
				placeholder="Adresse" name="pAdresse" required>
			</span>
		</div>

		<div class="column2Input">
			<span class="mb-3"> <label class="form-label" for="idCp">Code
					Postale</label> <input type="number" class="form-control" id="idCp"
				placeholder="Code Postal" name="pCp" required>
			</span> <span class="mb-3"> <label class="form-label" for="idVille">Ville</label>

				<input type="text" class="form-control" id="idVille"
				placeholder="Ville" name="pVille" required>
			</span> <span class="mb-3"> <label class="form-label" for="idTel">Numéro
					de téléphone</label> <input type="tel" class="form-control" id="idTel"
				placeholder="07 58 58 58 58" pattern="[0]{1}[0-9]{9}" name="pTel"
				required>
			</span> <span class="mb-3"> <label class="form-label" for="idMail">Adresse
					email</label> <input type="email" class="form-control" id="idMail"
				placeholder="adresse mail" name="pMail" required>
			</span>
		</div>

		<div class="containerBtnInput">
			<button type="submit" id="inputAjout" class="btn btn-success">Ajouter</button>
		</div>

		<h1 style="color: red; text-align: center;">${msg}</h1>
	</form>
	<%@ include file="./template/footer.html"%>

</body>
</html>