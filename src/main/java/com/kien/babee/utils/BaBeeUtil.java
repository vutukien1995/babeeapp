package com.kien.babee.utils;

import java.util.ArrayList;
import java.util.List;

public class BaBeeUtil {

    public static String paragraph = "From VOA Learning English, this is the Health and Lifestyle Report.\n" +
            "\n" +
            "American drug-maker Pfizer says its experimental vaccine appears to be more than 90 percent effective in preventing COVID-19.\n" +
            "\n" +
            "The information came Monday from early results of a Phase 3 study involving about 44,000 volunteers in the United States and other countries. An independent group of scientists also examined the study.\n" +
            "\n" +
            "Pfizer and its German partner BioNTech are the first drug-makers to show successful data from a large Phase 3 trial of a coronavirus vaccine. The companies also said they have found no serious safety concerns in the testing so far. And they expect to seek emergency use permission from the U.S. Food and Drug Administration (FDA) later this month.\n" +
            "\n" +
            "Albert Bourla is Pfizer’s chairman and chief. He said in a statement, “Today is a great day for science and humanity. The first set of results from our Phase 3 COVID-19 vaccine trial provides the initial evidence of our vaccine’s ability to prevent COVID-19.”\n" +
            "\n" +
            "Bourla added, “With today’s news, we are a significant step closer to providing people around the world with a much-needed breakthrough to help bring an end to this global health crisis. We look forward to sharing additional efficacy and safety data generated from thousands of participants in the coming weeks.”";

    public static void main(String[] args) {
        List<String> list = getListSentences(paragraph);

        for(String s: list) {
            System.out.println(s);
        }

        System.out.println();
    }

    public static List<String> getListSentences (String paragraph) {
        ArrayList<String> list = new ArrayList<>();

        paragraph = paragraph.replaceAll("\n", "");
        String[] sentences = paragraph.split("\\.");

        for (String sentence : sentences) {
            list.add(sentence);
        }

        return list;
    }
}
