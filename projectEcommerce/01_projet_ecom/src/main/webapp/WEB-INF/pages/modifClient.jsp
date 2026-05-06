<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--  ajouter la lib core de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--  ajouter la lib form de spring mvc  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ModifClient</title>

<%@ include file="/template/link.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ClientnavBar.css" />

</head>
<body style="background-color: #fafafa">
	<%@ include file="/template/headerAccueilInterface.html"%>


	<div>

		<%@ include file="/template/navBarClient.html"%>

	<%-- 	<h1 style="color: red; text-align: center;">${msg1}</h1>
		<h1 style="color: green; text-align: center;">${msg2}</h1> --%>


		<form:form id="formModifClient" class="form-horizontal" method="post"
			action="submitUpd" modelAttribute="client">
			<h1 style="color: #c4d5ec; text-align: center; margin-left:20px; margin-right:20px; border-bottom:2px solid #c4d5ec">Informations
				personnelles</h1>


			<div class="form-group" style="visibility: hidden">
				
				<div class="col-sm-10">
				<form:label  path="id"
					style="display: none">Id: </form:label>
					<form:input type="text" class="form-control" value="${client.id}"
						path="id" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label  path="mail">Mail: </form:label>
				
					<form:input type="text" class="form-control" value="${client.mail}"
						path="mail" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label  path="mdp">Password: </form:label>
				
					<form:input type="password" class="form-control" value="${client.mdp}"
						path="mdp" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label path="nom">Nom: </form:label>
				
					<form:input type="text" class="form-control" value="${client.nom}"
						path="nom" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label  path="prenom">Prenom:</form:label>
				
					<form:input type="text" class="form-control"
						value="${client.prenom}" path="prenom" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label  path="adresse">Adresse:</form:label>
				
					<form:input type="text" class="form-control"
						value="${client.adresse}" path="adresse" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-10">
								<form:label  path="tel">Tel:</form:label>
				
					<form:input type="text" class="form-control" value="${client.tel}"
						path="tel" />
				</div>
			</div>






			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Modifier</button>
				</div>
			</div>
		</form:form>

		<h1 style="color: red; text-align: center;">${msg}</h1>



	</div>
	<%@ include file="/template/footer.html"%>

</body>
</html>