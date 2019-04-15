package com.example.edesia.middle;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexMatcher {
    static String pattern;

    public regexMatcher() {

    }

    public static List<String>PatternMatch(List<String> list, Set set) {
        Pattern replace = Pattern.compile("(tsp.)*(lb.)*(tbsp.)*(c.)*(oz.)*\\d*([,.])*");
        pattern = "(\\btsp)(\\blb)(\\btbsp)(\\bc)(\\boz)(\\d)([\\.,])";
        String blank = "";
        StringBuilder sbList = new StringBuilder();
        for (String sList : list)
        {
            sbList.append(sList);
            //sbList.append("\t");
        }

        System.out.println(sbList.toString());

      //  System.out.println(sbList.replaceAll(pattern));

        //Pattern pattern = Pattern.compile("\\w+");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = replace.matcher(sbList);

        // check all occurance
        while (matcher.find()) {
            //System.out.print("Start index: " + matcher.start());
            //System.out.print(" End index: " + matcher.end() + "");
            System.out.println(matcher.group());
        }
        System.out.println(matcher.replaceAll(blank));
        System.out.println(matcher);
        // now create a new pattern and matcher to replace whitespace with tabs
        //Pattern replace = Pattern.compile("\\s+");
        //Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        //System.out.println(matcher2.replaceAll("\t"));

        return list;
    }
}
