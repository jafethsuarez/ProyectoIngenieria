package Clases;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private  ArrayList<Plato> platos;

	
	public Menu(ArrayList<Plato> platos) {
		this.platos = platos;
	}
	public void agregarPlato(Plato plato) {
        this.platos.add(plato);
    }
	public ArrayList<Plato> getPlatos() {
		return platos;
	}
	public void setPlatos(ArrayList<Plato> platos) {
		this.platos = platos;
	}
	
	
}
