package com.company;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> words;
        String pathIn = "C:\\Users\\Alina\\IdeaProjects\\syst_programming_1\\src\\com\\company\\input.txt";
        FilesWork filesWork = new FilesWork();
        words = filesWork.readFromFile(pathIn);

        new TextEditor(words);
    }
}
