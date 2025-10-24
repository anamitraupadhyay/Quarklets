package main.java;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.AutoBinder;

public class InputPOJOReflectionless extends AutoBinder {
    private String name;
    private int age;

    public InputPOJOReflectionless() {
    }

    public InputPOJOReflectionless(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void bind() {
        // Use the helper methods from AutobindInterface to extract values
        this.name = getString("Name");
        this.age = getInt("age");
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

    @Override
    public String toString() {
        return "InputPOJO{name='" + name + "', age=" + age + "}";
    }
}
