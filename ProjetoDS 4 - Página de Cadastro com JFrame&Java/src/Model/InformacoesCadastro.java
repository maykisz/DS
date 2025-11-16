package Model;

import java.sql.Date;

public class InformacoesCadastro {
	// Atributos da classe Produto
	private String idcpf;
	private String nome;
	private String telefone;
	private Date dataNascimento;
	
	public InformacoesCadastro() {}
	
	public String getIdcpf() {
		return idcpf;
	}
	public void setIdcpf(String idcpf) {
		this.idcpf = idcpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
}
