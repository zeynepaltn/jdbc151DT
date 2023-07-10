import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {
    /*
 Given
   User connects to the database
   (Kullanıcı veritabanına bağlanır)

 When
   User sends the query to get the region ids from "countries" table
   (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

 Then
   Assert that the number of region ids greater than 1 is 17.
   (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

 And
   User closes the connection
*/

    @Test
    public void countryTest() throws SQLException { //test methodlari public ve void olur ve parametresi olmaz
        //User connects to database
        JdbcUtils.connectToDataBase();
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
    String sql="select count(region_id) from countries where region_id>1";
    ResultSet resultSet=JdbcUtils.executeQuery(sql);

        //Assert that the number of region ids greater than 1 is 17.
        int number=0;
    while(resultSet.next()){
        number=resultSet.getInt(1);
    }
        assertEquals(17,number);

    }

    @Test
    public void countryTest02() throws SQLException {
        //User connects to database
        JdbcUtils.connectToDataBase();
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql="select region_id from countries where region_id>1";
        ResultSet resultSet=JdbcUtils.executeQuery(sql);
        List<Integer> region_idList=new ArrayList<>();

        while(resultSet.next()){
            region_idList.add(resultSet.getInt(1));

        }
        System.out.println("region_idList= "+ region_idList);
        assertEquals(17,region_idList.size()); //region_idList in eleman sayisi 17 olmali
        JdbcUtils.closeConnection();
    }


}
