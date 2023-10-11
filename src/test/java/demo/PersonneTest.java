package demo;

import fr.erodrigu.demo.Personne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonneTest {

    @Test
    void constructTest(){
        Personne p1 = new Personne("Michel","Jean");
        Personne p2 = new Personne("Michel","Jean");

        assertEquals(p1,p2);
    }
}
