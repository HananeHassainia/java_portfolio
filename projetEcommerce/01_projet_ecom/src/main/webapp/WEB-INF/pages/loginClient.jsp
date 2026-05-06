<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!--  ajouter la lib core de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nom Site || Connexion</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/loginClient.css" />
<script type="text/javascript" src="./assets/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./assets/js/login.js"></script>
</head>
<body>
	<h1>${msg}</h1>
	<div class="container" id="container">
		<div class="form-container sign-up-container">
			<form:form method="post" action="submitAdd" modelAttribute="cltAdd">

				<div>
					<form:label path="mail">Mail: </form:label>
					<div>
						<form:input type="text" placeholder="mail" path="mail"
							required="true" />
					</div>
				</div>

				<div>
					<form:label path="mdp">Password:</form:label>
					<div>
						<form:input type="text" placeholder="Password" path="mdp"
							required="true" />
					</div>
				</div>



				<div>
					<form:label path="nom">Nom: </form:label>
					<div>
						<form:input type="text" placeholder="Nom" path="nom"
							required="true" />
					</div>
				</div>

				<div>
					<form:label path="prenom">Prenom:</form:label>
					<div>
						<form:input type="text" placeholder="Prenom" path="prenom"
							required="true" />
					</div>
				</div>

				<div>
					<form:label path="adresse">Adresse: </form:label>
					<div>
						<form:input type="text" placeholder="Adresse" path="adresse"
							required="true" />
					</div>
				</div>

				<div>
					<form:label path="tel">Tel:</form:label>
					<div class="col-sm-10">
						<form:input type="text" placeholder="Telephone" path="tel"
							required="true" />
					</div>
				</div>

				<div class="form-group" style="visibility: hidden">
					<form:label class="col-sm-2 control-label" path="active"
						style="visibility: hidden">Active: </form:label>
					<div class="col-sm-10">
						<form:input type="boolean" class="form-control" value="true"
							path="active" />
					</div>
				</div>

				<input style="display: none;" value="${CommandeNvxClient}"
					name="pCommande" />


				<button type="submit" id="inscription">S'inscrire</button>

			</form:form>
		</div>

		<div class="form-container sign-in-container">
			<form:form action="submitLoginClt" method="post"
				modelAttribute="cltLogin">
				<form:label path="mail">Mail: </form:label>
				<form:input type="email" path="mail" required="true" />
				<br />
				<form:label path="mdp">mot de passe: </form:label>
				<form:input type="text" path="mdp" required="true" />
				<br />
				<button id="seConnecter" type="submit">Se connecter</button>
				<input type="text" value="${CommandeNvxClient}"
					style="display: none;" name="pCommande" />
			</form:form>



			<form:form action="createClt" method="get" modelAttribute="cltLogin">
				<input type="text" value="${CommandeNvxClient}"
					style="display: none;" name="pCommande">
				<input type="submit" value="creer un compte">
			</form:form>
		</div>

		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Bienvenue!</h1>
					<p>Veuillez vous inscrire</p>
					<div class="nomLogo">

						<p>
							<a id="titreHeader" href="<c:url value='/intHome'/>">Java<span
								id="esp">&</span>Rien
							</a>
						</p>
					<button class="ghost" id="signIn">Se connecter</button>
				</div>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Cher(e) Javiator</h1>
					<p>Entrez votre adresse mail et votre mot de passe pour vous
						connecter</p>
					<div class="nomLogo">

						<p>
							<a id="titreHeader" href="<c:url value='/intHome'/>">Java<span
								id="esp2">&</span>Rien
							</a>
						</p>
					<button class="ghost" id="signUp">S'inscrire</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>