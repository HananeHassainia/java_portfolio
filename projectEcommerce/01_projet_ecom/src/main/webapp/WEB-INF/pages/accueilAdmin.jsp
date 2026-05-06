<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des admins</title>
<!-- specifier le chemin du fichier css pour le style des forms -->


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/accueilStyle.css">

<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />

</head>

<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">

	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/headerAdminInfos.html"%>

	<c:choose>
		<c:when test="${Admins.size()!=1}">
			<!-- Formulaire pour rechercher un seul admin à partir de son id -->

			<div class="elementsadmin">
				<div class="btnSearch">
					<form:form id="inputSearch" class="form-horizontal" method="post"
						action="submitSearch" modelAttribute="aSearch"
						style="text-align: center; width: 200px; margin: 0 auto;">

						<div class="form-group" style="float: left;">
							<form:input type="number" class="form-control" placeholder="id"
								path="id" required="required" />

							<button id="boutonChercher2" type="submit">
								<img style="width: 25px !important;"
									src="../assets/images/loop.png" />
							</button>
							<h1 style="color: white; font-size:15px; margin-right:2%;">${msg1}</h1>
				<h1 style="color: white; font-size:15px; margin-right:2%;">${msg2}</h1>
				<h1 style="color: white; font-size:15px; margin-right:2%;">${msg3}</h1>
						</div>

					</form:form>
				</div>

		

				<!-- Afficher les donnees de l'admin qu'on recherche -->

				<div class="formaccueiladmin">
					<c:if test="${admin.role.rolename!='ROLE_ADMIN_MASTER'}">
						<form:form class="form-horizontal" method="post"
							action="submitUpdate" modelAttribute="aUpdate"
							enctype="multipart/form-data">


							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="id">ID</form:label>
								<form:input type="number" class="form-control"
									value="${admin.id}" path="id" />
							</div>

							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="nom">Nom </form:label>
								<form:input type="text" class="form-control" path="nom"
									value="${admin.nom}" required="required" />
							</div>

							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="prenom">Prénom</form:label>
								<form:input type="text" class="form-control"
									value="${admin.prenom}" path="prenom" />
							</div>

							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="mail">Adresse-mail</form:label>
								<form:input type="text" class="form-control"
									value="${admin.mail}" path="mail" readonly="true" />
							</div>

							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="mdp">Mot de passe</form:label>
								<form:input type="password" class="form-control"
									value="${admin.mdp}" path="mdp" />
							</div>

							<div class="form-group">
								<label style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label">Active</label>
								<div>
									<c:choose>
										<c:when test="${admin.active == 'true'}">

											<input type="radio" value="true" name="pActive"
												checked="checked" />
						true
						<input type="radio" value="false" name="pActive" />
						false
						</c:when>
										<c:otherwise>
											<input type="radio" value="true" name="pActive" />
						true
						<input type="radio" value="false" name="pActive" checked="checked" />
						false
						</c:otherwise>
									</c:choose>
								</div>
							</div>

							<!--
					<form:select path="active" id="active">
						<form:option value="${admin.active}">${admin.active}</form:option>
						<c:choose>
							<c:when test="${admin.active=='false'}">
								<form:option value="true">True</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="false">False</form:option>
							</c:otherwise>
						</c:choose>
					</form:select>
					-->

							<div class="form-group">
								<form:label
									style="width: 40%; text-align: left; padding-left: 10px;"
									class="col-sm-2 control-label" path="role.id">Type
						d'accès</form:label>
								<form:select path="role.id">
									<form:option value="${admin.role.id}">${admin.role.rolename}</form:option>
									<c:choose>
										<c:when test="${admin.role.rolename=='ROLE_ADMIN_CAT'}">
											<form:option value="1">ROLE_ADMIN_PROD</form:option>
										</c:when>
										<c:otherwise>
											<form:option value="2">ROLE_ADMIN_CAT</form:option>
										</c:otherwise>
									</c:choose>
								</form:select>
							</div>

							<button type="submit" class="btn btn-primary">Modifier</button>
							<button type="submit" class="btn btn-danger"
								onclick="form.action='submitDeleteAdmin';">Supprimer</button>
						</form:form>
					</c:if>
				</div>

				<!-- Affichage du tableau contenant toutes les tous admin -->
				<div class="tableAdmin">
					<table id="idTableAdmin" class="table table-bordered">
						<tr
							style="top: 0; position: sticky; z-index: 11; background-color: white; border-radius: 10px; box-shadow: 0 0 3em #000000;">
							<th>ID</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Mail</th>
							<th>Mot de passe</th>
							<th>Active</th>
							<th>Rôle</th>

						</tr>

						<c:forEach var="a" items="${Admins}">
							<c:if test="${a.role.rolename!='ROLE_ADMIN_MASTER'}">
								<tr>
									<td>${a.id}</td>
									<td>${a.nom}</td>
									<td>${a.prenom}</td>
									<td>${a.mail}</td>
									<td>${a.mdp}</td>
									<td>${a.active}</td>
									<td>${a.role.rolename}</td>

								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<h1 style="text-align: center">Aucun Admin pour le moment</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>