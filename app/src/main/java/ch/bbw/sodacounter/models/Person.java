package ch.bbw.sodacounter.models;

public class Person {
    private String name = "";
    private float weight = 0;
    private int goalSodaPerWeek = 0;
    private int age = 0;

    public Person(String name, float weight, int goalSodaPerWeek, int age, int notificationTime) {
        this.name = name;
        this.weight = weight;
        this.goalSodaPerWeek = goalSodaPerWeek;
        this.age = age;
        this.notificationTime = notificationTime;
    }

    private int notificationTime = 15;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getGoalSodaPerWeek() {
        return goalSodaPerWeek;
    }

    public void setGoalSodaPerWeek(int goalSodaPerWeek) {
        this.goalSodaPerWeek = goalSodaPerWeek;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(int notificationTime) {
        this.notificationTime = notificationTime;
    }
}
