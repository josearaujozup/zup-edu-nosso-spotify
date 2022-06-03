package br.com.zup.edu.nossospotify.musica;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MusicaController {
	
	
	private final AlbumRepository repository;
	
	private final ArtistaRepository artistaRepository;
	
	private final MusicaRepository musicaRepository;
	
	
	public MusicaController(AlbumRepository repository, ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
		this.repository = repository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
	}

	@Transactional
	@PostMapping("/albuns/{albumId}/musicas")
	public ResponseEntity<?> adicionaMusica(@PathVariable("albumId") Long albumId, @RequestBody @Valid NovaMusicaDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		Album album = repository.findById(albumId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cadastro de album para o id informado"));
		
		System.out.println("Nome album: "+ album.getNome());
		
		Musica musica = request.toModel(artistaRepository);
		album.adiciona(musica);
		
//		repository.save(album);
//		musicaRepository.save(musica);
//		musicaRepository.flush();
		repository.flush(); //força geração do ID do telefone no banco de dados
		
		URI location = uriComponentsBuilder.path("/albuns/{albumId}/musicas/{telefoneId}").buildAndExpand(album.getId(), musica.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@Transactional
	@DeleteMapping("/musicas/{musicaId}/artistas/{artistaId}")
	public ResponseEntity<?> removeParticipanteMusica(@PathVariable("musicaId") Long musicaId, @PathVariable("artistaId") Long artistaId){
		
		Musica musica = musicaRepository.findById(musicaId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não encontrada"));
		
		Artista artista = artistaRepository.findById(artistaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista com esse id não cadastrado"));
		
		musica.remove(artista);
		
		return ResponseEntity.noContent().build();
	}
	
}
