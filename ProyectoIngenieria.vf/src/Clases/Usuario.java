package Clases;

import javafx.scene.control.Alert;

public class Usuario {
	
	private String usuario;
	private String contrasena;
	private String ubicacion;
	public Usuario(String usuario, String contrasena,String ubicacion) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.ubicacion= ubicacion;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		 this.contrasena=  contrasena;
		
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}
