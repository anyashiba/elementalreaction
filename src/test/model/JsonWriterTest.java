package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for JsonWriter
// code referenced from JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            new TeamComp();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
            
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEnemyHP() {
        try {
            int hp = 100;
            JsonWriter writer = new JsonWriter("./data/testWriterEnemyHP.json");
            writer.open();
            writer.writeEnemyHP(100);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEnemyHP.json");
            hp = reader.readEnemyHP();
            assertEquals(100, hp);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            TeamComp team = new TeamComp();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeamComp.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeamComp.json");
            team = reader.read();
            assertEquals(0, team.getTeam().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyBattleLog() {
        try {
            List<String> battleLog = new ArrayList<String>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBattleLog.json");
            writer.open();
            writer.writeBattleLog(battleLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBattleLog.json");
            battleLog = reader.readBattleLog();
            assertEquals(0, battleLog.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBattleLog() {
        try {
            List<String> battleLog = new ArrayList<String>();
            battleLog.add("Canopy Hunter: Riding High and Secret Rite: Chasmic Soulfarer did 15 damage!");
            battleLog.add("Hail to the Almighty Dragonlord and Sacred Rite: Wolf's Swiftness did 15 damage!");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBattleLog.json");
            writer.open();
            writer.writeBattleLog(battleLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBattleLog.json");
            battleLog = reader.readBattleLog();
            assertEquals("Canopy Hunter: Riding High and Secret Rite: Chasmic Soulfarer did 15 damage!", 
                    battleLog.get(0));
            assertEquals("Hail to the Almighty Dragonlord and Sacred Rite: Wolf's Swiftness did 15 damage!", 
                    battleLog.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralTeamComp() {
        try {
            TeamComp team = new TeamComp();

            Element kinichSkill = new Element("Dendro", "Canopy Hunter: Riding High");
            Element kinichUlt = new Element("Dendro", "Hail to the Almighty Dragonlord");
            Character kinich = new Character("Kinich", "Dendro", kinichSkill, kinichUlt);

            Element cynoSkill = new Element("Electro", "Secret Rite: Chasmic Soulfarer");
            Element cynoUlt = new Element("Electro", "Sacred Rite: Wolf's Swiftness");
            Character cyno = new Character("Cyno", "Electro", cynoSkill, cynoUlt);

            team.addCharacter(kinich);
            team.addCharacter(cyno);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeamComp.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeamComp.json");
            team = reader.read();
            List<Character> characters = team.getTeam();
            kinich = characters.get(0);
            cyno = characters.get(1);
            assertEquals(2, characters.size());
            checkElement("Canopy Hunter: Riding High", "Dendro", kinich.getESkill());
            checkElement("Hail to the Almighty Dragonlord","Dendro", kinich.getUlt());
            checkCharacter("Kinich", "Dendro", kinich.getESkill(), kinich.getUlt(), kinich);

            checkElement("Secret Rite: Chasmic Soulfarer","Electro", cyno.getESkill());
            checkElement("Sacred Rite: Wolf's Swiftness","Electro", cyno.getUlt());
            checkCharacter("Cyno", "Electro", cyno.getESkill(), cyno.getUlt(), cyno);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}