package contexte;

import connection.Authentification;
import documents.Oeuvre;
import filters.OeuvreFilterFilter;
import query.OeuvreQuery;

import java.util.Scanner;

public class Main {
    private static final int RECHERHCER_OUVRE = 0;

    public static void main(String[] args) {
        ImportDesOeuvres.importOeuvres();
        ConsoleApplicative.demanderAuthentification();
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

            }
        }
    }
}
