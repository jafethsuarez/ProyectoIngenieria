package Clases;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<Plato> platos;

	
	public Menu(List<Plato> platos) {
		this.platos = platos;
	}
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	public void agregarPlato(Plato plato) {
        this.platos.add(plato);
    }
	public List<Plato> getPlatos() {
		return platos;
	}
	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}
	
}
