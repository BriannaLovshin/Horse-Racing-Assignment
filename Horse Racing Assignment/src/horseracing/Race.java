package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;

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
     System.out.print("Enter the amount to bet on Win: $");
     double winBetAmount = scanner.nextDouble();
         if(winBetAmount>0){
             System.out.print("which horse do you want to be on for Win:(number) ");
             double winHorseNumber = scanner.nextDouble();
         }

     // finds out the amount they want to be on Place
     System.out.print("Enter the amount to bet on Place: $");
     double placeBetAmount = scanner.nextDouble();
     if(placeBetAmount>0){
         System.out.print("which horse do you want to be on for Place:(number) ");
         double placeHorseNumber = scanner.nextDouble();
     }

     // finds out the amount they want to bet on Show
     System.out.print("Enter the amount to bet on Show: $");
     double showBetAmount = scanner.nextDouble();
     if(showBetAmount>0){
         System.out.print("which horse do you want to be on for Place:(number) ");
         double showHorseNumber = scanner.nextDouble();
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
        System.out.println("+--------------------+----------+----------+----------+------------------+-----------+------------+----------+");
        System.out.println("|List of Horse:      |Dirt:     |Grass:    |Mud:      |Preferred Length: |Show odds: |Place odds: |Win odds: |");
        for (int i = 0; i < horses.size(); i++) {   // iterates through the horses list
            Horse horse = horses.get(i);
            String s1 = i+1 + "." + " " + horse.getName();
            String s2 = "" + horse.getDirtRating();
            String s3 = "" + horse.getGrassRating();
            String s4 = "" + horse.getMudRating();
            String s5 = "" + horse.getPreferredLength();

            String oddsShow = getOddsS(horse);
            String oddsPlace = getOddsW(horse);
            String oddsWin = getOddsP(horse);

            System.out.println("+--------------------+----------+----------+----------+------------------+-----------+------------+----------+");
            System.out.printf("|%-20s|%10s|%10s|%10s|%18s|%11s|%11s|%11s|\n", s1, s2, s3, s4, s5, oddsShow, oddsPlace, oddsWin);
        }
        System.out.println("+------------------------------------------------------------------------------------------------------------+");
    }

    // public void displayRaceInfo() {
        
    //     // for (Horse horse : horses) {
    //     //     System.out.println("- " + horse.getName());
    //     // }
    //     displayHorseTable();
    // }

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
    }


    // public void displayRaceInfo() {
    //     System.out.println("Race Information:");
    //     System.out.println("Race Surface: " + raceSurface);
    //     System.out.println("Race Length: " + raceLength + " furlongs");
    //     // System.out.println("List of Horses:"); (removed by beatrice)

    //     System.out.println("Horse name," + " Horse rating for Mud, Grass, Dirt," + " Preferred racing length: ");

    //     for (Horse horse : horses) {
    //         // code modified by beatrice
    //         System.out.println("- " + horse.getName() + ":  " + horse.getMudRating() + ",  " + horse.getGrassRating() + ",  " + horse.getDirtRating()); 
            
    //     }
    // }




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
        // we have racelength and racesurface(as attributes)
        // horse.getPreferredLength()
        // horse.getDirtRating()
        // horse.getGrassRating()
        // horse.getMudRating() between 2 they like the length, 3-5 okay, and 6 no
        throw new UnsupportedOperationException("Unimplemented method 'getIncrement'");
    }

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
