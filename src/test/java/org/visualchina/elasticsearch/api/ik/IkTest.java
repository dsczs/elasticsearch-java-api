package org.visualchina.elasticsearch.api.ik;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.common.settings.Settings;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-05
 */
public class IkTest {
    public static void main(String []args) throws IOException {
        Settings settings  = Settings.EMPTY;
        IKAnalyzer analyzer = new IKAnalyzer();
        String text = "中华人民共和国国歌";
        StringReader stringReader = new StringReader(text);
        TokenStream tokenStream = analyzer.tokenStream("",stringReader);
        tokenStream.reset();
        CharTermAttribute term=tokenStream.getAttribute(CharTermAttribute.class);
        while(tokenStream.incrementToken()){
            System.out.print(term.toString()+"—");
        }
        stringReader.close();
        tokenStream.close();
    }
}
