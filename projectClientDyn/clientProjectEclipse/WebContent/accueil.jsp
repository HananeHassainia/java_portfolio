<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/headerAccueil.css">

<link rel="stylesheet" href="./assets/css/accueil.css">

</head>
<body>
	<%@ include file="./template/headerAccueil.html"%>

	<section class="presentationA">
		<div>
			<p>Gestion Clients et comptes banquaires</p>
			<p>Projet JSP servlet</p>
		</div>
		<img src="./assets/image/imageSectionA.png" alt="imageFd">
	</section>

	<section class="informationsA">
		<article>
			<img src="./assets/image/iconClt.png" alt="iconClient">
			<p>Gestion des clients</p>

		</article>
		<article>
			<img src="./assets/image/iconCarte.png" alt="iconCarte">
			<p>Gestion des comptes</p>
		</article>
		<article>
			<img src="./assets/image/iconeTransfert.png" alt="iconTransfert">
			<p>Transactions</p>
		</article>
		
	</section>
	<%@ include file="./template/footer.html"%>

</body>
</html>