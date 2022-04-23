package br.com.zup.edu.nossospotify.musica;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NovaMusicaDTO {
	
	@NotBlank
    private String nome;
	
	@NotNull
    @Positive
    private Long donoId;

	public NovaMusicaDTO(@NotBlank String nome, @NotNull @Positive Long donoId) {
		this.nome = nome;
		this.donoId = donoId;
	}
	
	public NovaMusicaDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public Long getDonoId() {
		return donoId;
	}

	public Musica toModel(ArtistaRepository artistaRepository) {
		Artista artista = artistaRepository.findById(donoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe um artista com o id informado."));
		
		System.out.println("Nome artista: "+ artista.getNome());
		
		Musica musica = new Musica(nome);
		
		artista.adicionar(musica);
		
		return musica;
	}
	
}
