package contexte;

import connection.Authentification;

public class Main {
    public static void main(String[] args) {

        ConsoleApplicative.demanderAuthentification();
        if(Authentification.exisiteUserConnecte()){
            ConsoleApplicative.afficherLeMenu();
        }
    }
}
