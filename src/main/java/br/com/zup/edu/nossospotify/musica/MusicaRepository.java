package br.com.zup.edu.nossospotify.musica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica,Long> {
//    @Query("select m from Musica m join fetch m.participantes where m.id = ?1")
//    @Query("select m from Musica m join fetch m.participantes where m.id = :id")
    @Query("SELECT m FROM Musica m JOIN FETCH m.participantes WHERE m.id = :id")
    Optional<Musica> findByIdAndParticipantes(Long id);
}
