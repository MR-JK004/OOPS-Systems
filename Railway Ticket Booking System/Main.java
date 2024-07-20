import java.util.*;

public class Main {

    static void bookTicket(Passenger p){
        Booking booking = new Booking();

        if(Booking.availableWaitListBirths == 0){
            System.out.println("Sorry, no more seats available");
            return;
        }

        if(p.birthPreference.equals("L") && Booking.availableLowerBirths > 0 ||
           p.birthPreference.equals("M") && Booking.availableMiddleBirths > 0 ||
           p.birthPreference.equals("U") && Booking.availableUpperBirths > 0 )
           {
                System.out.println("Preferred Birth Available");
                if(p.birthPreference.equals("L")){
                    System.out.println("Lower Birth Given");
                    booking.bookTicket(p,(Booking.lowerBirthPositions.get(0)),"L");
                    Booking.lowerBirthPositions.remove(0);
                    Booking.availableLowerBirths--;
                }

                else if(p.birthPreference.equals("M")){
                    System.out.println("Middle Birth Given");
                    booking.bookTicket(p,Booking.middleBirthPositions.get(0),"M");
                    Booking.middleBirthPositions.remove(0);
                    Booking.availableMiddleBirths--;
                }

                else{
                    System.out.println("Upper Birth Given");
                    booking.bookTicket(p, Booking.upperBirthPositions.get(0), "U");
                    Booking.upperBirthPositions.remove(0);
                    Booking.availableUpperBirths--;
                }
           }

           else if(Booking.availableLowerBirths > 0){
                System.out.println("Sorry Prefered Birth Unavailable");
                System.out.println("Lower Birth Given");
                booking.bookTicket(p,(Booking.lowerBirthPositions.get(0)),"L");
                Booking.lowerBirthPositions.remove(0);
                Booking.availableLowerBirths--;
           }

           else if(Booking.availableMiddleBirths > 0){
                System.out.println("Sorry Prefered Birth Unavailable");
                System.out.println("Middle Birth Given");
                booking.bookTicket(p,(Booking.middleBirthPositions.get(0)),"L");
                Booking.middleBirthPositions.remove(0);
                Booking.availableMiddleBirths--;
           }

           else if(Booking.availableUpperBirths > 0){
                System.out.println("Sorry Prefered Birth Unavailable");
                System.out.println("Upper Birth Given");
                booking.bookTicket(p, Booking.upperBirthPositions.get(0), "U");
                Booking.upperBirthPositions.remove(0);
                Booking.availableUpperBirths--;
           }

           else if(Booking.availableRacBirths > 0){
                System.out.println("Sorry Confirmed Birth Unavailable");
                System.out.println("RAC Alloted");
                booking.addToRac(p, Booking.racBirthPositions.get(0),"RAC");
           }

           else if(Booking.availableRacBirths > 0){
                System.out.println("Sorry Confirmed Birth Unavailable");
                System.out.println("Waiting List Available");
                booking.addToWaitList(p, Booking.waitListBirthPositions.get(0),"WL");
           }

    }

    static void cancelTicket(int id){
        Booking booking = new Booking();
        if(!booking.passengers.containsKey(id)){
            System.out.println("Invalid Passenger ID");
            return;
        }
        else{
            booking.cancelTicket(id);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("--------------------");
            System.out.println("Choose Your Option\n");
            System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.Available Tickets\n4.Booked Tickets\n5.Exit");
            int choice = sc.nextInt();

            switch(choice){
                case 1:{
                    System.out.println("Enter Your Name: ");
                    String name = sc.next();
                    System.out.println("Enter Your Age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter Preferred Birth(L,M,U): ");
                    String birthPreference = sc.next();

                    Passenger p = new Passenger(name,age,birthPreference);
                    bookTicket(p);
                    break;
                }
                case 2:{
                    System.out.println("\nEnter Passenger ID to Cancel the Ticket: ");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                }
                case 3:{
                    System.out.println("\nAvailable Tickets");
                    Booking booking = new Booking();
                    booking.printAvailableTickets();
                    break;
                }
                case 4:{
                    Booking booking = new Booking();
                    booking.printBookedPassengers();
                    break;
                }
                case 5:{
                    System.out.println("\nThank You for Using Our Service");
                    return;
                }
            }
        }
    }
}
