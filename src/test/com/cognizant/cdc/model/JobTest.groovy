package com.cognizant.cdc.model

import org.ansj.domain.Term
import org.ansj.splitWord.analysis.ToAnalysis
import org.junit.Test
import org.ansj.splitWord.analysis.IndexAnalysis

class JobTest {


    @Test
    void test() {
        List<Term> parse = IndexAnalysis.parse("大龄女青年");
        for(Term term : parse) {
            String nature = term.getNatureStr()
//            if(nature != "null" && ( nature == "en" || nature.startsWith("n") || nature.startsWith("v") || nature.startsWith("a") )) {
                println term.getName() + "  " + term.getNatureStr()
//            }
        }
    }
}
