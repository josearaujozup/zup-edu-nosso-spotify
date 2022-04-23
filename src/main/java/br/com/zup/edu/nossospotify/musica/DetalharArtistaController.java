package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/artistas")
public class DetalharArtistaController {
	
	private final ArtistaRepository repository;

	public DetalharArtistaController(ArtistaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ArtistaRespostaDTO> detalhar(@PathVariable("id") Long idArtista){
		
		Artista artista = repository.consultarArtistaComMusicasEJogos(idArtista).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrada"));
//		Artista artista = repository.findById(idArtista).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrada"));
		
		return ResponseEntity.ok(new ArtistaRespostaDTO(artista));
	}

}
