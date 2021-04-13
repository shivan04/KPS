package sr.unasat.kpsfinetracker.entities;

public class Vehicle {
    private long id;
    private String license_plate_number, vehicle_type;
    private Person person;

    public Vehicle(long id, String license_plate_number ,String vehicle_type, Person person) {
        this.id = id;
        this.license_plate_number = license_plate_number;
        this.vehicle_type = vehicle_type;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return license_plate_number;
    }

    public void setLicensePlateNumber(String license_plate_number) {
        this.license_plate_number = license_plate_number;
    }

    public String getVehicleType() {
        return vehicle_type;
    }

    public void setVehicleType(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
