package br.com.zup.edu.nossospotify.musica;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistaRepository extends JpaRepository<Artista,Long> {
	
	@Query(value = "select a from Artista a join fetch a.albuns join fetch a.musicas where a.id=:id")
	Optional<Artista> consultarArtistaComMusicasEJogos(Long id);

}
