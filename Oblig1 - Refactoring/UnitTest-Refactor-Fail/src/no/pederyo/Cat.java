package no.pederyo;

public class Cat extends Animal {
    Child child;

    public Cat(String name, int age, int something) {
        super(name, age, something);
        this.child = new Child();
    }

    public Cat() {
        super("Cat", 13, 13);
        this.child = new Child();
    }


}
