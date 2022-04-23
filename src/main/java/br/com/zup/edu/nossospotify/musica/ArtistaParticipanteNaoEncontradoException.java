package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistaParticipanteNaoEncontradoException extends RuntimeException {
	
	public ArtistaParticipanteNaoEncontradoException(String message) {
		super(message);
	}
	
}
