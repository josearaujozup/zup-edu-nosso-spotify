package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/albuns")
public class DeletarAlbumController {
	
	private final AlbumRepository repository;

    public DeletarAlbumController(AlbumRepository repository) {
        this.repository = repository;
    }
	
    
    @DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long idAlbum){
		
		Album album = repository.findById(idAlbum).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cadastro de album para o id informado"));
		
		repository.delete(album);
		
		return ResponseEntity.noContent().build();
	}
    
    
}
