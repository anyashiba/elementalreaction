package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestGenshin {
    private Element testElement;
    private ElementalReaction testER;
    private Enemy testEnemy;
    private TeamComp testTeam;
    
    @BeforeEach
    void runBefore() {
        testElement = new Element("Kinich", "Dendro", "Canopy Hunter: Riding High", "Hail to the Almighty Dragonlord");
        testER = new ElementalReaction();
        testEnemy = new Enemy();
        testTeam = new TeamComp();
    }

    @Test
    void testElementConstructor() {
        assertEquals("Dendro", testElement.getElement());
        assertEquals("Kinich", testElement.getName());
        assertEquals("Canopy Hunter: Riding High", testElement.getElementalSkill());
        assertEquals("Hail to the Almighty Dragonlord", testElement.getUlt());  
    }

    @Test
    void testElementAttributes() {
        ArrayList<String> testAttribute = new ArrayList<String>();
        testAttribute.add("Name: Kinich");
        testAttribute.add("Element: Dendro");
        testAttribute.add("ESkill: Canopy Hunter: Riding High");
        testAttribute.add("Ult: Hail to the Almighty Dragonlord");
        assertEquals(testAttribute, testElement.attributes());
        assertEquals(4, testElement.attributes().size());
    }

    @Test
    void testERconstructor() {
        assertTrue(testER.getLog().isEmpty());
        assertEquals(0, testER.getLog().size());
    }

}
