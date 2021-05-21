package contexte;

import documents.Oeuvre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImportDesOeuvres {

    public static final String DOSSIER_IMPORT = "import";

    public static void importOeuvres() {
        List<Oeuvre> listeOeuvres = null;
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

    }

    public static void main(String[] args) throws Exception {
        importOeuvres();

    }

    public static Oeuvre construireOeuvre(String contenuFichier) {
        return null; // todo
    }

    private static String readFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

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
