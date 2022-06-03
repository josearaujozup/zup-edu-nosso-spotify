package br.com.zup.edu.nossospotify.musica;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
class DetalharMusicaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @BeforeEach
    void setUp(){
        musicaRepository.deleteAll();
        artistaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve detalhar os dados de uma música")
    void test1() throws Exception {
        //cenarios
        Artista artista = new Artista("Selvagens a procura de lei", "Fortaleza", "Ceará");

        Musica musica = new Musica("juventude solitude", artista);
        artista.adicionar(musica);
        artista.participou(musica);
        musica.adicionar(Set.of(artista));

        artistaRepository.save(artista);
        musicaRepository.save(musica);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/musicas/{id}", musica.getId());

        String payload = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        DetalharMusicaResponse response = mapper.readValue(payload, DetalharMusicaResponse.class);

        assertNotNull(response);
        assertThat(response)
                .extracting("nome","dono")
                .contains(musica.getNome(),musica.getDono().getNome());
    }

    @Test
    @DisplayName("Não deve detalhar uma música não cadastrada")
    void test2() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/musicas/{id}", Integer.MAX_VALUE);

        Exception resolvedException = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                )
                .andReturn()
                .getResolvedException();

        assertNotNull(resolvedException);
        assertEquals(ResponseStatusException.class,resolvedException.getClass());
        assertEquals("Musica nao cadastrada.",((ResponseStatusException) resolvedException).getReason());
    }

}