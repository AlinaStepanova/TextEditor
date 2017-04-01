package com.company;

import java.util.ArrayList;
import java.util.List;

public class Lab {

    public String changeWord(String wordStr, String maskStr) throws Exception {
        System.out.println("input param word: " + wordStr + " mask: " + maskStr);
        String result = "";

        int openBracketIndex = maskStr.indexOf('{');
        int comeIndex = maskStr.indexOf(',', openBracketIndex);
        int closeBracketIndex = maskStr.indexOf('}', comeIndex);

        //System.out.println("openbrindex " + openBracketIndex);

        if (openBracketIndex < 0 || comeIndex < 0 || closeBracketIndex < 0) {
            if (wordStr.equals(maskStr)) {
                return maskStr;
            } else {
                throw new Exception();
            }
        }
        int startInterval = Integer.valueOf(maskStr.substring(openBracketIndex + 1, comeIndex));
        int endInterval = Integer.valueOf(maskStr.substring(comeIndex + 1, closeBracketIndex));

        if (wordStr.substring(0, openBracketIndex)
                .equals(maskStr.substring(0, openBracketIndex))) {
            result += wordStr.substring(0, openBracketIndex);

            wordStr = wordStr.substring(openBracketIndex);
            String startChar = wordStr.substring(0, 1);
            int j = 1;
            while (j < wordStr.length() && wordStr.substring(j, j + 1).equals(startChar)) {
                ++j;
            }
            if (startInterval <= j && j <= endInterval) {
                for (int k = 0; k < endInterval; ++k) {
                    result += startChar;
                }
            }
            wordStr = wordStr.substring(j);
            maskStr = maskStr.substring(closeBracketIndex + 1);

            return result + changeWord(wordStr, maskStr);
        } else {
            throw new Exception();
        }
    }

    public List<String> start(List<String> words, String mask) {
        List<String> outputList = new ArrayList<>();
        for (String word: words) {
            try {
                String correctWord = changeWord(word, mask);
                outputList.add(correctWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outputList;
    }
}
