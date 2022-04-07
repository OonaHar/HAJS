package try2;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 12. Pohjana on annettu ohjelma joka luo k kappaletta (esim. 6) sÃ¤iettÃ¤ (pÃ¤Ã¤sÃ¤ikeen lisÃ¤ksi). Kulle- kin sÃ¤ikeelle
 * annetaan parametrina viesti msg. LisÃ¤Ã¤ kuhunkin sÃ¤ikeeseen arvottu viive (0..9 s). Viiveen jÃ¤lkeen kukin sÃ¤ikeistÃ¤
 * tulostaa viestinsÃ¤ msg ja jÃ¤rjestysnumeron monentenako se tulostuksen pÃ¤Ã¤si tekemÃ¤Ã¤n (sai â€luvanâ€ tulostaa). Vihje:
 * AtomicInteger.
 *
 * 13. Muokkaa edellistÃ¤ ohjelmaa 12 kahdella tavalla: (a) varmista, ettÃ¤ sÃ¤ikeiden tekemÃ¤t tulos- tukset tulevat samaan
 * jÃ¤rjestykseen kuin mitÃ¤ niiden saamat jÃ¤rjestysnumerot osoittavat, (b) muuta ohjelmaa siten, ettÃ¤ vain k/2
 * ensimmÃ¤isenÃ¤ tulostamaan ryhtyvÃ¤Ã¤ sÃ¤iettÃ¤ saa tu- lostaa, loput sÃ¤ikeet jÃ¤ttÃ¤vÃ¤t tulostamisen tekemÃ¤ttÃ¤. Vihje:
 * tarvitset jonkinlaisen lukituksen niputtamaan numeron saamista ja tulostusta.
 *
 * 14. Muokkaa edellistÃ¤ ohjelmaa 13 siten, ettÃ¤ heti kun k/2 sÃ¤iettÃ¤ on tulostanut tekstinsÃ¤, loput sÃ¤ikeet sÃ¤ikeet
 * herÃ¤tetÃ¤Ã¤n vÃ¤littÃ¶mÃ¤sti. HerÃ¤tetyt sÃ¤ikeet jÃ¤ttÃ¤vÃ¤t tulostamisen tekemÃ¤ttÃ¤ ja lopettavat toimintansa. Ã„lÃ¤ kÃ¤ytÃ¤
 * System.exit() -kutsua vaan lÃ¤hetÃ¤ nukkuville sÃ¤ikeille keskeytys.
 */

public class hajs22_t12 {



    public static void main(String[] args) {
        int k = 8;


        if (args.length > 0)
            k = Integer.valueOf(args[0]);

        System.out.println("Ajetaan pohja:");
        ajaPohja(k);


    } // main()


    /**
     * Pohjan suoritus.
     * Eri metodina siksi, ettÃ¤ eri tehtÃ¤vÃ¤t voi sitten laittaa samanlaisiin
     * erillisiin metodeihin.
     *
     * @param k montako sÃ¤iettÃ¤ perustetaan
     */
    public static void ajaPohja(int k) {

        // perustetaan ja kÃ¤ynnistetÃ¤Ã¤n k sÃ¤iettÃ¤
        for (int i = 1; i <= k; i++) {
        	
        	int v = 0;
			SaiePohja s = new SaiePohja("saie_pohja_" + i, v, k);
            s.start();


        }

    }


} // class hajs22_de2_t12_14


/**
 * PohjasÃ¤ie joka arpoo ajan, nukkuu ja tulostaa.
 */
class SaiePohja1 extends Thread {
	
    String msg = null;
	int viive =0;
	int k = 0;
	static final AtomicInteger n = new AtomicInteger(0);
	static LinkedList<Thread> kaikkiSaikeet = new LinkedList<Thread>();
    static AtomicBoolean lopeta = new AtomicBoolean(false);



    public SaiePohja1(String msg, int viive, int k) {
        this.msg = msg;
        this.viive = viive;
        this.k = k;
        kaikkiSaikeet.add(this);

    }
       
        private static void lopetaKaikki() {
            lopeta.set(true);
            for (Thread t : kaikkiSaikeet)
                t.interrupt();
        }


    @Override
    public void run() {


        // kullekin sÃ¤ikeelle erilainen satunnaislukugeneraattori
        Random rnd = new Random(System.currentTimeMillis() + Thread.currentThread().getId());
        int viive = rnd.nextInt(5) + 1;
        try {
            Thread.sleep(viive*1000);
        } catch (InterruptedException ignored) {
        }
        
        synchronized (n) {
            if (lopeta.get())
                return;
        int järjestysnumero = n.incrementAndGet();

        System.out.println("SÃ¤ie " + msg + ", numerolla " + järjestysnumero + " tulostaa ja lopettaa");
       
        if (järjestysnumero >= k / 2)
            lopetaKaikki();
        

    }
        
}
}