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
        String path = "C:\\Users\\yazid\\Desktop\\Poject\\Programming Project\\Programming-Project\\src\\Resources\\netflix_titles.csv";
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
            System.out.println("Kies een optie:");
            System.out.println("1. Aanmaken review");
            System.out.println("2. Zoeken film/serie op naam of deel van de naam");
            System.out.println("3. Lijst films/series van een director");
            System.out.println("4. Lijst films/series van een bepaald release year");
            System.out.println("5. Afsluiten");
            System.out.println(films.size());

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
                        System.out.println("Voer de naam van de auteur in:");
                        String author = scanner.nextLine();
                        System.out.println("Voer de beschrijving in:");
                        String description = scanner.nextLine();
                        System.out.println("Voer de score in:");
                        int score = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Review review = new Review(author, description, score);
                        film.addReview(review);
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
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }

        scanner.close();
    }
}