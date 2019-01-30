# Lab3_TDD
Lab 3 Software Design


Requirements: Explain in your own word what were the requirements for this work.  Did you implement everything that was asked? Did you make additional improvements? 

  1. Development should only be done using Test Driven Development.
  2. Commit once a test is added and when the test passes.
  3. Add a method empty() that returns an int that contains the total amount of money collected since the last time empty() was called and empties the int. Money should only be collected after a call to the buy() function.
  4. Change the requirements of the void cancel() function to Map<Integer, Integer> cancel(). 
  5. The call to the empty() function returns the total amount entered.
  6. A call to the cancel() function does not add to the amount returned by empty.
  7. A call to the empty() function resets the total to zero.
  8. The call to the cancel() function returns a map containing one coin entered. 
  9. The call to the cancel() function returns a map containing a mixture of coins entered. 
  10. A call to cancel() function returns a map that does not contain a key for a coin not entered. 
  11. The call to cancel() function clears the map.
  12. A call to buy() function clears the map.
  13. All team members must take part in coding. 
  
  - We believe we've implemented everything that was asked and we have made no additional changes as of the writing of this README file (1/29/19)


Team: List all team members. Shortly explain the contribution of everybody.  

Scott O'Hara:
  -Created the testEmpty() to test the empty function
  -Created the empty() function 
  -Worked in person on bug fixes with Rattanak on the testCancel() function, the cancel() function, and a bug where totalSinceLastCheck wasn't being saved correctly because it wasnt static
  
Rattanak Um:
  -Created the testCancel() to test the cancel() function
  -Rewrote the cancel() function so it return a map
  -Worked in person on bug fixes with Scott on the testCancel() function, the cancel() function, and a bug where totalSinceLastCheck wasn't being saved correctly because it wasn't static in the payStationImpl.
