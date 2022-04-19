package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/musicas")
public class DetalharMusicaController {
	
	private final MusicaRepository repository;

	public DetalharMusicaController(MusicaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable("id") Long idMusica){
		
		Musica musica = repository.findById(idMusica).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não encontrada"));
		
		MusicaDTO dto = new MusicaDTO(musica);
		
		return ResponseEntity.ok(dto);
	}
	
}
