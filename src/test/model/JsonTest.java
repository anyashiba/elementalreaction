package model;

import model.Element;
import model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
