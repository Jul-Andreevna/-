package AbstractFactory;
import java.util.Scanner;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        Factory passengerFactory = new AbstractFactory().createFactory("Passenger");
        Factory vehicleFactory = new AbstractFactory().createFactory("Vehicle");
        Factory driverFactory = new AbstractFactory().createFactory("Driver");
        Passenger taxiPassenger = passengerFactory.createPassenger("TaxiPassenger");
        Passenger busPassenger = passengerFactory.createPassenger("BusPassenger");
        Driver taxiDriver = driverFactory.createDriver("TaxiDriver");
        Driver busDriver = driverFactory.createDriver("BusDriver");
        Vehicle bus = vehicleFactory.createVehicle("Bus");
        Vehicle taxi = vehicleFactory.createVehicle("Taxi");
        bus.driver_in();
        bus.passenger_in();
        bus.go_to_race();
        taxi.passenger_in();
        taxi.go_to_race();
    }
}

interface Passenger {
    void initialisation();
}

class TaxiPassenger implements Passenger {
    public void initialisation() {}
}

class BusPassenger implements Passenger {
    public void initialisation() {}
}

class PassengerFactory implements Factory {
    public Passenger createPassenger(String typeOfPassenger) {
        switch (typeOfPassenger) {
            case "TaxiPassenger" : return new TaxiPassenger();
            case "BusPassenger" : return new BusPassenger();
            default: return null;
        }
    }

    @Override
    public Vehicle createVehicle(String typeOfVehicle) {
        return null;
    }

    @Override
    public Driver createDriver(String typeOfDriver) {
        return null;
    }
}

interface Vehicle {
    void initialisation();
    void driver_in();
    void passenger_in();
    void go_to_race();
}

class Taxi implements Vehicle {
    int min_number_of_passenger = 0;
    int max_number_of_passenger = 4;
    boolean driver = false;
    boolean passenger = false;

    @Override
    public void initialisation() {
    }

    @Override
    public void driver_in() {
        if (driver == false)
        {
            driver = true;
            System.out.println("Водитель сел в такси");
        }
        else {
            System.out.println("Водитель уже в такси");
        }
    }

    @Override
    public void passenger_in() {
        int passenger_in_taxi = 0;
        if (passenger_in_taxi <= max_number_of_passenger) {
            System.out.println("Количество людей, садящихся в такси: ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            passenger_in_taxi = passenger_in_taxi + number;
            if (passenger_in_taxi > min_number_of_passenger && passenger_in_taxi <= max_number_of_passenger) {
                passenger = true;
            }
        }
    }

    @Override
    public void go_to_race() {
        if (driver && passenger) {
            System.out.println("Такси поехало");
        }
        if (driver==false && passenger) {
            System.out.println("Без водителя такси не поедет");
        }
        if (driver && passenger==false){
            System.out.println("Без нужного количества пассажиров такси не поедет");
        }
    }
}

class Bus implements Vehicle {
    int min_number_of_passenger = 0;
    int max_number_of_passenger = 30;
    boolean driver = false;
    boolean passenger = false;

    @Override
    public void initialisation() {
    }

    @Override
    public void driver_in() {
        if (driver == false)
        {
            driver = true;
            System.out.println("Водитель сел в автобус");
        }
        else {
            System.out.println("Водитель уже в автобусе");
        }
    }

    @Override
    public void passenger_in() {
        int passenger_in_bus = 0;
        if (passenger_in_bus <= max_number_of_passenger) {
            System.out.println("Количество людей, садящихся в автобус: ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            passenger_in_bus = passenger_in_bus + number;
            if (passenger_in_bus > min_number_of_passenger && passenger_in_bus <= max_number_of_passenger) {
                passenger = true;
            }
        }
    }

    @Override
    public void go_to_race() {
        if (driver && passenger) {
            System.out.println("Автобус поехал");
        }
        if (driver==false && passenger) {
            System.out.println("Без водителя автобус не поедет");
        }
        if (driver && passenger==false) {
            System.out.println("Без нужного количества пассажиров автобус не поедет");
        }
    }
}

class VehicleFactory implements Factory {
    public Vehicle createVehicle(String typeOfVehicle) {
        switch (typeOfVehicle) {
            case "Taxi" : return new Taxi();
            case "Bus" : return new Bus();
            default: return null;
        }
    }

    @Override
    public Driver createDriver(String typeOfDriver) {
        return null;
    }

    @Override
    public Passenger createPassenger(String typeOfPassenger) {
        return null;
    }
}

interface Driver {
    void initialisation();
}

class TaxiDriver implements Driver {
    public void initialisation() {
        String car_license = "Taxi";
    }
}

class BusDriver implements Driver {
    public void initialisation() {
        String car_license = "Bus";
    }
}

class DriverFactory implements Factory {
    public Driver createDriver(String typeOfDriver) {
        switch (typeOfDriver) {
            case "TaxiDriver" : return new TaxiDriver();
            case "BusDriver" : return new BusDriver();
            default: return null;
        }
    }

    @Override
    public Passenger createPassenger(String typeOfPassenger) {
        return null;
    }

    @Override
    public Vehicle createVehicle(String typeOfVehicle) {
        return null;
    }
}

interface Factory {
    Passenger createPassenger(String typeOfPassenger);
    Vehicle createVehicle(String typeOfVehicle);
    Driver createDriver(String typeOfDriver);
}

class AbstractFactory { //фабрика фабрик
    Factory createFactory(String typeOfFactory){
        switch (typeOfFactory) {
            case "Passenger" : return new PassengerFactory();
            case "Vehicle" : return new VehicleFactory();
            case "Driver" : return new DriverFactory();
            default : return null;
        }
    }
}