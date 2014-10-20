package com.cognizant.cdc.util

import org.ansj.domain.Term
import org.ansj.splitWord.analysis.IndexAnalysis
import org.jsoup.Jsoup

class TextParserUtil {

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
}
