package contexte;

import documents.Oeuvre;
import filters.OeuvreFilterFilter;
import query.OeuvreQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ImportDesOeuvres {

    public static final String DOSSIER_IMPORT = "import";

    public static void importOeuvres() {
        final List<Oeuvre> listeOeuvres;
        try {
            listeOeuvres = Files.walk(Paths.get(DOSSIER_IMPORT))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(ImportDesOeuvres::readFile)
                    .map(ImportDesOeuvres::construireOeuvre)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final OeuvreQuery oeuvreQuery = new OeuvreQuery();
        for (Oeuvre listeOeuvre : listeOeuvres) {
            if (!oeuvreExisteDeja(listeOeuvre)) {
                oeuvreQuery.insert(listeOeuvre);
            } else {
                System.out.println("L'oeuvre '" + listeOeuvre.getTitre() + "' existe déjà");
            }
        }
    }

    private static boolean oeuvreExisteDeja(Oeuvre listeOeuvre) {
        // une oeuvre existe deja si son titre et sa date de publication sont sur la base mongodb
        OeuvreQuery oeuvreQuery = new OeuvreQuery();
        Oeuvre oeuvre = oeuvreQuery.find(OeuvreFilterFilter.oeuvreParTitreEtDatePublication(
                listeOeuvre.getTitre(), listeOeuvre.getPublication()));
        return oeuvre != null;
    }

    public static void main(String[] args) throws Exception {
        importOeuvres();

    }

    public static Oeuvre construireOeuvre(String contenuFichier) {

        Map<String, String> map = creerMapOeuvre(contenuFichier);
        Oeuvre oeuvre = new Oeuvre();
        oeuvre.setTitre(map.getOrDefault("TITRE", ""));
        oeuvre.setTheme(map.getOrDefault("THEME", ""));
        oeuvre.setContenu(map.getOrDefault("CONTENU", ""));
        oeuvre.setAuteurs(stringToArrayByComma(map.getOrDefault("AUTEURS", "")));
        oeuvre.setPages(Integer.valueOf(map.getOrDefault("PAGES", "0")));
        oeuvre.setUniversites(stringToArrayByComma(map.getOrDefault("UNIVERSITES", "")));
        oeuvre.setFormations(stringToArrayByComma(map.getOrDefault("FORMATIONS", "")));
        oeuvre.setRoles(stringToArrayByComma(map.getOrDefault("ROLES", "")));
        Date publication = convertStringToDate(map.getOrDefault("PUBLICATION", ""));
        oeuvre.setPublication(publication);
        return oeuvre;
    }

    public static Date convertStringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    private static List<String> stringToArrayByComma(String str) {
        return new ArrayList<>(Arrays.asList(str.split(",\\s+")));
    }

    private static Map<String, String> creerMapOeuvre(String contenuFichier) {
        Scanner scanner = new Scanner(contenuFichier);
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNextLine() && scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineSplitted = line.split(":\\s*");
            if (line.trim().length() == 0) {
                continue;
            }

            String clef = lineSplitted[0].trim().toUpperCase();
            StringBuilder valeur = new StringBuilder();
            if (!clef.equals("CONTENU")) {
                valeur = new StringBuilder(lineSplitted[1].trim());
            } else {
                while (scanner.hasNextLine()) {
                    valeur.append(scanner.nextLine()).append('\n');
                }
            }
            map.put(clef, valeur.toString());
        }
        return map;
    }

    private static String readFile(File file) {
        try {
            final FileInputStream fis = new FileInputStream(file);
            final InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }

                return stringBuilder.toString();
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
