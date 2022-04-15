package br.com.zup.edu.nossospotify.musica;

import javax.validation.constraints.NotBlank;

public class ArtistaAtualizarDTO {
	
	@NotBlank
    private String nome;

	public ArtistaAtualizarDTO(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public ArtistaAtualizarDTO() {
		
	}

	public String getNome() {
		return nome;
	}

}
