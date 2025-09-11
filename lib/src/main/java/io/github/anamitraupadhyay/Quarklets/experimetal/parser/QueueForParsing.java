package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

public class QueueForParsing {
    int length;

    public int getLength() {
        return length;
    }

    public char[] array;
    public QueueForParsing(StringBuilder obj){
        this.length = obj.length();
        this.array = new char[length];
    }

}

