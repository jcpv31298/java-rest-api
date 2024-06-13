package spring.boot.java_rest_api.PokemonTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Bean;
import spring.boot.java_rest_api.service.IPokemonService;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PokemonControllerTest {
    @Autowired
    private IPokemonService pokemonService;

    @Test
    void shouldReturnNullWhenPokemonNotExits() {
        String pokemon = "nopokemon";
        Object response = pokemonService.findByName(pokemon);

        assertThat(response).isNull();
    }

    @Test
    void shouldReturnNullWhenPokemonIsEmpty() {
        String pokemon = "";
        Object response = pokemonService.findByName(pokemon);

        assertThat(response).isNull();
    }

    @Test
    void shouldGetPokemonWhenIfExists() throws IOException {
        String pokemon = "pikachu";
        Object response = pokemonService.findByName(pokemon);

        assertThat(response).isNotNull();
    }
}
