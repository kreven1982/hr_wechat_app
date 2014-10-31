package com.cognizant.cdc.util

import org.ansj.domain.Term
import org.ansj.splitWord.analysis.IndexAnalysis
import org.jsoup.Jsoup
import java.security.MessageDigest

class Utils {

    public static List<String> parseKeywords(String source) {
        List<Term> parse = IndexAnalysis.parse(source);
        List<String> keywords = []

        for(Term term : parse) {
            String nature = term.getNatureStr()
            String keyword = term.getName()
            if(nature != "null" && ( nature.startsWith("n") || nature.startsWith("v") || nature.startsWith("a") )) {
                keywords.add(term.getName())
            } else if (nature == "en") {
                if(keyword.length() >= 3) {
                    keywords.add(term.getName())
                }
            }
        }

        keywords
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public static String md5(byte[] content) {
        MessageDigest digest = java.security.MessageDigest
                .getInstance("MD5")
        digest.reset()
        digest.update(content)
        byte[] hash = digest.digest()
        StringBuffer hexString = new StringBuffer()
        String hex
        for (int i = 0; i < hash.length; i++) {
            hex = Integer.toHexString((0xFF & hash[i]) as int)
            if (hex.length() == 1)
                hexString.append('0')

            hexString.append(hex)
        }

        return hexString.toString()
    }

    public static String sha1(String content) {

        MessageDigest digest = java.security.MessageDigest
                .getInstance("SHA1")
        digest.reset()
        digest.update(content.getBytes())
        byte[] hash = digest.digest()
        StringBuffer hexString = new StringBuffer()
        String hex
        for (int i = 0; i < hash.length; i++) {
            hex = Integer.toHexString((0xFF & hash[i]) as int)
            if (hex.length() == 1)
                hexString.append('0')

            hexString.append(hex)
        }

        return hexString.toString()
    }
}
