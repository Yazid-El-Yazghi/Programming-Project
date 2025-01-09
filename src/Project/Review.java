package Project;

/**
 * Deze klasse vertegenwoordigt een recensie van een film.
 */
public class Review {
    private String author;
    private String description;
    private int score;

    /**
     * Constructor voor de Review klasse.
     *
     * @param author De naam van de auteur van de recensie.
     * @param description De beschrijving van de recensie.
     * @param score De score van de recensie.
     */
    public Review(String author, String description, int score) {
        this.author = author;
        this.description = description;
        this.score = score;
    }

    // Getters en setters

    /**
     * Haalt de naam van de auteur op.
     *
     * @return De naam van de auteur.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Stelt de naam van de auteur in.
     *
     * @param author De naam van de auteur.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Haalt de beschrijving van de recensie op.
     *
     * @return De beschrijving van de recensie.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Stelt de beschrijving van de recensie in.
     *
     * @param description De beschrijving van de recensie.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Haalt de score van de recensie op.
     *
     * @return De score van de recensie.
     */
    public int getScore() {
        return score;
    }

    /**
     * Stelt de score van de recensie in.
     *
     * @param score De score van de recensie.
     */
    public void setScore(int score) {
        if(score < 0 || score > 10) {
            throw new IllegalArgumentException("Score moet tussen 0 en 10 liggen.");
        }
        this.score = score;
    }

    /**
     * Drukt de recensie af.
     */
    public void printReview() {
        System.out.println("Auteur: " + author);
        System.out.println("Beschrijving: " + description);
        System.out.println("Score: " + score);
    }
}