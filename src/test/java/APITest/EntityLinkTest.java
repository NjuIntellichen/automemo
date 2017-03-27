package APITest;

import com.intellichens.api.EntityLinkAPI;
import com.intellichens.api.impl.EntityLinkImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raychen on 2017/3/26.
 */
public class EntityLinkTest {
    EntityLinkAPI api;

    @Before
    public void setup(){
        this.api = new EntityLinkImpl();
    }

    @Test
    public void testGetLinks(){
        String text = "The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licences. As of May 2007, in compliance with the specifications of the Java Community Process, Sun relicensed most of its Java technologies under the GNU General Public License. Others have also developed alternative implementations of these Sun technologies, such as the GNU Compiler for Java (bytecode compiler), GNU Classpath (standard libraries), and IcedTea-Web (browser plugin for applets).";
        String ans = api.getEntities(text, null, -1);
        System.out.println(ans);
    }
}
