package try2;
import java.util.Random;

public class hajs22_t6{

    static Random rnd = new Random(System.currentTimeMillis());
    static int num1 = 0;
    static int num2 = 0;

    public static void main(String[] args) {

        System.out.println("Pääohjelma alkaa");
        
        try {
            nuku(0, 0);
        } catch (InterruptedException ie) {
            // herätettiin kesken unien
        }
        
   
        
        saie paasaie = new saie(""); //??


        System.out.println("Pääohjelma lopettaa");
    }

    /**
     * Nukkuu min-max sekuntia.
     * @param min minimiaika sekunteina
     * @param max maksimiaika sekunteina
     * @throws InterruptedException jos nukkuminen keskeytettiin
     */
    static void nuku(int min, int max) throws InterruptedException {
        Thread.sleep(min*1000L + rnd.nextInt(((max-min)*1000+1)));
    }

    /**
     * Säikeen toiminnallisuus tehtävään 5.
     */
     static class saie implements Runnable {
    	 
    	 

        String nimi = "";
        boolean jatkaSukua = false;
        
    	saie(String nimi) { //S�ikeelle nimi   	
    		this.nimi = nimi;
    		
    		Thread saie1 = new Thread(this); //Perustaa kaksi uutta s�iett�
    		Thread saie2 = new Thread(this);
    		
    		saie1.setName("PERTTI");
    		saie2.setName("ANNE");

    		saie1.start();
    		saie2.start();
		
   	}

	
        /**
         * Säikeen elinkaari.
         */

        public void run() {
        	
        	System.out.println("Nimi: " + Thread.currentThread().getName() + " " + (int) Math.floor(Math.random() * 10)); 
 
            
            //System.out.println("Säie " + " alkaa.");          	
            //System.out.println("Säie " + " lopettaa.");
        }

    }
}
