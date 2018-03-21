
package com.mycompany.unicafe; 

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate paate;

    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @Test
    public void aluksiOikein() {
        assertEquals(paate.kassassaRahaa(), 100000); // 1000.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void edullisenVoiOstaaKateisellaJosRahaRiittaa() {
        // edullinen = 2.50!
        assertEquals(paate.syoEdullisesti(300), 50); // 3.00 -> 0.50
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void edullistaEiVoiOstaaKateisellaJosRahaEiRiita() {
        assertEquals(paate.syoEdullisesti(100), 100); // 1.00 -> 1.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void maukkaanVoiOstaaKateisellaJosRahaRiittaa() {
        // maukas = 4.00!
        assertEquals(paate.syoMaukkaasti(600), 200); // 6.00 -> 2.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void maukastaEiVoiOstaaKateisellaJosRahaEiRiita() {
        assertEquals(paate.syoMaukkaasti(300), 300); // 3.00 -> 3.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void edullisenVoiOstaaKortillaJosRahaRiittaa() {
        Maksukortti kortti = new Maksukortti(300);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 50); // 3.00 -> 0.50
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void edullistaEiVoiOstaaKortillaJosRahaEiRiita() {
        Maksukortti kortti = new Maksukortti(100);
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 100); // 1.00 -> 1.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void maukkaanVoiOstaaKortillaJosRahaRiittaa() {
        Maksukortti kortti = new Maksukortti(600);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 200); // 6.00 -> 2.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void maukastaEiVoiOstaaKortillaJosRahaEiRiita() {
        Maksukortti kortti = new Maksukortti(300);
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 300); // 3.00 -> 3.00
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
}
