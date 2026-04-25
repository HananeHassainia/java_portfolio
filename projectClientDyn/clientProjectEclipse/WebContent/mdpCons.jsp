<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@ include file="./template/link.html"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">
</head>
<body>
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCons.html"%>


	<Form class="inputAjout" method="post" action="updateMdpCons">
		<div class="titre">
			<p>Changer le mot de passe</p>
		</div>
		<p class="mdpSession" style="display: none">${cSession.mdp}</p>
		<span class="mb-3"> <label for="idmdpOld"
			class="form-label">Mot de passe actuel* :</label> <input
			type="password" class="form-control" id="idmdpOld"
			placeholder="Mot de passe actuel" name="pMdpOld" required> 

		</span> <span class="mb-3"> <label for="idmdpNew" class="form-label">Nouveau
				mot de passe* :</label> <input type="password" min="5" class="form-control"
			style="margin-bottom: 0px;" id="idmdpNew"
			placeholder="Nouveau mot de passe" name="pMdpNew" required disabled>

		</span>
	

		<span class="mb-3" id="testmdp"> <label
			for="idmdpConfirm" class="form-label">Confirmation
				du nouveau mot de passe* :</label> <input type="password" min="5" class="form-control"
			id="idmdpConfirm" placeholder="Nouveau mot de passe"
			name="pIdmdpConfirm" required disabled>  
		</span>

		<div class="containerBtnInput"
			style="margin-top: 40px; margin-left: 90px">
			<button type="submit" id="btnMdp" class="btn btn-success">Enregistrer
				les modifications</button>
		</div>

	</Form>
		<%@ include file="./template/footer.html"%>
	
</body>
</html>