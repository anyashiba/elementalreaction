package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestGenshin {
    private Character testCharacter;
    private Element testSkill;
    private Element testUlt;
    private ElementalReaction testER;
    private Enemy testEnemy;
    private TeamComp testTeam;
    
    @BeforeEach
    void runBefore() {
        testSkill = new Element("Dendro");
        testUlt = new Element("Dendro");
        testCharacter = new Character("Kinich", "Dendro", testSkill, testUlt);
        testER = new ElementalReaction();
        testEnemy = new Enemy();
        testTeam = new TeamComp();
    }

    @Test
    void testCharacterConstructor() {
        assertEquals("Dendro", testCharacter.getESkill().getElement());
        assertEquals("Kinich", testCharacter.getName());
        assertEquals(testSkill, testCharacter.getESkill());
        assertEquals(testUlt, testCharacter.getUlt());  
    }

    @Test
    void testCharacterAttributes() {
        ArrayList<String> testAttribute = new ArrayList<String>();
        testAttribute.add("Name: Kinich");
        testAttribute.add("Element: Dendro");
        testAttribute.add("ESkill: testSkill");
        testAttribute.add("Ult: testUlt");
        assertEquals(testAttribute, testCharacter.attributes());
        assertEquals(4, testCharacter.attributes().size());
    }

    @Test
    void testERconstructor() {
        assertTrue(testER.getLog().isEmpty());
        assertEquals(0, testER.getLog().size());
    }

}
