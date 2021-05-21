package contexte;

import connection.Authentification;
import documents.Oeuvre;
import filters.OeuvreFilterFilter;
import org.bson.conversions.Bson;
import query.OeuvreQuery;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int RECHERHCER_OUVRE_TITRE = 1;
    private static final int RECHERHCER_OUVRE_THEME = 2;
    private static final int RECHERHCER_OUVR_MOT_CONTENU = 3;

    public static void main(String[] args) {
        ImportDesOeuvres.importOeuvres();
        ConsoleApplicative.demanderAuthentification();

        int choix;
        if (Authentification.exisiteUserConnecte()) {
            do {
                choix = ConsoleApplicative.afficherLeMenu();
                if (choix == RECHERHCER_OUVRE_TITRE) {
                    rechercherOeuvrePartitre();
                } else if (choix == RECHERHCER_OUVRE_THEME) {
                    rechercherOeuvreParTheme();
                } else if (choix == RECHERHCER_OUVR_MOT_CONTENU) {
                    rechercherOeuvreParMotContenu();
                }
            } while (choix != 0);
        }
    }

    private static void rechercherOeuvrePartitre() {
        System.out.print("Titre de l'oeuvre : ");
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        if (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine()).append(' ');
        }
        String nomOeuvre = stringBuilder.toString().trim();
        OeuvreQuery query = new OeuvreQuery();
        Bson filter = OeuvreFilterFilter.oeuvreParTitre(nomOeuvre);
        List<Oeuvre> oeuvre = query.findAllThatMatch(filter);
        if (oeuvre.isEmpty()) {
            System.out.println("L'oeuvre n'est pas trouvé");
        } else {
            System.out.println("Oeuvres trouvées : \n" + oeuvre);
        }
    }

    private static void rechercherOeuvreParTheme() {
        System.out.print("Theme de l'oeuvre : ");
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        if (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine()).append(' ');
        }
        String theme = stringBuilder.toString().trim();
        OeuvreQuery query = new OeuvreQuery();
        Bson filter = OeuvreFilterFilter.oeuvreParTheme(theme);
        List<Oeuvre> oeuvre = query.findAllThatMatch(filter);
        if (oeuvre.isEmpty()) {
            System.out.println("Aucune oeuvre n'est trouvée");
        } else {
            System.out.println("Oeuvres trouvées : \n" + oeuvre);
        }
    }

    private static void rechercherOeuvreParMotContenu() {
        System.out.print("Mot dans le contenu de l'oeuvre : ");
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        if (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine()).append(' ');
        }
        String nomOeuvre = stringBuilder.toString().trim();
        OeuvreQuery query = new OeuvreQuery();
        Bson filter = OeuvreFilterFilter.oeuvreParContenu(nomOeuvre);
        List<Oeuvre> oeuvre = query.findAllThatMatch(filter);
        if (oeuvre.isEmpty()) {
            System.out.println("L'oeuvre n'est pas trouvé");
        } else {
            System.out.println("Oeuvre trouvé : \n" + oeuvre);
        }
    }
}
