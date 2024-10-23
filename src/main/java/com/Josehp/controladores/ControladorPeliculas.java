package com.Josehp.controladores;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peliculas")
public class ControladorPeliculas {

    private static HashMap<String, String> listaPeliculas = new HashMap<>();

    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }

    @GetMapping
    public List<String> obtenerTodasLasPeliculas() {
        return listaPeliculas.keySet().stream().collect(Collectors.toList());
    }

    @GetMapping("/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        String director = listaPeliculas.get(nombre);
        if (director != null) {
            return "Pelicula: " + nombre + ", Director: " + director;
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }

    @GetMapping("/director/{nombre}")
    public List<String> obtenerPeliculasPorDirector(@PathVariable String nombre) {
        List<String> peliculas = listaPeliculas.entrySet().stream()
                .filter(entry -> entry.getValue().equals(nombre))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        if (peliculas.isEmpty()) {
            return List.of("No contamos con películas con ese director en nuestra lista.");
        } else {
            return peliculas;
        }
    }
}