package model;
import persistence.JsonReader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for JsonReader
// code referenced from JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeamComp.json");
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
            checkElement("Canopy Hunter: Riding High", "Dendro", kinich.getESkill());
            checkElement("Hail to the Almighty Dragonlord",  "Dendro", kinich.getUlt());
            checkCharacter("Kinich", "Dendro", kinich.getESkill(), kinich.getUlt(), kinich);
            Character cyno = characters.get(1);
            checkElement("Secret Rite: Chasmic Soulfarer","Electro", cyno.getESkill());
            checkElement("Sacred Rite: Wolf's Swiftness","Electro", cyno.getUlt());
            checkCharacter("Cyno", "Electro", cyno.getESkill(), cyno.getUlt(), cyno);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
