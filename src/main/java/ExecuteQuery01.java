import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","208518913");
        Statement statement=connection.createStatement();

        //Ornek 1: Region id'si 1 olan "country name" degerlerini cagirin
        String sql1="select country_name,region_id from countries where region_id=1";
        boolean r1=statement.execute(sql1);
        System.out.println("r1= "+r1);
        // Satirlari gormek icin executeQuery () kullannaliyiz, execute () sadece true false verir

        ResultSet resultSet= statement.executeQuery(sql1);
       // System.out.println(resultSet.next()); //next senden sonra var mi diye sorar bakar
       // System.out.println(resultSet.next());
      //  System.out.println(resultSet.getString(1)); //isvicre verir 2 next 2. siraya getirir

        //hepsini okumak istersek
        while (resultSet.next()){
            System.out.println(resultSet.getString("country_name"));
        }


        //- 2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        System.out.println("============ 2. Ornek ===============");
        String sql2="SELECT country_id, country_name FROM countries WHERE region_id>2";
        ResultSet resultSet2=statement.executeQuery(sql2);
        resultSet2.next();
        System.out.println(resultSet2.getString(2)); //avustralya verir bu bana

        //hepsini cagirmak icin while loop
        while (resultSet2.next()){ //resultSet son satira gelip false verdikten sonra kapanir, kapali ResultSet
            //uzerinde islem yapilirsa exception atar
            System.out.println(resultSet2.getString(1)+"--"+ resultSet2.getString(2));
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("\n********** 3.Örnek ***********");
        String sql3="SELECT * FROM companies where number_of_employees=(SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3=statement.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getObject(1)+"--"+resultSet3.getObject(2)+"--"+resultSet3.getObject(3));
        }

        connection.close();
        statement.close();



    }//main
}//class
