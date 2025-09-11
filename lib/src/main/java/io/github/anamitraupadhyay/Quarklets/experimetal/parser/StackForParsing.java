package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

public class StackForParsing {
    int length;

    public int getLength() {
        return length;
    }

    public char[] array;
    public StackForParsing(StringBuilder obj){
        this.length = obj.length();
        this.array = new char[length];
    }

}