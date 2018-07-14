package com.gzxant.utils;

/**
 * 文本工具
 * @author: Fatal
 * @date: 2018/7/14 0014 19:07
 */
public class TextUtil {

    public static String subString(String str) {
        char[] charArray = str.toCharArray();
        Integer begin = 0;
        Integer end = 0;
        String all = "";
        String newStr = "";
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '>' && i != charArray.length - 1 && charArray[i+1] != '<' ) {
                begin = i + 1;
                continue;
            }
            if (charArray[i] == '<' && i != 0 && charArray[i-1] != '>') {
                end = i;
                newStr = str.substring(begin, end);
                all += newStr;
                begin = i + 1;
            }
        }
        if (all.length() > 50) {
            all = all.substring(0, 49);
            all += "...";
        }
        return all;
    }

    public static String catchImgUrl(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'i') {
                str = str.substring(4);
                if (str.startsWith("img src=")) {
                    str = str.substring(str.indexOf('"') + 1);
                    break;
                }
            }
        }
        str = str.substring(0, str.indexOf('"'));
        return str;
    }

}
