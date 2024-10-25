package model;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.Character;
import model.Element;
import model.TeamComp;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// code referenced from JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TeamComp team = new TeamComp();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
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