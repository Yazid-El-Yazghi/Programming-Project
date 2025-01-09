package Project;

/**
 * Deze klasse vertegenwoordigt een recensie van een film.
 */
public class Review {
    private String title;
    private String description;
    private int score;

    /**
     * Constructor voor de Review klasse.
     *
     * @param description De beschrijving van de recensie.
     * @param score De score van de recensie.
     */
    public Review(String description, int score, String title) {
        this.description = description;
        this.score = score;
        this.title = title;
    }

    // Getters en setters

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
     * Gets the title of the review.
     *
     * @return The title of the review.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the review.
     *
     * @param title The title of the review.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Stelt de score van de recensie in.
     *
     * @param score De score van de recensie.
     */
    public void setScore(int score) {this.score = score;}

    /**
     * Drukt de recensie af.
     */
    public void printReview() {
        System.out.println("Titel review: " + getTitle());
        System.out.println("Beschrijving: " + description);
        System.out.println("Score: " + score);
    }
}