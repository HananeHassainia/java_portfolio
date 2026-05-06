<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@ include file="/template/link.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/accueil.css" />

<title>Nom du site | accueil</title>
</head>
<body>
	<%@ include file="/template/headerAccueilInterface.html"%>
	<h1 id="messageDeco">${msgDeco}</h1>
	<section id="sliderHome">
		<div id="container">
			<div class="articleHide test" id="page1">
				<div class="codePromo">
					<p>Promotion exceptionnelle</p>
					<p id="pourcent">-30% sur toute la boutique avec le code :</p>
					<p id="code">Nomane2022</p>
				</div>
				<img src="./assets/images/photoAccueilSlide.png" />
			</div>
			<div class="articleHide test" id="page2">
				<article>
					<div>
						<p>Russe : MiG-25</p>
						<p>avion d'interception et de reconnaissance soviétique, connu
							sous le nom de code OTAN « Foxbat ». Avec le MiG-31, il est le
							seul avion de combat capable d'atteindre une vitesse de Mach 3 à
							avoir été mis en service dans le monde.</p>

						<img src="./assets/images/russe.png" />
					</div>
					<div id="slide2">
						<p>Rafale</p>
						<p>avion à aile delta et plans canard, à commandes de vol
							électriques et utilise des éléments de furtivité passifs et
							actifs</p>
						<img id="slide2Img" src="./assets/images/rafal.png" />

					</div>

					<div>
						<p>Mirage</p>
						<p>avion militaire conçu et construit en France par Dassault.
							Il se distingue des autres avions de la famille des Mirage par
							l'utilisation d'une aile en flèche.</p>
						<img id="slide2Img" src="./assets/images/mirage.png" />

					</div>
				</article>

			</div>
			
			<div style="text-align: center">
				<span class="dot active" id="first"></span> <span class="dot"
					id="second"></span>
			</div>
		</div>
		<span class="bouton" id="d"> &#10095; </span> <span class="bouton"
			id="g"> &#10094;</span>

	</section>

	<section id="homeSection2">
		<c:forEach var="cat" items="${listCat}">
			<div>
				<p>${cat.nomCategorie}</p>
				<p>${cat.description}</p>
				<img width="60%" src="${cat.image}" alt="Image Categorie "+${cat.nomCategorie} />
			</div>
		</c:forEach>
	</section>

	<%@ include file="/template/footer.html"%>

</body>
</html>