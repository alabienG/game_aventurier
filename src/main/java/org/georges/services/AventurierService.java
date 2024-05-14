package org.georges.services;

import org.georges.Main;
import org.georges.dto.Coordonnes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class AventurierService {

    public int maxX;
    public int maxY;
    private static final String CARTE_PATH = "carte.txt";

    public String init(String deplacements, Coordonnes initial) throws IOException {
        List<String> maps = readFile(CARTE_PATH);

        if (maps != null && !maps.isEmpty()) {
            maxY = maps.size();
            // on récupère le nombre max de ligne
            for (String ligne : maps) {
                if (ligne.length() > maxX) {
                    maxX = ligne.length();
                }
            }

            Coordonnes finale = new Coordonnes(initial.getX(), initial.getY());
            for (int i = 0; i < deplacements.length(); i++) {
                finale = deplacement(finale, deplacements.charAt(i), maps);
            }
            return "Le personnage doit se trouver en (" + finale.toString() + ")";

        }
        return "";
    }


    public Coordonnes deplacement(Coordonnes coordonnes, char direction, List<String> carte) {
        Coordonnes cordI = getCoordonnees(coordonnes, direction);
        char espace = carte.get(cordI.getY()).charAt(cordI.getX());
        if (espace == ' ') {
            coordonnes = cordI;
        } else {
            System.out.println("Deplacement impossible");
        }
        return coordonnes;
    }

    public List<String> readFile(String path) throws IOException {
        File file = new File(Main.class.getClassLoader().getResource(path).getPath());
        if (file.exists()) {
            return Files.readAllLines(file.toPath());
        }
        return null;
    }

    public Coordonnes getCoordonnees(Coordonnes coordonne, char deplacement) {
        Coordonnes finale = new Coordonnes(coordonne.getX(), coordonne.getY());
        switch (deplacement) {
            case 'O':
                finale.setX(finale.getX() > 0 ? finale.getX() - 1 : finale.getX());
                break;
            case 'E':
                finale.setX(finale.getX() < getMaxX() ? finale.getX() + 1 : finale.getX());
                break;
            case 'N':
                finale.setY(finale.getY() > 0 ? finale.getY() - 1 : finale.getY());
                break;
            case 'S':
                finale.setY(finale.getY() < getMaxY() ? finale.getY() + 1 : finale.getY());
                break;
            default:
                break;
        }
        return finale;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}
