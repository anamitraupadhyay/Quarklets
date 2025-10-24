package main.java;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.AutoBinder;

public class InputPOJOReflectionless extends AutoBinder {
    private String name;
    private int age;

    public InputPOJO() {
    }

    public InputPOJO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void bind() {
        if (jsonTree == null) return;
        
        // Use the helper methods from AutobindInterface to extract values
        this.name = getString("Name", jsonTree);
        this.age = getInt("age", jsonTree);
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
