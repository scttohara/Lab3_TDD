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
    private int totalSinceLastCheck; 
    
    //global variable to hold total amount of each coins
    private int totalNikel; 
    private int totalDime; 
    private int totalQuarter; 
    private Map<Integer, Integer> items = new HashMap<>();
    
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        
        switch (coinValue) {
            
            case 5:
                if (items.containsKey(5) == false)
                    items.put(5, totalNikel);
                   
                else
                    totalNikel++;
                break;
            case 10: 
                if (items.containsKey(10) == true)
                    totalDime++;
                else
                   items.put(10, totalDime);
                break;
            case 25: 
                if (items.containsKey(25) == true)
                    totalQuarter++;
                else
                   items.put(5, totalQuarter); 
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
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        Map<Integer, Integer> coins = new HashMap<>();
        coins.put(5, items.get(5));
        coins.put(10, items.get(10));
        coins.put(25, items.get(25));
        reset(); //reset all coins to zero
        return coins;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        totalNikel= totalDime= totalQuarter=0;
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
