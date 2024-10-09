package model;

import static org.junit.jupiter.api.Assertions.*;

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
    private Element dendro;
    private Element hydro;
    private Element pyro;
    private Element anemo;
    private Element electro;
    private Element geo;
    private Element cryo;
    
    @BeforeEach
    void runBefore() {
        testSkill = new Element("Dendro", "Canopy Hunter: Riding High");
        testUlt = new Element("Dendro", "Hail to the Almighty Dragonlord");
        testCharacter = new Character("Kinich", "Dendro", testSkill, testUlt);
        testER = new ElementalReaction();
        testEnemy = new Enemy();
        testTeam = new TeamComp();
        dendro = new Element("Dendro", "ability1");
        hydro = new Element("Hydro", "ability2");
        pyro = new Element("Pyro", "ability3");
        anemo = new Element("Anemo", "ability4");
        electro = new Element("Electro", "ability5");
        geo = new Element("Geo", "ability6");
        cryo = new Element("Cryo", "ability7");
    }

    @Test
    void testCharacterConstructor() {
        assertEquals("Dendro", testCharacter.getElement());
        assertEquals("Kinich", testCharacter.getName());
        assertEquals(testSkill, testCharacter.getESkill());
        assertEquals(testUlt, testCharacter.getUlt());  
    }

    @Test
    void testCharacterAttributes() {
        ArrayList<String> testAttribute = new ArrayList<String>();
        testAttribute.add("Name: Kinich");
        testAttribute.add("Element: Dendro");
        testAttribute.add("1: ESkill: Canopy Hunter: Riding High");
        testAttribute.add("2: Ult: Hail to the Almighty Dragonlord");
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
    void testERreact() {
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals("ability1 and ability2 did 15 damage!", testER.getLog().get(0));
        assertEquals(1, testER.getLog().size());
    }

    @Test 
    void testERreactMultiple() {
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals(5, testER.react(dendro, pyro));
        assertEquals("ability1 and ability2 did 15 damage!", testER.getLog().get(0));
        assertEquals("ability1 and ability3 did 5 damage!", testER.getLog().get(1));
        assertEquals(2, testER.getLog().size());
    }

    @Test
    void testERreactAllDendro() {
        //Dendro
        assertEquals(2, testER.react(dendro, dendro));
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals(5, testER.react(dendro, pyro));
        assertEquals(2, testER.react(dendro, anemo));
        assertEquals(15, testER.react(dendro, electro));
        assertEquals(2, testER.react(dendro, geo));
        assertEquals(2, testER.react(dendro, cryo));
    }

    @Test
    void testERreactAllHydro() {
        //Hydro
        assertEquals(15, testER.react(hydro, dendro));
        assertEquals(2, testER.react(hydro, hydro));
        assertEquals(18, testER.react(hydro, pyro));
        assertEquals(10, testER.react(hydro, anemo));
        assertEquals(8, testER.react(hydro, electro));
        assertEquals(7, testER.react(hydro, geo));
        assertEquals(12, testER.react(hydro, cryo));
    }

    @Test
    void testERreactAllPyro() {
        //Pyro
        assertEquals(5, testER.react(pyro, dendro));
        assertEquals(18, testER.react(pyro, hydro));
        assertEquals(2, testER.react(pyro, pyro));
        assertEquals(10, testER.react(pyro, anemo));
        assertEquals(8, testER.react(pyro, electro));
        assertEquals(7, testER.react(pyro, geo));
        assertEquals(20, testER.react(pyro, cryo));
    }

    @Test
    void testERreactAllAnemo() {
        //Anemo
        assertEquals(2, testER.react(anemo, dendro));
        assertEquals(10, testER.react(anemo, hydro));
        assertEquals(10, testER.react(anemo, pyro));
        assertEquals(2, testER.react(anemo, anemo));
        assertEquals(10, testER.react(anemo, electro));
        assertEquals(2, testER.react(anemo, geo));
        assertEquals(10, testER.react(anemo, cryo));
    }
    
    @Test
    void testERreactAllElectro() {
        //Electro
        assertEquals(15, testER.react(electro, dendro));
        assertEquals(8, testER.react(electro, hydro));
        assertEquals(8, testER.react(electro, pyro));
        assertEquals(10, testER.react(electro, anemo));
        assertEquals(2, testER.react(electro, electro));
        assertEquals(7, testER.react(electro, geo));
        assertEquals(9, testER.react(electro, cryo));
    }

    @Test
    void testERreactAllGeo() {
        //Geo
        assertEquals(2, testER.react(geo, dendro));
        assertEquals(7, testER.react(geo, hydro));
        assertEquals(7, testER.react(geo, pyro));
        assertEquals(2, testER.react(geo, anemo));
        assertEquals(7, testER.react(geo, electro));
        assertEquals(2, testER.react(geo, geo));
        assertEquals(2, testER.react(geo, cryo));
    }

    @Test
    void testERreactAllCryo() {
        //Cryo
        assertEquals(2, testER.react(cryo, dendro));
        assertEquals(12, testER.react(cryo, hydro));
        assertEquals(20, testER.react(cryo, pyro));
        assertEquals(10, testER.react(cryo, anemo));
        assertEquals(9, testER.react(cryo, electro));
        assertEquals(2, testER.react(cryo, geo));
        assertEquals(2, testER.react(cryo, cryo));
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
        assertEquals(0, testEnemy.getHP());
    }

    @Test
    void testEnemyResetHP() {
        testEnemy.damage(50);
        testEnemy.resetHP();
        assertEquals(100, testEnemy.getHP());
    }

    @Test
    void testTeamCompConstructor() {
        assertTrue(testTeam.getTeam().isEmpty());
    }

    @Test
    void testAddCharacter() {
        testTeam.addCharacter(testCharacter);
        assertEquals(1, testTeam.getTeam().size());
        assertEquals(testCharacter, testTeam.getTeam().get(0));
    }

    @Test
    void testAddMultipleCharacter() {
        Element e1 = new Element("Hydro", "O Tears, I Shall Repay");
        Element e2 = new Element("Hydro", "O Tides, I Have Returned");
        Character testCharacter2 = new Character("Neuvillette", "Hydro", e1, e2);
        testTeam.addCharacter(testCharacter);
        testTeam.addCharacter(testCharacter2);
        assertEquals(2, testTeam.getTeam().size());
        assertEquals(testCharacter, testTeam.getTeam().get(0));
        assertEquals(testCharacter2, testTeam.getTeam().get(1));
    }
}
