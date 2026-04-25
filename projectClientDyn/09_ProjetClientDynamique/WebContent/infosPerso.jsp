<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">

</head>
<body>
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCons.html"%>

	<form class="inputAjout" method="post" action="updateCons">
		<div class="titre">
			<p>Informations personnelles</p>
		</div>
		<div class="column1Input">
			<span class="mb-3"> <label for="idId" class="form-label">Id</label>

				<input type="number" class="form-control" id="idId" placeholder="Id"
				name="pId" value="${cSession.id}" readonly>
			</span> <span class="mb-3"> <label for="idNom" class="form-label">Nom</label>

				<input type="text" class="form-control" id="idNom" placeholder="Nom"
				name="pNom" value="${cSession.nom}">
			</span> <span class="mb-3"> <label for="idPrenom" class="form-label">Prenom</label>

				<input type="text" class="form-control" id="idPrenom"
				placeholder="prenom" name="pPrenom" value="${cSession.prenom}">
			</span> <span class="mb-3"> <label for="idAdresse" class="form-label">Adresse</label>

				<input type="text" class="form-control" id="idAdresse"
				placeholder="Adresse" name="pAdresse" value="${cSession.adresse}">
			</span>

		</div>

		<div class="column2Input">
			<span class="mb-3"> <label for="idCp" class="form-label">Code
					Postale</label> <input type="number" class="form-control" id="idCp"
				placeholder="Code Postal" name="pCp" value="${cSession.codePostal}">
			</span> <span class="mb-3"> <label for="idVille" class="form-label">Ville</label>

				<input type="text" class="form-control" id="idVille"
				placeholder="Ville" name="pVille" value="${cSession.ville}">
			</span> <span class="mb-3"> <label for="idTel" class="form-label">Numéro
					de téléphone</label> <input type="tel" class="form-control" id="idTel"
				placeholder="07 58 58 58 58" pattern="[0]{1}[0-9]{9}" name="pTel"
				value="${cSession.telephone}">
			</span> <span class="mb-3"> <label for="idMail" class="form-label">Adresse
					email</label> <input type="email" class="form-control" id="idMail"
				placeholder="adresse mail" name="pMail" value="${cSession.mail}">
			</span>
		</div>


		<div class="containerBtnInfoP">
			<button type="submit" id="inputAjout" class="btn btn-success">Modifier</button>
		</div>
		<h1 style="color: red; text-align: center;">${msg}</h1>

	</form>
	<%@ include file="./template/footer.html"%>

</body>
</html>