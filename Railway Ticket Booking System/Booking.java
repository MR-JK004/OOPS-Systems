import java.util.*;

public class Booking {
    static int availableLowerBirths = 1;
    static int availableUpperBirths = 1;
    static int availableMiddleBirths = 1;
    static int availableRacBirths = 1;
    static int availableWaitListBirths = 1;

    static Queue<Integer> racList = new LinkedList<>();
    static Queue<Integer> waitList = new LinkedList<>();
    static List<Integer> bookedList = new ArrayList<>();

    static List<Integer> lowerBirthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBirthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBirthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racBirthPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitListBirthPositions = new ArrayList<>(Arrays.asList(1));

    static Map<Integer, Passenger> passengers = new HashMap<>();

    public void bookTicket(Passenger p, int seatNumber, String birth) {
        p.number = seatNumber;
        p.alloted = birth;
        passengers.put(p.PassengerId, p);
        bookedList.add(p.PassengerId);
        System.out.println("Ticket Booked Successfully for " + p.name);
        System.out.println("-------------------------------------");
    }

    public void addToRac(Passenger p, int seatNumber, String birth) {
        p.number = seatNumber;
        p.alloted = birth;
        passengers.put(p.PassengerId, p);
        racList.add(p.PassengerId);
        racBirthPositions.remove(0);
        availableRacBirths--;
        System.out.println("Passenger " + p.name + " Added to RAC");
        System.out.println("---------------------------------");
    }

    public void addToWaitList(Passenger p, int seatNumber, String birth) {
        p.number = seatNumber;
        p.alloted = birth;
        passengers.put(p.PassengerId, p);
        waitList.add(p.PassengerId);
        waitListBirthPositions.remove(0);
        availableWaitListBirths--;
        System.out.println("Passenger " + p.name + " Added to Waiting List");
        System.out.println("------------------------------------------");
    }

    public void cancelTicket(int id) {
        Passenger p = passengers.get(id);
        passengers.remove(id);
        bookedList.remove(id);

        int positionBooked = p.number;
        System.out.println("Ticket Cancelled Successfully");
        System.out.println("-----------------------------");

        if (p.alloted.equals("L")) {
            availableLowerBirths++;
            lowerBirthPositions.add(positionBooked);
        } else if (p.alloted.equals("M")) {
            availableMiddleBirths++;
            middleBirthPositions.add(positionBooked);
        } else if (p.alloted.equals("U")) {
            availableUpperBirths++;
            upperBirthPositions.add(positionBooked);
        }

        if (racList.size() > 0) {
            Passenger passengerFromRAC = passengers.get(racList.poll());
            int racBookedPosition = passengerFromRAC.number;
            racBirthPositions.add(racBookedPosition);
            racList.remove(passengerFromRAC.PassengerId);
            availableRacBirths++;

            if (waitList.size() > 0) {
                Passenger passengerFromWaitList = passengers.get(waitList.poll());
                int waitListBookedPosition = passengerFromWaitList.number;
                waitListBirthPositions.add(waitListBookedPosition);
                waitList.remove(passengerFromWaitList.PassengerId);

                passengerFromWaitList.number = racBirthPositions.get(0);
                passengerFromWaitList.alloted = "RAC";
                racBirthPositions.remove(0);
                racList.add(passengerFromWaitList.PassengerId);

                availableWaitListBirths++;
                availableRacBirths--;
            }
            Main.bookTicket(passengerFromRAC);
        }
    }

    public void printAvailableTickets(){
        System.out.println("Available Lower Births        : "+availableLowerBirths);
        System.out.println("Available Middle Births       : "+availableMiddleBirths);
        System.out.println("Available Upper Births        : "+availableUpperBirths);
        System.out.println("Available RAC Births          : "+availableRacBirths);
        System.out.println("Available Waiting List Births : "+availableWaitListBirths);
        System.out.println("-------------------------------------");
    }

    public void printBookedPassengers(){
        if(passengers.size() == 0){
            System.out.println("No Passengers Booked");
        }
        else{
            for(Passenger p : passengers.values()){
                System.out.println("ID     : "+p.PassengerId);
                System.out.println("Name   : "+p.name);
                System.out.println("Age    : "+p.age);
                System.out.println("Status : "+p.number+" "+p.alloted);
                System.out.println("-------------------------");
            }
        }
    }
}
