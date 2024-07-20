import java.util.*;

public class Booking {

    public static List<Taxi> createTaxis(int n) {
        List<Taxi> taxis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> checkFreeTaxis(List<Taxi> taxis, char start, int pickupTime) {
        List<Taxi> freetaxis = new ArrayList<Taxi>();
        for (Taxi t : taxis) {
            if (t.freeTime <= pickupTime
                    && Math.abs((t.currentSpot - '0') - (start - '0')) <= Math.abs(t.freeTime - pickupTime)) {
                freetaxis.add(t);
            }
        }
        return freetaxis;
    }

    public static void bookTaxi(int id,char start,char destination,int pickupTime,List<Taxi> freetaxis){
        Taxi bookedTaxi = null;
        int min = Integer.MAX_VALUE;
        int earning = 0;
        int nextFreeTime=0;
        char nextSpot = 'Z';
        String tripDetail = null;

        for (Taxi taxi : freetaxis) {
                int distanceBetweenTaxiandPickup = Math.abs((taxi.currentSpot-'0')-(start-'0'));
            if(distanceBetweenTaxiandPickup<min){
                bookedTaxi = taxi;
                int distanceBetweenPickUpandDrop = Math.abs((start-'0')-(destination-'0'))*15;
                earning = (distanceBetweenPickUpandDrop - 5)*10+100;

                int dropTime = pickupTime+(distanceBetweenPickUpandDrop/15);
                nextFreeTime = dropTime;
                nextSpot = destination;

                tripDetail=" "+id+"              "+id+"             "+start+"       "+destination+"     "+pickupTime+"             "+dropTime+"           "+earning;
                min = distanceBetweenTaxiandPickup;

            }
        }
        bookedTaxi.setDetails(true,nextSpot,nextFreeTime,(bookedTaxi.totalEarnings+earning),tripDetail);
        System.out.println("Taxi ID: "+bookedTaxi.id+" is Booked");
    }

    public static void main(String[] args) {
        System.out.println("\n                                                Welcome to the Booking System   ");
        List<Taxi> taxis = createTaxis(4);

        Scanner sc = new Scanner(System.in);
        int id = 1;

        while (true) {
            System.out.println("Choose Your Option :\n");
            System.out.println("0->Book Taxi\n1->Print Taxi Details\nPress any other Keys to Exit!!!");
            int choice = sc.nextInt();
            switch (choice) {
                case 0: {
                    int customerId = id;
                    System.out.println("Enter your Starting Point: ");
                    char start = sc.next().charAt(0);
                    System.out.println("Enter your Destination Point: ");
                    char destination = sc.next().charAt(0);
                    System.out.println("Enter your Pickup Time: ");
                    int pickupTime = sc.nextInt();
                    if (start < 'A' || destination > 'F' || start > 'F' || destination < 'A') {
                        System.out.println("You Entered a Invalid Point,Exitting");
                        return;
                    }

                    List<Taxi> freetaxis = checkFreeTaxis(taxis, start, pickupTime);
                    if (freetaxis.size() == 0) {
                        System.out.println("No Taxi are Free right now!!,Sorry for the inConvenience\nCome Back Again");
                        return;
                    }
                    Collections.sort(freetaxis, (a, b) -> a.totalEarnings - b.totalEarnings);
                    bookTaxi(customerId, start, destination, pickupTime, freetaxis);
                    id++;
                    break;
                }

                case 1: {
                    for (Taxi taxi : taxis) {
                        taxi.printTaxiDetails();
                    }
                    for (Taxi taxi : taxis) {
                        taxi.printDetails();
                    }
                    break;
                }
                default:
                    return;
            }
        }
    }
}
