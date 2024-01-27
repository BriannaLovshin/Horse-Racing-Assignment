package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private int winHorseNumber;
    private int placeHorseNumber;
    private int showHorseNumber; 
    private int winBetAmount;
    private int showBetAmount;
    private int placeBetAmount;
    private double walletAmount;

    private List<Horse> results;



    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public int numHorses(){
        return horses.size();
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public String getRaceSurface() {
        return raceSurface;
    }

    public void Betting(){
        Scanner scanner = new Scanner(System.in);

        boolean betSuccessful = false;

        while (!betSuccessful) {
            
     // find out how much is in the user's wallet
     System.out.print("Enter the amount in your wallet: $");
     double walletAmount = scanner.nextDouble();

     
     // finds out how much they want to be on win
     System.out.print("Enter the amount you want to bet on Win: $");
     int winBetAmount = scanner.nextInt(); 
         if(winBetAmount>0){
             System.out.print("which horse do you want to bet on for Win:(number) ");
             winHorseNumber = scanner.nextInt();
         }

     // finds out the amount they want to be on Place
     System.out.print("Enter the amount to bet on Place: $");
     int placeBetAmount = scanner.nextInt();
     if(placeBetAmount>0){
         System.out.print("which horse do you want to bet on for Place:(number) ");
        placeHorseNumber = scanner.nextInt();
     }   

     // finds out the amount they want to bet on Show
     System.out.print("Enter the amount to bet on Show: $");
     int showBetAmount = scanner.nextInt();
     if(showBetAmount>0){
         System.out.print("which horse do you want to bet on for Show:(number) ");
        showHorseNumber = scanner.nextInt();
     }

            double  totalBetAmount = winBetAmount + placeBetAmount + showBetAmount;
    
            // check if the total betting amount is lower or equal to the wallet's amount
            if (totalBetAmount > walletAmount) {
                System.out.println("There are Insufficient amount of funds. We cannot place those bets. Please try again.");
            } else {
                System.out.println("Bets placed successfully!");
                System.out.println("Total amount bet: $" + totalBetAmount);
                betSuccessful = true;
                }
        }
        

        scanner.close();
}
    //looks to see what the surface is and gets the rating for the odds with that surface, then calculates the prefered length of the horse to the race length
    // then sets it to getOddsS for the chart

    public String getOddsS(Horse horse){
        double lengthDifference = horse.getPreferredLength() - raceLength;
        double percent = 0;
        if(raceSurface.equalsIgnoreCase("mud")){
           percent = horse.getMudRating();
        }
        if(raceSurface.equalsIgnoreCase("dirt")){
            percent = horse.getDirtRating();
        }
        if(raceSurface.equalsIgnoreCase("grass")){
            percent = horse.getGrassRating();
        }

        return HorseRacingHelper.calculateRatioShow(percent, lengthDifference);
    }

    //looks to see what the surface is and gets the rating for the odds with that surface, then calculates the prefered length of the horse to the race length
    // then sets it to getOddsW for the chart

    public String getOddsW(Horse horse){
        double lengthDifference = horse.getPreferredLength() - raceLength;
        double percent = 0;
        if(raceSurface.equalsIgnoreCase("mud")){
           percent = horse.getMudRating();
        }
        if(raceSurface.equalsIgnoreCase("dirt")){
            percent = horse.getDirtRating();
        }
        if(raceSurface.equalsIgnoreCase("grass")){
            percent = horse.getGrassRating();
        }

        return HorseRacingHelper.calculateRatioWin(percent, lengthDifference);
    }

    //looks to see what the surface is and gets the rating for the odds with that surface, then calculates the prefered length of the horse to the race length
    // then sets it to getOddsP for the chart
    public String getOddsP(Horse horse){
        double lengthDifference = horse.getPreferredLength() - raceLength;
        double percent = 0;
        if(raceSurface.equalsIgnoreCase("mud")){
           percent = horse.getMudRating();
        }
        if(raceSurface.equalsIgnoreCase("dirt")){
            percent = horse.getDirtRating();
        }
        if(raceSurface.equalsIgnoreCase("grass")){
            percent = horse.getGrassRating();
        }

        return HorseRacingHelper.calculateRatioPlace(percent, lengthDifference);
    }

    public void displayRaceInfo(){
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("+-----------------------+----------+----------+----------+------------------+-----------+------------+----------+");
        System.out.println("|List of Horse:         |Dirt:     |Grass:    |Mud:      |Preferred Length: |Show odds: |Place odds: |Win odds: |");
        for (int i = 0; i < horses.size(); i++) {   // iterates through the horses list
            Horse horse = horses.get(i);
            String s1 = i+1 + "." + " " + horse.getName();
            String s2 = "" + horse.getDirtRating();
            String s3 = "" + horse.getGrassRating();
            String s4 = "" + horse.getMudRating();
            String s5 = "" + horse.getPreferredLength();

            String oddsShow = getOddsS(horse); //calculating the odds for show for that horse and puttin it into a string
            String oddsPlace = getOddsW(horse); //calculating the odds for Win for that horse and puttin it into a string
            String oddsWin = getOddsP(horse);//calculating the odds for Place for that horse and puttin it into a string

            System.out.println("+-----------------------+----------+----------+----------+------------------+-----------+------------+----------+");
            System.out.printf("|%-23s|%10s|%10s|%10s|%18s|%11s|%11s|%11s|\n", s1, s2, s3, s4, s5, oddsWin, oddsPlace, oddsShow);   
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------+");
    }

    double amountwon = 0;


    // displays results and checks if the horse they bet on matches the results and if so updates the amountwon
    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            Horse horse = results.get(i);
            System.out.println((i+1) + ": " + horse.getName() + "("+horse.getNumber()+")");

            // checks if the horse they bet on for win wins and if so updated the amountwon variable
            if (i == 0 && winHorseNumber == horse.getNumber()) {
                amountwon += winBetAmount * justnumOdds(getOddsW(horse));
            }
    
            // checks if they horse they bet on for place comes 1st or 2nd and if so updates the amountwon variable
            if ((i == 0 || i == 1) && placeHorseNumber == horse.getNumber()) {
                amountwon += placeBetAmount * justnumOdds(getOddsP(horse));
            }
    
            // checks if the horse they bet on for show comes 1,st, 2nd, or 3rd and if so updated the variable amountwon
            if ((i == 0 || i == 1 || i == 2) && showHorseNumber == horse.getNumber()) {
                amountwon += showBetAmount * justnumOdds(getOddsS(horse));
            }
        }
       
    }

    // Display the total amount won and how much s left in the user's wallet
    public void displayamountwon(){
        System.out.println("Total amount won: $" + amountwon);
        System.out.println(("Amount left in wallet") + (walletAmount - amountwon)); 
    }

    // since the odds right now are in string (odd - 1) this class converts the numbers up to the hyphen into a double so you can use it in displayresults
    // the Double.valueOf changes the string value to a double
        private double justnumOdds(String oddsString) {
        int hyphen = oddsString.indexOf("-");
        return Double.valueOf(oddsString.substring(0, hyphen));       
    }



    public void startRace(){
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(1000);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        while(!done){
            HorseRacingHelper.pauseForMilliseconds(100);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();
           

            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition(getIncrement(horse)); 
            }


            displayResults();

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private int getIncrement(Horse horse) {
        int d = (int)(7 - Math.abs(horse.getPreferredLength() - this.raceLength));

        if (raceSurface.equals("grass"))
            d += horse.getGrassRating() / 2;
        else if (raceSurface.equals("mud"))
            d += horse.getMudRating() / 2;
        else if (raceSurface.equals("dirt"))
            d += horse.getDirtRating() / 2;
        // this.raceLength
        // this.raceSurface
       return (int)(Math.random() * d);
    }
     

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
