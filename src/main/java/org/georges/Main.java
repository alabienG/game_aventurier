package org.georges;

import org.georges.dto.Coordonnes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static int maxX=0;
    public static int maxY=0;

    private static final String CARTE_PATH = "carte.txt";

    public static void main(String[] args) throws IOException {
        List<String> maps = readFile(CARTE_PATH);
        Coordonnes initial = new Coordonnes(3,0);
        String DEPLACEMETNS ="SSSSEEEEEENN";


        if(maps != null && !maps.isEmpty()){
            maxY = maps.size();
            // on récupère le nombre max de ligne
            for(String ligne : maps) {
                if(ligne.length() > maxX){
                    maxX = ligne.length();
                }
            }

            Coordonnes finale = new Coordonnes(initial.getX(), initial.getY());
            for(int i=0; i<DEPLACEMETNS.length(); i++){
                finale = deplacement(finale, DEPLACEMETNS.charAt(i), maps);
            }
            System.out.println(finale);

        }
    }

    public static Coordonnes deplacement(Coordonnes coordonnes, char direction, List<String> carte){
        Coordonnes cordI = getCoordonnees(coordonnes, direction);
        char espace = carte.get(cordI.getY()).charAt(cordI.getX());
        if(espace == ' '){
            coordonnes = cordI;
        }
        return coordonnes;
    }
    public static List<String> readFile(String path) throws IOException {
        File file  = new File(Main.class.getClassLoader().getResource(path).getPath());
        if(file.exists()){
            return  Files.readAllLines(file.toPath());
        }
        return null;
    }
    public static Coordonnes getCoordonnees(Coordonnes coordonne, char deplacement){
        Coordonnes finale = new Coordonnes(coordonne.getX(), coordonne.getY());
        switch (deplacement){
            case 'O':
                finale.setX(finale.getX() > 0 ? finale.getX()-1 : finale.getX()) ;
                break;
            case 'E':
                finale.setX(finale.getX() < maxX ? finale.getX()+1 : finale.getX());
                break;
            case 'N':
                finale.setY(finale.getY() >0 ? finale.getY()-1 : finale.getY());
                break;
            case 'S':
                finale.setY( finale.getY() < maxY ? finale.getY()+1 : finale.getY());
                break;
            default:
                break;
        }
        return finale;
    }


}