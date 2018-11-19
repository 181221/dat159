package no.pederyo;

public class Child extends Animal{

    public String test;
    public Child(String name, int age, int something) {
        super(name, age, something);
        this.test = "testen";
    }

    public Child() {
        super("Child", 13, 13);
    }
    public String sayHi(){
        return "hi from " + this.getName();
    }
}
