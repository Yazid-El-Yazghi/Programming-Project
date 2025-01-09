package Project;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Deze klasse beheert een verzameling van films.
 */
public class FilmCollection {
    private List<Film> films;

    /**
     * Constructor voor de FilmCollection klasse.
     *
     * @param films De lijst van films.
     */
    public FilmCollection(List<Film> films) {
        this.films = films;
    }

    /**
     * Zoekt films op titel.
     *
     * @param title De titel van de film.
     * @return Een lijst van films met de opgegeven titel.
     */
    public List<Film> findFilmsByTitle(String title) {
        return films.stream()
                .filter(film -> film.getTitle() != null && film.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Zoekt films op regisseur.
     *
     * @param director De regisseur van de film.
     * @return Een lijst van films met de opgegeven regisseur.
     */
    public List<Film> findFilmsByDirector(String director) {
        return films.stream()
                .filter(film -> film.getDirector() != null &&
                        film.getDirector().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Zoekt films op jaar.
     *
     * @param year Het jaar van de film.
     * @return Een lijst van films met het opgegeven jaar.
     */
    public List<Film> findFilmsByYear(int year) {
        return films.stream()
                .filter(film -> film.getYear() == year)
                .collect(Collectors.toList());
    }
}