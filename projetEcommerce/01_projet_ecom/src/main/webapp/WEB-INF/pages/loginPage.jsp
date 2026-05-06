<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nom site || Login page</title>
<!-- specifier le chemin de fichier ma page css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/loginAdmin.css" />
</head>
<body>

	<div class="container" id="container">


		<div class="form-container sign-in-container">

			<!--       <svg xmlns="https://svgsilh.com/svg/147495.svg" class="site__logo" width="56" height="84" viewBox="77.7 214.9 274.7 412"><defs><linearGradient id="a" x1="0%" y1="0%" y2="0%"><stop offset="0%" stop-color="#8ceabb"/><stop offset="100%" stop-color="#378f7b"/></linearGradient></defs><path fill="url(#a)" d="M215 214.9c-83.6 123.5-137.3 200.8-137.3 275.9 0 75.2 61.4 136.1 137.3 136.1s137.3-60.9 137.3-136.1c0-75.1-53.7-152.4-137.3-275.9z"/></svg>
 -->
			<!--  <img src="./assets/images/pexels-photomix-company-96622.jpg" />
 -->
			<form action="connection" method="post">
				<h2>Connectez vous</h2>

				<div>
					<input type="email" placeholder="info@mailaddress.com"
						name="j_username">
				</div>

				<div>
					<input type="password" placeholder="password" name="j_password">
				</div>

				<div>
					<button id="btnConnexion" type="submit">Se connecter</button>
				</div>

			</form>
		</div>


		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Cher(e) Javiator</h1>
					<p>Entrez votre adresse mail et votre mot de passe pour vous
						connecter</p>
					<div class="nomLogo">

						<p>
							<a id="titreHeader" href="<c:url value='/intHome'/>">Java<span
								id="esp">&</span>Rien
							</a>
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>


	<%-- 	<h1 style="color: pink; text-align: center;">${msg}</h1> --%>
</body>
</html>