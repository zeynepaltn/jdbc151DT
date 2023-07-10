import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class JunitTest01 {

    @Test
    public void test01(){

        assertEquals(1,1);//assertEquals() methodunda parantez içindeki parametreler birbirne eşitse test geçer değilse kalır.
        assertTrue("Merhaba".contains("a"));//assertTrue() methodunun parantez içindeki koşul true ise test geçer, değilse kalır

    }

}