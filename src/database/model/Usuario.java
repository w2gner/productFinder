package database.model;

public class Usuario {
	private String senha;
	private String nome;
	private Boolean is_Admin;
	private int id;
	private String criadoEm;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIs_Admin() {
		return is_Admin;
	}

	public void setIs_Admin(Boolean is_Admin) {
		this.is_Admin = is_Admin;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}
}
