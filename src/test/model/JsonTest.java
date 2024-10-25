package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class to check json objects
// code referenced from JsonSerializationDemo
public class JsonTest {
    protected void checkCharacter(String name, String element, Element eskill, Element ult, Character chara) {
        assertEquals(name, chara.getName());
        assertEquals(element, chara.getElement());
        assertEquals(eskill, chara.getESkill());
        assertEquals(ult, chara.getUlt());
    }

    protected void checkElement(String name, String element, Element ability) {
        assertEquals(name, ability.getName());
        assertEquals(element, ability.getElement());
    }

}
