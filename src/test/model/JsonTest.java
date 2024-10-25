package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

// code referenced from JsonSerializationDemo
public class JsonTest {
    protected void checkCharacter(String name, String element, Element eskill, Element ult, Character chara) {
        assertEquals(name, chara.getName());
        assertEquals(element, chara.getElement());
        assertEquals(eskill, chara.getESkill());
        assertEquals(ult, chara.getUlt());
    }

    protected void checkElement(String element, String name, Element ability) {
        assertEquals(name, ability.getName());
        assertEquals(element, ability.getElement());
    }
}
