package br.com.zup.edu.nossospotify.musica;

import java.util.Set;
import java.util.stream.Collectors;

public class ArtistaRespostaDTO {
	private String nome;
	
	private Set<AlbumResponse> albuns;
	private Set<MusicaResponse> musicas;
	
	public ArtistaRespostaDTO(Artista artista) {
		this.nome = artista.getNome();
		this.albuns = artista.getAlbuns().stream().map(AlbumResponse::new).collect(Collectors.toSet());
		this.musicas = artista.getMusicas().stream().map(MusicaResponse::new).collect(Collectors.toSet());
	}
	
	public ArtistaRespostaDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public Set<AlbumResponse> getAlbuns() {
		return albuns;
	}

	public Set<MusicaResponse> getMusicas() {
		return musicas;
	}
	
}
