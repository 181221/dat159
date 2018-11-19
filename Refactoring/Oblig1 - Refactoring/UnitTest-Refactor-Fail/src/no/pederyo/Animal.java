package no.pederyo;

public class Animal {
    protected int something;
    private String name;
    private int age;

    public Animal(String name, int age, int something) {
        this.name = name;
        this.age = age;
        this.something = something;
    }

    public Animal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String sayHi(){
        return "hi from " + this.getName();
    }
}
