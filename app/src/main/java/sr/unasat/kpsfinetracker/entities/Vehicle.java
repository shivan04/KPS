package sr.unasat.kpsfinetracker.entities;

public class Vehicle {
    private long id;
    private String license_plate_number, model, vehicle_type;
    private Person person;

    public Vehicle(long id, String license_plate_number, String model, String vehicle_type, Person person) {
        this.id = id;
        this.license_plate_number = license_plate_number;
        this.model = model;
        this.vehicle_type = vehicle_type;
        this.person = person;
    }

}
