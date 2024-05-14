package org.georges.services;

import org.georges.dto.Coordonnes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AventurierTest {

    private AventurierService service;

    @BeforeEach
    public void setUp() {
        service = new AventurierService();
    }


    @Test
    public void testDeplacement() {
        List<String> carte = List.of("   ", "###", "   ");
        Coordonnes initial = new Coordonnes(1, 1);
        Coordonnes finale = service.deplacement(initial, 'E', carte);
        assertEquals(1, finale.getX());
        assertEquals(1, finale.getY());
    }

    @Test
    public void testReadFile() throws IOException {
        AventurierService mockService = mock(AventurierService.class);
        when(mockService.readFile("carte.txt")).thenReturn(List.of("   ", "###", "   "));
        List<String> maps = mockService.readFile("carte.txt");
        assertNotNull(maps);
        assertEquals(3, maps.size());
    }

    @Test
    public void testGetCoordonnees() {
        Coordonnes initial = new Coordonnes(1, 1);
        Coordonnes finale = service.getCoordonnees(initial, 'N');
        assertEquals(1, finale.getX());
        assertEquals(0, finale.getY());
    }

    @Test
    public void testAventurier() throws IOException {
        Coordonnes initial = new Coordonnes(3, 0);
        String result = service.init("SSSSEEEEEENN", initial);
        assertNotNull(result);
        assertEquals("Le personnage doit se trouver en (9,2)", result);
    }

    @Test
    public void testAventurierSecondCas() throws IOException {
        Coordonnes initial = new Coordonnes(6, 9);
        String result = service.init("OONOOOSSO", initial);
        assertNotNull(result);
        assertEquals("Le personnage doit se trouver en (7,5)", result);
    }
}
