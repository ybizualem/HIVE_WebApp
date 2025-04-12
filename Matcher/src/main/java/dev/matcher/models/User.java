package dev.matcher.models;

public class User {
    String Name = "";
    String Recipient = "";
    String Courses = "";
    String Hours = "" + "";
    String University = "";
    String Location = "";
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String name, String recipient, String courses, String hours, String university, String location, Long id, String email, String password, String firstName, String lastName) {
        Name = name;
        Recipient = recipient;
        Courses = courses;
        Hours = hours;
        University = university;
        Location = location;
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String Name, String Courses, String Hours, String University, String Location){
        this.Name = Name;
        this.Courses = Courses;
        this.Hours = Hours;
        this.University = University;
        this.Location = Location;
    }
    public String getName() {
        return Name;
    }
    public String getCourses() {
        return Courses;
    }
    public String getHours() {
        return Hours;
    }
    public String getUniversity() {
        return University;
    }
    public String getLocation() {
        return Location;
    }
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


}