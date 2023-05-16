package Clases;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Administrador extends Usuario {
	
	public Administrador(String usuario, String contrasena,String ubicacion) {
		// TODO Auto-generated constructor stub
		super(usuario, contrasena,ubicacion);
				
	}
	public Administrador()
	{
		
	}
	
	//Leer Json
	public Vector<Administrador> desserealizarJsonArray()
		{ Vector<Administrador> adm = new Vector<Administrador>();
			try (Reader reader = new FileReader("Admin.json")) {
				Gson gson = new Gson();
				Type tipoListaAdmin = new TypeToken<Vector<Administrador>>(){}.getType();
				adm = gson.fromJson(reader, tipoListaAdmin);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return adm;
		}
		


	
}
