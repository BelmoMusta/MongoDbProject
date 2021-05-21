package contexte;

import connection.Authentification;
import documents.Oeuvre;
import filters.OeuvreFilterFilter;
import org.bson.types.ObjectId;
import query.OeuvreQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int RECHERHCER_OUVRE = 0;
    private static final int INSERT_OUVRE = 1;

    public static void main(String[] args) {
        ConsoleApplicative.demanderAuthentification();
        boolean estAdmin = Authentification.getUtilisateurConnecte() != null
                && Authentification.getUtilisateurConnecte().estAdmin();
        if (estAdmin) {
            ImportDesOeuvres.importOeuvres();
        } else {
            System.out.println("L'utilisateur connecté ne peut importer les oeuvres !");
        }
        if (Authentification.exisiteUserConnecte()) {
            int choix = ConsoleApplicative.afficherLeMenu();
            if (choix == RECHERHCER_OUVRE) {
                System.out.print("Titre de l'oeuvre : ");
                Scanner sc = new Scanner(System.in);
                StringBuilder stringBuilder = new StringBuilder();
                if (sc.hasNextLine()) {
                    stringBuilder.append(sc.nextLine()).append(' ');
                }
                String nomOeuvre = stringBuilder.toString().trim();
                OeuvreQuery query = new OeuvreQuery();
                Oeuvre oeuvre = query.find(OeuvreFilterFilter.oeuvre(nomOeuvre));
                if (oeuvre == null) {
                    System.out.println("L'oeuvre n'est pas trouvé");
                } else {
                    System.out.println("Oeuvre trouvé : \n" + oeuvre);
                }
            } else if (choix == INSERT_OUVRE) {
                insererUneOeuvre();
            }
        }
    }

    private static void insererUneOeuvre() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------Insertion d'une oeuvre------------");
        Oeuvre oeuvre = new Oeuvre();

        System.out.print("Titre:");
        oeuvre.setTitre(scanner.nextLine());
        System.out.print("Auteurs:");
        oeuvre.setAuteurs(Arrays.asList(scanner.nextLine().split(",\\s*")));
        System.out.print("Pages:");
        oeuvre.setPages(Integer.valueOf(scanner.nextLine().trim()));
        System.out.print("Publication:");
        try {
            Date datePublication = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine().trim());
            oeuvre.setPublication(datePublication);
        } catch (ParseException ignored) {
        }
        System.out.print("Theme:");
        oeuvre.setTheme(scanner.nextLine());
        System.out.print("Formations:");
        oeuvre.setFormations(Arrays.asList(scanner.nextLine().split(",\\s*")));
        System.out.print("Universites:");
        oeuvre.setUniversites(Arrays.asList(scanner.nextLine().split(",\\s*")));
        System.out.print("Roles:");
        oeuvre.setRoles(Arrays.asList(scanner.nextLine().split(",\\s*")));
        System.out.print("Contenu:");
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                break;
            }
            stringBuilder.append(line)
                    .append('\n');
        }
        oeuvre.setContenu(stringBuilder.toString());
        oeuvre.setId(new ObjectId());

        OeuvreQuery oeuvreQuery = new OeuvreQuery();
        oeuvreQuery.insert(oeuvre);
    }
}
