import java.util.*;

public class Taxi{
    static int taxiNumber = 0;//Taxi Number
    int id;
    boolean booked;
    char currentSpot;
    int freeTime;
    int totalEarnings;
    List<String> trips;

    public Taxi(){
        booked = false;
        currentSpot = 'A';
        freeTime = 6;
        totalEarnings = 0;
        trips = new ArrayList<String>();
        taxiNumber = taxiNumber+1;
        id = taxiNumber;
    }

    public void setDetails(boolean booked,char currentSpot,int freeTime,int totalEarnings,String tripDetail){
        this.booked = booked;
        this.currentSpot = currentSpot;
        this.freeTime = freeTime;
        this.totalEarnings = totalEarnings;
        this.trips.add(tripDetail);
    }

    public void printTaxiDetails(){
        System.out.println("Taxi Number: "+id+" Total Earnings: "+totalEarnings+" Current Spot: "+currentSpot+" Free Time: "+freeTime+"\n");
    }

    public void printDetails(){
        System.out.println("\nTaxi : "+id+" Total Earnings : "+totalEarnings+"\n");
        System.out.println("Taxi ID    Booking ID    Customer ID    From    To    PickupTime    DropTime    Amount");
        for(String trip:trips){
            System.out.println(id+"         "+trip);   
        }
        System.out.println("----------------------------------------------------------------------------------------\n");
    }
}      