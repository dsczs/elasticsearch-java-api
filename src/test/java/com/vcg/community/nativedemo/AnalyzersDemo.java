package com.vcg.community.nativedemo;

import com.vcg.community.elasticsearch.BaseDemo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import java.io.IOException;
import java.io.StringReader;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/guide/1.x/using-language-analyzers.html</a>
 * 测试各种分词器
 * @author lei.fang
 * @since 2017/3/10
 */
public class AnalyzersDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        String text = "我是中国人";
        displayToken(text,new StandardAnalyzer());
        displayToken(text,new SimpleAnalyzer());
        displayToken(text,new EnglishAnalyzer());
        displayToken(text,new ClassicAnalyzer());
        displayToken(text,new KeywordAnalyzer());
    }

    private static void displayToken(String text,Analyzer analyzer){
        try {
            //将一个字符串创建成Token流
            TokenStream stream  = analyzer.tokenStream("", new StringReader(text));
            stream.reset();
            //保存相应词汇
            CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
            while(stream.incrementToken()){
                System.out.print(cta+",");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
