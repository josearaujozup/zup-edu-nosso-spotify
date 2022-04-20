package br.com.zup.edu.nossospotify.musica;

public class MusicaResponse {
	
	private String nome;
	
	public MusicaResponse() {
	}
	
	public MusicaResponse(Musica musica) {
		this.nome = musica.getNome();
	}
	
	public String getNome() {
		return nome;
	}
}
