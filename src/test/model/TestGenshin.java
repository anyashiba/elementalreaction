package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestGenshin {
    private Enemy testEnemy;
    private TeamComp testTeam;
    private ElementalReaction testER;

    //characters
    private Character kinich;
    private Element kinichSkill;
    private Element kinichUlt;
    private Character neuvilette;
    private Element neuvSkill;
    private Element neuvUlt;
    private Character zhongli;
    private Element zhongliSkill;
    private Element zhongliUlt;
    private Character wrio;
    private Element wrioSkill;
    private Element wrioUlt;
    private Character gaming;
    private Element gamingSkill;
    private Element gamingUlt;
    private Character cyno;
    private Element cynoSkill;
    private Element cynoUlt;
    private Character xiao;
    private Element xiaoSkill;
    private Element xiaoUlt;
    
    //elements
    private Element dendro;
    private Element hydro;
    private Element pyro;
    private Element anemo;
    private Element electro;
    private Element geo;
    private Element cryo;
    private ArrayList<String> assertTrue;
    
    @SuppressWarnings("methodlength") 
    @BeforeEach
    void runBefore() {
        // test characters
        kinichSkill = new Element("Dendro", "Canopy Hunter: Riding High");
        kinichUlt = new Element("Dendro", "Hail to the Almighty Dragonlord");
        kinich = new Character("Kinich", "Dendro", kinichSkill, kinichUlt);
        neuvSkill = new Element("Hydro", "O Tears, I Shall Repay");
        neuvUlt = new Element("Hydro", "O Tides, I Have Returned");
        neuvilette = new Character("Neuvillette", "Hydro", neuvSkill, neuvUlt);
        zhongliSkill = new Element("Geo", "Dominus Lapidis");
        zhongliUlt = new Element("Geo", "Planet Befall");
        zhongli = new Character("Zhongli", "Geo", zhongliSkill, zhongliUlt);
        wrioSkill = new Element("Cryo", "Icefang Rush");
        wrioUlt = new Element("Cryo", "Darkgold Wolfbite");
        wrio = new Character("Wriothesley", "Cryo", wrioSkill, wrioUlt);
        gamingSkill = new Element("Pyro", "Bestial Ascent");
        gamingUlt = new Element("Pyro", "Suanni's Gilded Dance");
        gaming = new Character("Gaming", "Pyro", gamingSkill, gamingUlt);
        cynoSkill = new Element("Electro", "Secret Rite: Chasmic Soulfarer");
        cynoUlt = new Element("Electro", "Sacred Rite: Wolf's Swiftness");
        cyno = new Character("Cyno", "Electro", cynoSkill, cynoUlt);
        xiaoSkill = new Element("Anemo", "Lemniscatic Wind Cycling");
        xiaoUlt = new Element("Anemo", "Bane of All Evil");
        xiao = new Character("Xiao", "Anemo", xiaoSkill, xiaoUlt);

        assertTrue = new ArrayList<String>();

        testER = new ElementalReaction();
        testEnemy = new Enemy();
        testTeam = new TeamComp();

        //elements
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
        assertEquals("Dendro", kinich.getElement());
        assertEquals("Kinich", kinich.getName());
        assertEquals(kinichSkill, kinich.getESkill());
        assertEquals(kinichUlt, kinich.getUlt());  
    }

    @Test
    void testCharacterAttributes() {
        ArrayList<String> testAttribute = new ArrayList<String>();
        testAttribute.add("Name: Kinich");
        testAttribute.add("Element: Dendro");
        testAttribute.add("1: ESkill: Canopy Hunter: Riding High");
        testAttribute.add("2: Ult: Hail to the Almighty Dragonlord");
        assertEquals(testAttribute, kinich.attributes());
        assertEquals(4, kinich.attributes().size());
    }

    @Test
    void testElementConstructor() {
        assertEquals("Canopy Hunter: Riding High", kinichSkill.getName());
        assertEquals("Dendro", kinichSkill.getElement());
        assertEquals("Hail to the Almighty Dragonlord", kinichUlt.getName());
        assertEquals("Dendro", kinichUlt.getElement());
    }

    @Test
    void testERconstructor() {
        assertTrue(testER.getLog().isEmpty());
        assertEquals(0, testER.getLog().size());
    }

    @Test 
    void testERreact() {
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals("ability1 and ability2 did 15 damage!", testER.getLog().get(0));
        assertEquals(1, testER.getLog().size());
    }

    @Test 
    void testERreactMultiple() {
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals(5, testER.react(dendro, pyro));
        assertEquals(15, testER.react(dendro, hydro));
        assertEquals(5, testER.react(dendro, pyro));
        assertEquals("ability1 and ability2 did 15 damage!", testER.getLog().get(0));
        assertEquals("ability1 and ability3 did 5 damage!", testER.getLog().get(1));
        assertEquals(2, testER.getLog().size());
    }

    @Test
    void testERreactAllDendro() {
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
        testTeam.addCharacter(kinich);
        assertEquals(1, testTeam.getTeam().size());
        assertEquals(kinich, testTeam.getTeam().get(0));
    }

    @Test
    void testAddMultipleCharacter() {
        testTeam.addCharacter(kinich);
        testTeam.addCharacter(neuvilette);
        assertEquals(2, testTeam.getTeam().size());
        assertEquals(kinich, testTeam.getTeam().get(0));
        assertEquals(neuvilette, testTeam.getTeam().get(1));
    }

    @Test
    void testTeamReactionsBloom() {
        assertTrue.add("Bloom");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsBloomTwice() {
        assertTrue.add("Bloom");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(kinich);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsAllDendro() {
        assertTrue.add("Bloom");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
        testTeam.addCharacter(wrio);
        assertTrue.add("Frozen");
        assertEquals(assertTrue, testTeam.viewTeamReactions());
        testTeam.addCharacter(cyno);
        assertTrue.add("Quicken");
        assertTrue.add("Superconduct");
        assertTrue.add("Electrocharged");
        assertTrue(testTeam.viewTeamReactions().contains("Electrocharged"));
        assertTrue(testTeam.viewTeamReactions().contains("Superconduct"));
        assertTrue(testTeam.viewTeamReactions().contains("Quicken"));
        testTeam.addCharacter(xiao);
        assertTrue(testTeam.viewTeamReactions().contains("Swirl"));
        testTeam.addCharacter(gaming);
        assertTrue(testTeam.viewTeamReactions().contains("Melt"));
        assertTrue(testTeam.viewTeamReactions().contains("Overloaded"));
        assertTrue(testTeam.viewTeamReactions().contains("Vaporize"));
        assertTrue(testTeam.viewTeamReactions().contains("Burning"));
    }

    @Test
    void testTeamReactionsBurning() {
        assertTrue.add("Burning");
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsBurningTwice() {
        assertTrue.add("Burning");
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(kinich);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizeHydro() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizeHydroTwice() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizePyro() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(zhongli);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizePyroTwice() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(zhongli);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizeElectro() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsCrystallizeElectroTwice() {
        assertTrue.add("Crystallize");
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsFrozen() {
        assertTrue.add("Frozen");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(wrio);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsFrozenTwice() {
        assertTrue.add("Frozen");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(wrio);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsOverloaded() {
        assertTrue.add("Overloaded");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsOverloadedTwice() {
        assertTrue.add("Overloaded");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSuperconduct() {
        assertTrue.add("Superconduct");
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSuperconductTwice() {
        assertTrue.add("Superconduct");
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsElectrocharged() {
        assertTrue.add("Electrocharged");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsElectrochargedTwice() {
        assertTrue.add("Electrocharged");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsMelt() {
        assertTrue.add("Melt");
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsMeltTwice() {
        assertTrue.add("Melt");
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlElectro() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlElectroTwice() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(cyno);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlPyro() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlPyroTwice() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlHydro() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlHydroTwice() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(neuvilette);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlCryo() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(zhongli);
        assertEquals(1, testTeam.viewTeamReactions().size());
        assertEquals("Swirl", testTeam.viewTeamReactions().get(0));
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlCryoTwice() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(zhongli);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        assertEquals(1, testTeam.viewTeamReactions().size());
        assertEquals("Swirl", testTeam.viewTeamReactions().get(0));
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsMultipleReactions() {
        testTeam.addCharacter(kinich);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        assertTrue(testTeam.viewTeamReactions().contains("Burning"));
        assertTrue(testTeam.viewTeamReactions().contains("Quicken"));
        assertTrue(testTeam.viewTeamReactions().contains("Overloaded"));
        assertTrue(testTeam.viewTeamReactions().contains("Swirl"));
        assertTrue(testTeam.viewTeamReactions().contains("Melt"));
        assertTrue(testTeam.viewTeamReactions().contains("Superconduct"));
    }

    @Test
    void testTeamReactionsOneReaction() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(wrio);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlCryoOnly() {
        assertTrue.add("Swirl");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(wrio);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsSwirlAll() {
        assertTrue.add("Vaporize");
        assertTrue.add("Overloaded");
        assertTrue.add("Swirl");
        assertTrue.add("Frozen");
        assertTrue.add("Electrocharged");
        assertTrue.add("Melt");
        assertTrue.add("Superconduct");
        testTeam.addCharacter(xiao);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(wrio);
        assertTrue(testTeam.viewTeamReactions().contains("Vaporize"));
        assertTrue(testTeam.viewTeamReactions().contains("Frozen"));
        assertTrue(testTeam.viewTeamReactions().contains("Overloaded"));
        assertTrue(testTeam.viewTeamReactions().contains("Swirl"));
        assertTrue(testTeam.viewTeamReactions().contains("Melt"));
        assertTrue(testTeam.viewTeamReactions().contains("Superconduct"));
        assertTrue(testTeam.viewTeamReactions().contains("Electrocharged"));
    }

    @Test
    void testTeamReactionsQuicken() {
        assertTrue.add("Quicken");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsQuickenTwice() {
        assertTrue.add("Quicken");
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(kinich);
        testTeam.addCharacter(cyno);
        testTeam.addCharacter(kinich);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsVaporize() {
        assertTrue.add("Vaporize");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

    @Test
    void testTeamReactionsVaporizeTwice() {
        assertTrue.add("Vaporize");
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(gaming);
        testTeam.addCharacter(neuvilette);
        testTeam.addCharacter(gaming);
        assertEquals(assertTrue, testTeam.viewTeamReactions());
    }

}
