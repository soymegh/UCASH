package entidades;

public class Tbl_user2 {
	// Atributos
	private int idUsuario;
	private String tokens; // key_encriptacion

	// Metodos
	public int getId_user() {
		return idUsuario;
	}

	public void setId_user(int id_user) {
		this.idUsuario = id_user;
	}

	public String getToken() {
		return tokens;
	}

	public void setToken(String token) {
		this.tokens = token;
	}
}
