package model;

import model.Character;
import model.Element;
import model.TeamComp;
import persistence.JsonReader;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TeamComp team = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            TeamComp team = reader.read();
            assertEquals(0, team.getTeam().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTeamComp() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeamComp.json");
        try {
            TeamComp team = reader.read();
            List<Character> characters = team.getTeam();
            assertEquals(2, characters.size());
            Character kinich = characters.get(0);
            checkElement("Dendro", "Canopy Hunter: Riding High", kinich.getESkill());
            checkElement("Dendro", "Hail to the Almighty Dragonlord", kinich.getUlt());
            checkCharacter("Kinich", "Dendro", kinich.getESkill(), kinich.getUlt(), kinich);
            Character cyno = characters.get(1);
            checkElement("Electro", "Secret Rite: Chasmic Soulfarer", cyno.getESkill());
            checkElement("Electro", "Sacred Rite: Wolf's Swiftness", cyno.getUlt());
            checkCharacter("Cyno", "Electro", cyno.getESkill(), cyno.getUlt(), cyno);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
