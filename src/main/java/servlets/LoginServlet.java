package servlets;

import java.io.IOException;

import beans.Utilisateur;
import services.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UtilisateurService service = UtilisateurService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		Utilisateur utilisateur = this.service.getUtilisateur(email);
		if (utilisateur != null) {
			if (utilisateur.getPassword().equals(password)) {
				System.out.println("ok");
				session.setAttribute("utilisateur", utilisateur);
				resp.sendRedirect("/Exercice3/utilisateurs");
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
		}
	}
}
