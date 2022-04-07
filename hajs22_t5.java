package try2;

import java.util.Random;



public class hajs22_t5{

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
        

        saie saie1 = new saie("HALOO", num1); //Perustaa kaksi uutta s�iett�
        saie saie2 = new saie("HUHUU", num2);
        
        saie1.start();	//startataan
        saie2.start();

     


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
    static class saie extends Thread {

        String nimi = "";
        boolean jatkaSukua = false;
        
        int num1 = (int) Math.floor(Math.random() * 10);
        int num2 = (int) Math.floor(Math.random() * 10);


        /**
         * Säikeen kontruktori.
         * 
         */
    	public saie(String nimi, double num) { //S�ikeelle nimi 
    		this.nimi = nimi;
    		
  
    		
    	}

        /**
         * Säikeen elinkaari.
         */
        @Override
        public void run() {
        	
        	 
        	System.out.println("Nimi: " + nimi + ", " + num1);     
            
            //System.out.println("Säie " + " alkaa.");                     
            //System.out.println("Säie " + " lopettaa.");
        }

    }

}