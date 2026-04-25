<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion Conseiller</title>
<link rel="stylesheet" href="./assets/css/bootstrap.css">
<link rel="stylesheet" href="./assets/css/headerAccueil.css">
<link rel="stylesheet" href="./assets/css/accueil.css">

</head>
<body>
	<%@ include file="./template/headerAccueil.html"%>

	<section class="sectionCons">
		<div id="loginCons">
		
		<article>
			<p>Connectez vous</p>
		</article>
		
		<article>
		<form  method="post" action="loginC">
			<h1 style="color: red; text-align: center">${msg}</h1>
		
			<div class="form-group">
				<label >E-mail</label>
					<input class="form-control" name='pMail' , type='text'
						placeholder="email">
			</div>


			<div class="form-group">
				<label >Mot de passe : </label>
					<input class="form-control" name='pMdp' , type='password'
						placeholder="Password">
			</div>

			<div class="form-group" >
					<input class="btn btn-default" id="btnLoginCons"type="submit" , value='se connecter' />
			</div>
		</form>
		
		
		</article>
		
		</div>
	
		<%-- <h1 style="color:red; text-align: center"><%= request.getAttribute("msg") %></h1>--%>

	</section>
	
		<%@ include file="./template/footer.html"%>
	
</body>
</html>