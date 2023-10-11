package demo;

import fr.erodrigu.demo.Comptes;
import fr.erodrigu.demo.Personne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComptesTest {

    @Test
    void testConstruct(){
        Personne ps = new Personne("Scheller", "Paul");
        Comptes c1 = new Comptes(1,ps);

        Comptes expected = new Comptes(1,ps,0F);
        expected.setDebitMax(expected.getDebitMax());
        expected.setDecouvert(expected.getDecouvert());
        expected.setSolde(expected.getSolde());
        expected.setDecouvertMax(expected.getDecouvertMax());

        assertEquals(c1,expected);
    }


    @Test
    void testCrediter() {
        Personne jm = new Personne("Moulin","Jean");
        Comptes c1 = new Comptes(1,jm,200.0F);
        Comptes expected = new Comptes(1,jm,300.0F);

        c1.crediter(100.0F);

        assertEquals(c1,expected);
    }

    @Test
    void testDebiter() {
        Personne jm = new Personne("Moulin","Jean");
        Comptes c1 = new Comptes(1,jm,200.0F);
        Comptes expected = new Comptes(1,jm,100.0F);

        c1.debiter(100.0F);

        assertEquals(c1,expected);
    }

    @Test
    void virer() {
        Personne p1 = new Personne("Scheller","Paul");
        Personne p2 = new Personne("Levrey","Tanguy");
        Comptes c1 = new Comptes(1,p1,500F);
        Comptes c2 = new Comptes(2,p2,700F);

        Comptes expected1 = new Comptes(1,p1,600F);
        Comptes expected2 = new Comptes(2,p2,600F);

        c2.virer(100F,c1);

        Comptes[] comptes = {c1,c2};
        Comptes[] expected = {expected1,expected2};

        assertArrayEquals(comptes,expected);
    }
}