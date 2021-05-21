package contexte;

import connection.Authentification;

import java.util.Scanner;

public class ConsoleApplicative {
    public static void demanderAuthentification() {
        int nombreTentative = 0;
        System.out.println("Veuilez entrer votre nom d'utilisateur est le mot de passe");
        Scanner scanner = new Scanner(System.in);
        while (nombreTentative < 3 && !Authentification.exisiteUserConnecte()) {
            System.out.print("username : ");
            String username = scanner.next();
            System.out.print("password : ");
            String password = scanner.next();
            Authentification.authentifier(username, password);
            nombreTentative++;
        }
        if (nombreTentative == 3 && !Authentification.exisiteUserConnecte()) {
            System.out.println("Vous avez dépassé le nombre de tentives permises");
            System.exit(2);
        }
    }

    public static void afficherLeMenu() {
        System.out.println();
    }
}
