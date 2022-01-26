package services;

import java.util.ArrayList;
import java.util.List;

import beans.Categorie;

public class CategorieService {
	
	private static CategorieService instance = null;
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	
	public static CategorieService getInstance() {
		if (CategorieService.instance == null) {
			CategorieService.instance = new CategorieService();
		}
		return CategorieService.instance;
	}
	
	public List<Categorie> getCategories() {
		return this.categories;
	}
	
	public void setCategorie(Categorie categorie) {
		this.categories.add(categorie);
	}
	
	public Categorie getCategorie(String titre) {
		Categorie response = null;
		for (Categorie categorie: this.categories) {
			if (categorie.getTitre().equals(titre)) {
				response = categorie;
			}
		}
		return response;
	}
	
	public Boolean isEmpty() {
		Boolean response = false;
		if (this.categories.size() < 1) {
			response = true;
		}
		return response;
	}
	
	public Categorie createCategorie(String titre) {
		Categorie categorie = new Categorie();
		categorie.setTitre(titre);
		return categorie;
	}

}
