package Project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * De hoofdklasse van het programma.
 * Deze klasse leest een CSV-bestand met Netflix-titels in,
 * maakt een verzameling van films aan en biedt een menu
 * voor interactie met de gebruiker.
 */
public class Main {
    public static void main(String[] args) {
        List<Film> films = new ArrayList<>();
        String path = ".\\resources\\netflix_titles.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {
                boolean inQuotes = false;
                boolean inComas = false;
                String[] data = new String[24];
                char[] character = new char[1000];
                int added = 0;
                int count = 0;

                for (int i = 1; i < line.length(); i++) {
                    if (line.charAt(i) == '\"' && i + 1 < line.length() && line.charAt(i + 1) == '\"') {
                        inQuotes = !inQuotes;
                    }
                    if ((!inQuotes && line.charAt(i) == ',') || (line.charAt(i) == ';')) {
                        inComas = !inComas;
                        if (character != null) {
                            int k = 0;
                            while (character[k] != '\u0000') {
                                k++;
                            }
                            char[] temp = new char[k];
                            for (int t = 0; t < k; t++) {
                                temp[t] = character[t];
                            }
                            data[added] = new String(temp);
                            character = new char[1000];
                            count = 0;
                            added++;
                        } else {
                            added++;
                        }
                    }

                    if (line.charAt(i) != ',' && line.charAt(i) != '\"' && line.charAt(i) != '\u0000') {
                        character[count] = line.charAt(i);
                        count++;
                    } else if (line.charAt(i) == ',' && inQuotes && line.charAt(i) != '\u0000') {
                        character[count] = line.charAt(i);
                        count++;
                    }
                }

                String title = data[2];
                String director = data[3];
                String yearStr = data[7];
                int year = 0;
                if (yearStr != null && !yearStr.isEmpty()) {
                    year = Integer.parseInt(yearStr);
                }
                Film film = new Film(title, director, year);
                films.add(film);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FilmCollection filmCollection = new FilmCollection(films);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("");
            System.out.println("Welkom bij Rotten Potatoes!");
            System.out.println("Kies een optie:");
            System.out.println("1. Aanmaken review");
            System.out.println("2. Zoeken film/serie op naam of deel van de naam");
            System.out.println("3. Lijst films/series van een director");
            System.out.println("4. Lijst films/series van een bepaald release year");
            System.out.println("5. Bekijk een recensie");
            System.out.println("6. Wijzig een recensie");
            System.out.println("7. Afsluiten");
            System.out.println("Totaal Netfix database: "+films.size());
            System.out.println("");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Voer de titel van de film/serie in:");
                    String filmTitle = scanner.nextLine();
                    List<Film> foundFilms = filmCollection.findFilmsByTitle(filmTitle);
                    if (foundFilms.isEmpty()) {
                        System.out.println("Film/serie niet gevonden.");
                    } else {
                        Film film = foundFilms.get(0);
                        System.out.println("Voer de titel van de recensie in:");
                        String reviewTitle = scanner.nextLine();
                        System.out.println("Voer de beschrijving in:");
                        String description = scanner.nextLine();
                        int score = -1;
                        while (score < 0 || score > 10) {
                            System.out.println("Voer de score in tussen 0 en 10:");
                            if (scanner.hasNextInt()) {
                                score = scanner.nextInt();
                                if (score < 0 || score > 10) {
                                    System.out.println("Ongeldige score. Probeer opnieuw.");
                                }
                            } else {
                                System.out.println("Ongeldige invoer. Voer een getal in.");
                                scanner.next(); // Consume the invalid input
                            }
                        }
                        scanner.nextLine(); // Consume newline
                        Review review = new Review(description, score, reviewTitle);
                        film.addReview(review);
                        review.printReview();
                        System.out.println("Review toegevoegd.");
                    }
                    break;
                case 2:
                    System.out.println("Voer de (deel van de) naam van de film/serie in:");
                    String name = scanner.nextLine();
                    List<Film> filmsByName = filmCollection.findFilmsByTitle(name);
                    if (filmsByName.isEmpty()) {
                        System.out.println("Geen films/series gevonden.");
                    } else {
                        filmsByName.forEach(f -> System.out.println(f.getTitle()));
                    }
                    break;
                case 3:
                    System.out.println("Voer de naam van de director in:");
                    String director = scanner.nextLine();
                    List<Film> filmsByDirector = filmCollection.findFilmsByDirector(director);
                    if (filmsByDirector.isEmpty()) {
                        System.out.println("Geen films/series gevonden.");
                    } else {
                        filmsByDirector.forEach(f -> System.out.println(f.getTitle()));
                    }
                    break;
                case 4:
                    System.out.println("Voer het release year in:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    List<Film> filmsByYear = filmCollection.findFilmsByYear(year);
                    if (filmsByYear.isEmpty()) {
                        System.out.println("Geen films/series gevonden.");
                    } else {
                        filmsByYear.forEach(f -> System.out.println(f.getTitle()));
                    }
                    break;
                case 5:
                    System.out.println("Enter the title of the film/series:");
                    String filmTitleToView = scanner.nextLine();
                    List<Film> foundFilmsToView = filmCollection.findFilmsByTitle(filmTitleToView);
                    if (foundFilmsToView.isEmpty()) {
                        System.out.println("Film/series not found.");
                    } else {
                        Film filmToView = foundFilmsToView.get(0);
                        List<Review> reviews = filmToView.getReviews();
                        if (reviews.isEmpty()) {
                            System.out.println("No reviews found.");
                        } else {
                            for (Review review : reviews) {
                                review.printReview();
                                System.out.println();
                            }
                        }
                        System.out.println("Average score: " + filmToView.getAverageScore());
                        System.out.println("Number of reviews: " + filmToView.getNumberOfReviews());
                    }
                    break;

                case 6:
                    System.out.println("Enter the title of the film/series:");
                    String filmTitleToModify = scanner.nextLine().trim();
                    List<Film> foundFilmsToModify = filmCollection.findFilmsByTitle(filmTitleToModify);
                    if (foundFilmsToModify.isEmpty()) {
                        System.out.println("Film/series not found.");
                    } else {
                        Film filmToModify = foundFilmsToModify.get(0);
                        System.out.println("Enter the title of the review:");
                        String reviewTitleToModify = scanner.nextLine().trim();
                        Review reviewToModify = filmToModify.getReviews().stream()
                                .filter(review -> review.getTitle().equalsIgnoreCase(reviewTitleToModify))
                                .findFirst()
                                .orElse(null);
                        if (reviewToModify != null) {
                            System.out.println("Enter the new title:");
                            String newTitle = scanner.nextLine().trim();
                            System.out.println("Enter the new description:");
                            String newDescription = scanner.nextLine().trim();
                            System.out.println("Enter the new score:");
                            int newScore = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (newScore >= 0 && newScore <= 10) {
                                reviewToModify.setTitle(newTitle);
                                reviewToModify.setDescription(newDescription);
                                reviewToModify.setScore(newScore);
                                System.out.println("Review updated.");
                            } else {
                                System.out.println("Invalid score. Must be between 0 and 10.");
                            }
                        } else {
                            System.out.println("Review niet gevonden.");
                        }
                    }
                    break;
                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        scanner.close();
    }
}