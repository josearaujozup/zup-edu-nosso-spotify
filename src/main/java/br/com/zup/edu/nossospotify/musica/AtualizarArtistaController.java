package br.com.zup.edu.nossospotify.musica;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/artistas")
public class AtualizarArtistaController {
	
	public final ArtistaRepository repository;

	public AtualizarArtistaController(ArtistaRepository repository) {
		this.repository = repository;
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long idArtista, @RequestBody @Valid ArtistaAtualizarDTO request){
		
		Artista artista = repository.findById(idArtista).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista com esse id não cadastrado"));
		
		artista.setNome(request.getNome());
		
		repository.save(artista);
		
		return ResponseEntity.noContent().build();
	}
	
}
