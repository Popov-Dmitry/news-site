package com.github.PopovDmitry.nstu.webcw.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BBParserUtil {

    private static final Hashtable<Character, String> startTag = new Hashtable<>();
    private static final Hashtable<Character, String> endTag = new Hashtable<>();
    private static final List<Character> bbCodes = new ArrayList<>();

    static {
        //bbCodes.put("\\[b\\][.*]\\[/b\\]", "<span style='font-style:bold;'>$1</span>");
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
        //List<Point> indexes = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (bbCodes.contains(chars[i]) && i > 0 && i < chars.length - 1 && chars[i - 1] == '[' && chars[i + 1] == ']') {
                char bbcode = chars[i];
                for(int j = i; j < chars.length; j++) {
                    if (chars[j] == bbcode && j > 1 && j < chars.length - 1 && chars[j - 2] == '[' && chars[j - 1] == '/' && chars[j + 1] == ']') {
                        chars[i - 1] = '<';
                        chars[i + 1] = '>';
                        chars[j - 2] = '<';
                        chars[j + 1] = '>';
                        //indexes.add(new Point(i, j));
                        break;
                    }
                }
            }
        }

//        StringBuilder stringBuilder = new StringBuilder(String.valueOf(chars));
//        //int offset = 0;
//        int startOffset = 0;
//        int endOffset = 0;
//        for(int i = 0; i < indexes.size(); i++) {
//            stringBuilder.replace((int)indexes.get(i).getX() + startOffset, (int)indexes.get(i).getX() + 1 + startOffset, startTag.get(chars[(int)indexes.get(i).getX()]));
//            stringBuilder.replace((int)indexes.get(i).getY() + startTag.get(chars[(int)indexes.get(i).getX()]).length() - 1 + endOffset, (int)indexes.get(i).getY() + startTag.get(chars[(int)indexes.get(i).getX()]).length() + endOffset, endTag.get(chars[(int)indexes.get(i).getY()]));
//            //offset = offset + startTag.get(chars[(int)indexes.get(i).getX()]).length() - 1 + endTag.get(chars[(int)indexes.get(i).getY()]).length() - 1;
//            if(i < indexes.size() - 1 && indexes.get(i + 1).getX() < indexes.get(i).getY()) {
//                startOffset += startTag.get(chars[(int)indexes.get(i).getX()]).length() - 1;
//                endOffset += endTag.get(chars[(int)indexes.get(i).getY()]).length() - 1;
//            }
//            if(i < indexes.size() - 1 && indexes.get(i + 1).getX() > indexes.get(i).getY()) {
//                startOffset += startTag.get(chars[(int)indexes.get(i).getX()]).length() - 1 + endTag.get(chars[(int)indexes.get(i).getY()]).length() - 1;
//                endOffset = startOffset;
//            }
//        }
//        System.out.println(stringBuilder.toString());
//        return stringBuilder.toString();

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
