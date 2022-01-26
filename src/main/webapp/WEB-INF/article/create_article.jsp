<%@page import="beans.Utilisateur"%>
<%@page import="beans.Article"%>
<%@page import="beans.Categorie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vos articles</title>
</head>
<body>
	<h1>Ajouter un article</h1>
	<a href="/Exercice3/utilisateurs">Voir les utilisateurs</a>
	<br>
	<a href="/Exercice3/categories">Ajouter une catégorie</a>
	<br>
	<%@ include file="/WEB-INF/generic/message.jsp" %>
	<form action="" method="post">
		<label for="titre">Titre : </label>
		<br>
		<input type="text" id="titre" name="titre">
		<br>
		<br>
		<label for="description">Description : </label>
		<br>
		<input type="text" id="description" name="description">
		<br>
		<br>
		<label for="contenu">Contenu : </label>
		<br>
		<input type="text" id="contenu" name="contenu">
		<br>
		<br>
		
		<label for="categorie">Catégorie : </label>
		<br>
		<select name="categorie">
			<%
				List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
				for (Categorie categorie: categories) {
					out.println("<option value=\"" + categorie.getTitre() + "\">" + categorie.getTitre() + "</option>");
				}
			%>
		</select>
		<br>
		<br>
		<input type="submit" value="Ajouter l'article">
	</form>
	<br>
	<br>
	<h2>Voici la liste de vos articles : </h2>
	<ul>
		<%
			List<Article> articles = (List<Article>) request.getAttribute("articles");
			for (Article article : articles) {
				out.println("<li>");
				out.println("<p> Titre : "+ article.getTitre() +"</p>");
				out.println("<p> Description :"+ article.getDescription() +"</p>");
				out.println("<p> Contenu : "+ article.getContenu() + "</p>");
				out.println("<p> Auteur : "+article.getAuteur().getNom()+" "+article.getAuteur().getPrenom()+" | "+article.getAuteur().getEmail()+"</p>");
				out.println("</li>");
			}
		%>
	</ul>
</body>
</html>