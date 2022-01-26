package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.Categorie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CategorieService;

@WebServlet(urlPatterns = "/categories")
public class CategorieServlet extends HttpServlet {
	
	private List<Categorie> categories = new ArrayList<Categorie>();
	private CategorieService categorieService = CategorieService.getInstance();
	public String message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (this.categorieService.isEmpty()) {
			message = "Vous devez d'abord créer un catégorie avant d'ajouter un article";
			req.setAttribute("message", message);
		}
		req.setAttribute("categories", this.categories);
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie/create_categorie.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String titre = req.getParameter("titre");
	
			if (titre.trim().isEmpty()) {
				message = "Merci de remplir tout les champs";
				req.setAttribute("message", message);
			} else {
				Categorie categorie = this.categorieService.createCategorie(titre);
				this.categorieService.setCategorie(categorie);
			}
			req.setAttribute("categories", this.categories);
			this.getServletContext().getRequestDispatcher("/WEB-INF/categorie/create_categorie.jsp").forward(req, resp);
	}
}
