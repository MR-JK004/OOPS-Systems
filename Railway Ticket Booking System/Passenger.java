public class Passenger {
    static int id = 1;
    int PassengerId;
    String name;
    int age;
    String birthPreference;
    String alloted;
    int number;

    Passenger(String name,int age,String birthPreference){
        this.name = name;
        this.age = age;
        this.birthPreference = birthPreference;
        PassengerId = id++;
        alloted = "";
        number = -1;
    }
}
