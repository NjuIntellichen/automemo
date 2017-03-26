package APITest;

import com.intellichens.api.TextAnalyzeAPI;
import com.intellichens.api.impl.TextAnalyzeImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raychen on 2017/3/26.
 */
public class TextAnalyzeTest {
    TextAnalyzeAPI api;

    @Before
    public void setup(){
        api = new TextAnalyzeImpl();
    }

    @Test
    public void testKeys(){
        String text = "The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licences. As of May 2007, in compliance with the specifications of the Java Community Process, Sun relicensed most of its Java technologies under the GNU General Public License. Others have also developed alternative implementations of these Sun technologies, such as the GNU Compiler for Java (bytecode compiler), GNU Classpath (standard libraries), and IcedTea-Web (browser plugin for applets).";
        String key = api.getKeys(text);
        JSONObject returnValue = new JSONObject(key);
        JSONArray docs = (JSONArray) returnValue.get("documents");
        for (int i = 0; i < docs.length(); i++) {
            JSONObject doc = (JSONObject) docs.get(i);
            JSONArray keys = (JSONArray) doc.get("keyPhrases");
            for (int j = 0; j < keys.length(); j++) {
                System.out.println(keys.get(j));
            }
        }
    }
}
