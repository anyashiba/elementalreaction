package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.zip.CRC32;

import model.ElementalReaction;

public class TestGenshin {
    private Character testCharacter;
    private Element testSkill;
    private Element testUlt;
    private ElementalReaction testER;
    private Enemy testEnemy;
    private TeamComp testTeam;
    
    @BeforeEach
    void runBefore() {
        testSkill = new Element("Dendro", "Canopy Hunter: Riding High");
        testUlt = new Element("Dendro", "Hail to the Almighty Dragonlord");
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
        testAttribute.add("ESkill: Canopy Hunter: Riding High");
        testAttribute.add("Ult: Hail to the Almighty Dragonlord");
        assertEquals(testAttribute, testCharacter.attributes());
        assertEquals(4, testCharacter.attributes().size());
    }

    @Test
    void testElementConstructor() {
        assertEquals("Canopy Hunter: Riding High", testSkill.getName());
        assertEquals("Dendro", testSkill.getElement());
        assertEquals("Hail to the Almighty Dragonlord", testUlt.getName());
        assertEquals("Dendro", testUlt.getElement());
    }

    @Test
    void testERconstructor() {
        assertTrue(testER.getLog().isEmpty());
        assertEquals(0, testER.getLog().size());
    }

    @Test
    void testERgetLog() {
        ArrayList<String> testLog = new ArrayList<>();
        testLog.add("ability1");
        assertEquals("ability1", testLog.get(0));
        assertEquals(1, testER.getLog().size());
    }

    void testERgetLogMultiple() {
        ArrayList<String> testLog = new ArrayList<>();
        testLog.add("ability1");
        testLog.add("ability2");
        assertEquals("ability1", testLog.get(0));
        assertEquals("ability1", testLog.get(1));
        assertEquals(2, testER.getLog().size());
    }

    @Test 
    void testERreact() {
        Element e1 = new Element("Dendro", "ability1");
        Element e2 = new Element("Hydro", "ability2");
        assertEquals(15, testER.react(e1, e2));
        assertEquals("ability1 and ability 2 did 15 damage!", testER.getLog().get(0));
        assertEquals(1, testER.getLog().size());
    }

    @Test 
    void testERreactMultiple() {
        Element e1 = new Element("Dendro", "ability1");
        Element e2 = new Element("Hydro", "ability2");
        Element e3 = new Element("Pyro", "ability3");
        assertEquals(15, testER.react(e1, e2));
        assertEquals(5, testER.react(e1, e3));
        assertEquals("ability1 and ability 2 did 15 damage!", testER.getLog().get(0));
        assertEquals("ability1 and ability 3 did 5 damage!", testER.getLog().get(1));
        assertEquals(2, testER.getLog().size());
    }

    @Test
    void testERreactAll() {
        Element Dendro = new Element("Dendro", "ability1");
        Element Hydro = new Element("Hydro", "ability2");
        Element Pyro = new Element("Pyro", "ability3");
        Element Anemo = new Element("Anemo", "ability4");
        Element Electro = new Element("Electro", "ability5");
        Element Geo = new Element("Geo", "ability6");
        Element Cryo = new Element("Cryo", "ability7");
        //Dendro
        assertEquals(2, testER.react(Dendro, Dendro));
        assertEquals(15, testER.react(Dendro, Hydro));
        assertEquals(5, testER.react(Dendro, Pyro));
        assertEquals(2, testER.react(Dendro, Anemo));
        assertEquals(15, testER.react(Dendro, Electro));
        assertEquals(2, testER.react(Dendro, Geo));
        assertEquals(2, testER.react(Dendro, Cryo));
        //Hydro
        assertEquals(15, testER.react(Hydro, Dendro));
        assertEquals(2, testER.react(Hydro, Hydro));
        assertEquals(18, testER.react(Hydro, Pyro));
        assertEquals(8, testER.react(Hydro, Anemo));
        assertEquals(8, testER.react(Hydro, Electro));
        assertEquals(7, testER.react(Hydro, Geo));
        assertEquals(12, testER.react(Hydro, Cryo));
        //Pyro
        assertEquals(5, testER.react(Pyro, Dendro));
        assertEquals(18, testER.react(Pyro, Hydro));
        assertEquals(2, testER.react(Pyro, Pyro));
        assertEquals(10, testER.react(Pyro, Anemo));
        assertEquals(8, testER.react(Pyro, Electro));
        assertEquals(7, testER.react(Pyro, Geo));
        assertEquals(20, testER.react(Pyro, Cryo));
        //Anemo
        assertEquals(2, testER.react(Anemo, Dendro));
        assertEquals(10, testER.react(Anemo, Hydro));
        assertEquals(10, testER.react(Anemo, Pyro));
        assertEquals(2, testER.react(Anemo, Anemo));
        assertEquals(10, testER.react(Anemo, Electro));
        assertEquals(2, testER.react(Anemo, Geo));
        assertEquals(10, testER.react(Anemo, Cryo));
        //Electro
        assertEquals(15, testER.react(Electro, Dendro));
        assertEquals(8, testER.react(Electro, Hydro));
        assertEquals(8, testER.react(Electro, Pyro));
        assertEquals(10, testER.react(Electro, Anemo));
        assertEquals(2, testER.react(Electro, Electro));
        assertEquals(7, testER.react(Electro, Geo));
        assertEquals(9, testER.react(Electro, Cryo));
        //Geo
        assertEquals(2, testER.react(Geo, Dendro));
        assertEquals(7, testER.react(Geo, Hydro));
        assertEquals(7, testER.react(Geo, Pyro));
        assertEquals(2, testER.react(Geo, Anemo));
        assertEquals(7, testER.react(Geo, Electro));
        assertEquals(2, testER.react(Geo, Geo));
        assertEquals(7, testER.react(Geo, Cryo));
        //Cryo
        assertEquals(2, testER.react(Cryo, Dendro));
        assertEquals(12, testER.react(Cryo, Hydro));
        assertEquals(20, testER.react(Cryo, Pyro));
        assertEquals(10, testER.react(Cryo, Anemo));
        assertEquals(9, testER.react(Cryo, Electro));
        assertEquals(7, testER.react(Cryo, Geo));
        assertEquals(2, testER.react(Cryo, Cryo));
    }

    @Test
    void testEnemyConstructor() {
        assertEquals(100, testEnemy.getHP());
    }

    @Test
    void testEnemyDamage() {
        testEnemy.damage(50);
        assertEquals(50, testEnemy.getHP());
    }

    @Test
    void testEnemyDamageMultiple() {
        testEnemy.damage(50);
        testEnemy.damage(30);
        assertEquals(20, testEnemy.getHP());
    }

    @Test
    void testEnemyHPzero() {
        testEnemy.damage(100);
        assertEquals(100, testEnemy.getHP());
    }
}
