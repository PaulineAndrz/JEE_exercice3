package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.Article;
import beans.Categorie;
import beans.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.CategorieService;
import services.UtilisateurService;

@WebServlet("/articles")
public class ArticleServlet extends HttpServlet {
	
	private List<Article> articles = new ArrayList<Article>();
	private UtilisateurService service = UtilisateurService.getInstance();
	private CategorieService categorieService = CategorieService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!this.categorieService.isEmpty()) {
			req.setAttribute("categories", this.categorieService.getCategories());
			req.setAttribute("articles", articles);
			this.getServletContext().getRequestDispatcher("/WEB-INF/article/create_article.jsp").forward(req, resp);	
		} else {
			resp.sendRedirect("/Exercice3/categories");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titre = req.getParameter("titre");
		String description = req.getParameter("description");
		String contenu = req.getParameter("contenu");
		String auteur = req.getParameter("auteur");
		String categorie = req.getParameter("categorie");
		String message; 
		
		if (titre.trim().isEmpty() || description.trim().isEmpty() || contenu.trim().isEmpty() || auteur.trim().isEmpty()) {
			message = "Merci de remplir tout les champs !";
			req.setAttribute("message", message);
		} else {
			Article article = new Article(); 
			article.setTitre(titre);
			article.setContenu(contenu);
			article.setDescription(description);
			article.setAuteur(this.service.getUtilisateur(auteur));
			article.setCategorie(this.categorieService.getCategorie(categorie));
			articles.add(article);
		}

		req.setAttribute("auteurs", this.service.getUtilisateurs());
		req.setAttribute("categories", this.categorieService.getCategories());
		req.setAttribute("articles", articles);
		this.getServletContext().getRequestDispatcher("/WEB-INF/article/create_article.jsp").forward(req, resp);
	}

}
