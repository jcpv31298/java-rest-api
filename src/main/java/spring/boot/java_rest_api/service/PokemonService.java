package spring.boot.java_rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService implements IPokemonService {
    private final String pokemonApi;

    public PokemonService(@Value("${pokemon.api.url}") String pokemonApi) {
        this.pokemonApi = pokemonApi;
    }
    @Override
    public Object findByName(String name) {
        try{
            if(name.isEmpty()) return null;

            RestTemplate restTemplate = new RestTemplate();
            String url = pokemonApi + name.toLowerCase();
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response.getBody();
        }
        catch (Exception e) {
            return null;
        }
    }
}
