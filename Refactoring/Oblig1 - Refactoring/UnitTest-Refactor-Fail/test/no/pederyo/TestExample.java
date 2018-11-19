package no.pederyo;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestExample {
    private Cat cat;

    @Before
    public void setup() {
        cat = new Cat("Cat", 12,13);
        System.out.println(cat.sayHi());
        System.out.println(cat.child.sayHi());

    }

    @Test
    public void tester(){
        assertTrue(cat.something() != "");
    }
}
