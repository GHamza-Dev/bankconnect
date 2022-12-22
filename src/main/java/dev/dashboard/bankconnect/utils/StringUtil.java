package dev.dashboard.bankconnect.utils;

public class StringUtil {

    public static String extractSuffix(String target,Character delimiter){
        if (!target.contains(delimiter.toString())) return target;

        int delimiterIndex = target.lastIndexOf(delimiter);

        return target.substring(delimiterIndex+1);
    }

    public static String extractPrefix(String target,Character delimiter){
        if (!target.contains(delimiter.toString())) return target;

        int delimiterIndex = target.lastIndexOf(delimiter);

        return target.substring(0,delimiterIndex);
    }
}
