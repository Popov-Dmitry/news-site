package com.github.PopovDmitry.nstu.webcw.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BBParserUtil {

    private static final Hashtable<Character, String> startTag = new Hashtable<>();
    private static final Hashtable<Character, String> endTag = new Hashtable<>();
    private static final List<Character> bbCodes = new ArrayList<>();

    static {
        bbCodes.add('b');
        startTag.put('b', "<span style='font-weight:bold;'>");
        endTag.put('b', "</span>");
        bbCodes.add('i');
        startTag.put('i', "<span style='font-style:italic;'>");
        endTag.put('i', "</span>");
        bbCodes.add('u');
        startTag.put('u', "<span style='text-decoration:underline;'>");
        endTag.put('u', "</span>");
        bbCodes.add('p');
        startTag.put('p', "<p>");
        endTag.put('p', "</p>");
        bbCodes.add('c');
        startTag.put('c', "<div align='center'>");
        endTag.put('c', "</div>");
    }


    public static String parse(String text) {
        char[] chars = text.toCharArray();
        String[] strings = String.valueOf(chars).split("[<]([b]|[i]|[u]|[p]|[c])[>][.][<][/]([b]|[i]|[u]|[p]|[c])[>]");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            for(int j = 0; j < bbCodes.size(); j++) {
                strings[i] = strings[i].replace("<" + bbCodes.get(j) + ">", startTag.get(bbCodes.get(j)));
                strings[i] = strings[i].replace("</" + bbCodes.get(j) + ">", endTag.get(bbCodes.get(j)));
            }

            result.append(strings[i]);
        }

        return result.toString();
    }
}
