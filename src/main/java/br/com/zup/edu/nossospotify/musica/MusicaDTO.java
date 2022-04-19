package br.com.zup.edu.nossospotify.musica;

public class MusicaDTO {
	
	private String nome;
	
	private Artista dono;

	public MusicaDTO(Musica musica) {
		this.nome = musica.getNome();
		this.dono = musica.getDono();
	}
	
	public MusicaDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getDono() {
		return dono.getNome();
	}

}
