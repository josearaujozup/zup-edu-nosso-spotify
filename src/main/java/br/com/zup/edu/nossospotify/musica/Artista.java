package br.com.zup.edu.nossospotify.musica;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @ManyToMany(mappedBy = "participantes")
    private Set<Musica> participacoes = new HashSet<>();

    @OneToMany(mappedBy ="dono")
    private Set<Album> albuns = new HashSet<>();

    @OneToMany(mappedBy ="dono")
    private Set<Musica> musicas = new HashSet<>();


    public Artista(String nome, String cidade, String estado) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Artista() {
    }

    public Long getId() {
        return this.id;
    }

    public void participou(Musica musica) {
        this.participacoes.add(musica);
    }

    public void adicionar(Musica musica) {
    	musica.setDono(this);
        this.musicas.add(musica);
        System.out.println("Chegou dentro de artista: "+ musicas.size());
    }

    public void adicionar(Album album){
        this.albuns.add(album);
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Set<Album> getAlbuns() {
		return albuns;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public Set<Musica> getParticipacoes() {
		return participacoes;
	}	
	
}
