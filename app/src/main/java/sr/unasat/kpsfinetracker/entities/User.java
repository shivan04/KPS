package sr.unasat.kpsfinetracker.entities;

public class User {
    private long id;
    private String username, password, station, region, district;
    private Person person;

    public User(long id, String username, String password, String station ,String region, String district, Person person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.station = station;
        this.region = region;
        this.district = district;
        this.person = person;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
