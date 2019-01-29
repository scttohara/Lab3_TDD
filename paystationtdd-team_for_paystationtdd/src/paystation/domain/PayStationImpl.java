package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    
    //global variable to hold total amount of money entered
    private static int totalSinceLastCheck; 
    
    //global variable to hold total amount of each coins
    private int totalNikel; 
    private int totalDime; 
    private int totalQuarter; 
    
    
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        
        switch (coinValue) {
            
            case 5:
                totalNikel++;
                break;
            case 10: 
                totalDime++;
                break;
            case 25: 
                totalQuarter++;
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        
        //adds the amount entered when "buy" was selected to the total 
        //amount of money in the machine
        totalSinceLastCheck += insertedSoFar; 

        Receipt r = new ReceiptImpl(timeBought);
        
        reset();
        coins.clear();
        return r;
    }

    private Map<Integer, Integer> coins = new HashMap<>();
       
       
    @Override
    public Map<Integer, Integer> cancel() {
        
        reset(); //reset all coins to zero
        return coins;
    }
    
    private void reset() {
        
        
       int nickel = totalNikel;
       int dime = totalDime;
       int quarter = totalQuarter;
        if (nickel>0){
            coins.put(5, nickel);
        }
        if (dime>0){
            coins.put(10, dime);
        }
        if (quarter>0){
            coins.put(25, quarter);
        }
        
        timeBought = insertedSoFar = 0;
        totalNikel = totalDime = totalQuarter=0;
        
    }

    @Override
    public int empty() { 
    
        // sets the total amount of money entered since last checked to temp
        int temp = totalSinceLastCheck; 
        
        // resets the total amount since last checked to 0 
        totalSinceLastCheck = 0;
                
        return temp; // returns the total amount 
    
    }
}
