package com.beyond.di.owner;


import com.beyond.di.pet.Pet;
import com.beyond.di.pet.Pet;

public class Owner {
    private String name;

    private int age;

    private Pet pet;

    public Owner() {
    }

    public Owner(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet= pet;
    }

    public int getAge() {
        return age;
    }

    public Pet getPet() {
        return pet;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPet(Pet Pet) {
        this.pet = Pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }
}
