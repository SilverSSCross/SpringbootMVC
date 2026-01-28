package org.example.helloworldspringboot2026;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
class WebController {

    GameRepository gameRepository;
    public WebController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("platforms",gameRepository.getPlatforms());
        System.out.println( gameRepository.getPlatforms() );
        return "index2";
    }

    @GetMapping("/{nombre}")
    public String saludar(@PathVariable String nombre, Model model)
    {
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", "anonimo");
        return "saludo";
    }

    @GetMapping("/juego/{id}")
    public String juego(@PathVariable Integer id, Model model)
    {
        if(gameRepository.findById(id).isPresent()) {
            model.addAttribute("game", gameRepository.findById(id).get());
            return "game";
        } else {
            model.addAttribute("error","No existe el juego "+id);
            return "error";
        }
    }

    /**
     * Mio
     * */
    public List<String> mostrarPlataformas(Model model) {
        // Suponiendo que tienes una lista de juegos
        List<Game> games = gameRepository.findAll();

        // Crear un Set para eliminar duplicados automáticamente
        Set<String> plataformasUnicas = games.stream()
                .map(Game::getPlatform)
                .collect(Collectors.toSet());

        // Ordenar las plataformas si es necesario
        List<String> plataformasOrdenadas = new ArrayList<>(plataformasUnicas);
        Collections.sort(plataformasOrdenadas);

        model.addAttribute("plataformas", plataformasOrdenadas);
        return plataformasOrdenadas;
    }

}
