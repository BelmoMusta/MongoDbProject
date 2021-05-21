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

    public static int afficherLeMenu() {
        System.out.println("____________________________________________");
        System.out.println("- Pour chercher une oeuvre par titre taper 1");
        System.out.println("- Pour chercher une oeuvre par thématique taper 2");
        System.out.println("- Pour chercher une oeuvre par mot dans le contenu taper 3");
        System.out.println("- Pour sourtir de l'application taper 0");
        System.out.println("____________________________________________");
        System.out.print("Choix : ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
