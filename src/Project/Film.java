package Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse vertegenwoordigt een film.
 */
public class Film {
    private String title;
    private String director;
    private int year;
    private List<Review> reviews;

    /**
     * Constructor voor de Film klasse.
     *
     * @param title De titel van de film.
     * @param director De regisseur van de film.
     * @param year Het jaar waarin de film is uitgebracht.
     */
    public Film(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.reviews = new ArrayList<>();
    }

    /**
     * Voegt een recensie toe aan de lijst van recensies.
     *
     * @param review De recensie die moet worden toegevoegd.
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    // Getters en setters

    /**
     * Haalt de titel van de film op.
     *
     * @return De titel van de film.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Stelt de titel van de film in.
     *
     * @param title De titel van de film.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Haalt de regisseur van de film op.
     *
     * @return De regisseur van de film.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Stelt de regisseur van de film in.
     *
     * @param director De regisseur van de film.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Haalt het jaar van de film op.
     *
     * @return Het jaar van de film.
     */
    public int getYear() {
        return year;
    }

    /**
     * Stelt het jaar van de film in.
     *
     * @param year Het jaar van de film.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Haalt de lijst van recensies op.
     *
     * @return De lijst van recensies.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Stelt de lijst van recensies in.
     *
     * @param reviews De lijst van recensies.
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Haalt een recensie op basis van de auteur op.
     *
     * @param author De naam van de auteur.
     * @return De recensie van de auteur, of null als deze niet bestaat.
     */
    public Review getReviewByAuthor(String author) {
        for (Review review : reviews) {
            if (review.getAuthor().equalsIgnoreCase(author)) {
                return review;
            }
        }
        return null;
    }

    /**
     * Wijzigt een bestaande recensie.
     *
     * @param author De naam van de auteur van de recensie.
     * @param newDescription De nieuwe beschrijving van de recensie.
     * @param newScore De nieuwe score van de recensie.
     * @return True als de recensie is bijgewerkt, anders false.
     */
    public boolean updateReview(String author, String newDescription, int newScore) {
        Review review = getReviewByAuthor(author);
        if (review != null) {
            review.setDescription(newDescription);
            review.setScore(newScore);
            return true;
        }
        return false;
    }

}