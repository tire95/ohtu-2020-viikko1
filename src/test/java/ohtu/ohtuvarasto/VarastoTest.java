package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriTilavuusLiianPieni() {
        varasto = new Varasto(0, 10.0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);     
    }
    
    @Test
    public void konstruktoriAlkusaldoLiianPieni() {
        varasto = new Varasto(1.0, -10.0);
        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);    
    }
    
    @Test
    public void konstruktoriAlkusaldoLiianSuuri() {
        varasto = new Varasto(1.0, 10.0);
        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);    
    }
    
    @Test
    public void konstruktoriTilavuusJaSaldoKunnossa() {
        varasto = new Varasto(10.0, 1.0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);    
    }
    
    @Test
    public void alkutilavuusLiianPieni() {
        varasto = new Varasto(-10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNollaEiVaikuta() {
        varasto.lisaaVarastoon(0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNegatiivinenEiVaikuta() {
        varasto.lisaaVarastoon(-10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaLiikaaTayttaaVaraston() {
        varasto.lisaaVarastoon(20);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaAlleNollaPalauttaaNollan() {
        varasto.lisaaVarastoon(5);
        double otettu = varasto.otaVarastosta(-10);
        assertEquals(0, otettu, vertailuTarkkuus);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaOttamineTyhjentaaVaraston() {
        varasto.lisaaVarastoon(5);
        double otettu = varasto.otaVarastosta(10);
        assertEquals(5, otettu, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
    
    @Test
    public void toStringToimii2() {
        varasto.lisaaVarastoon(2.0);
        assertEquals(varasto.toString(), "saldo = 2.0, vielä tilaa 8.0");
    }
}