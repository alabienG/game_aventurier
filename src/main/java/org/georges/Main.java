package org.georges;

import org.georges.dto.Coordonnes;
import org.georges.services.AventurierService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        Coordonnes initial = new Coordonnes(3, 0);
        String DEPLACEMETNS = "SSSSEEEEEENN";

        AventurierService service = new AventurierService();

        System.out.println(service.init(DEPLACEMETNS, initial));

    }
}