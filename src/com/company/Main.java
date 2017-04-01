package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> words;
        String pathIn = "/Users/vova/Desktop/TextEditor/src/com/company/input.txt";
        FilesWork filesWork = new FilesWork();
        words = filesWork.readFromFile(pathIn);

        new TextEditor(words);
    }


    /*public void main(String[] args) {
        Lab lab = new Lab();
        List<String>
        lab.start()
    }*/
}
