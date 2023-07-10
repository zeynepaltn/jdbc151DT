import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "208518913");
        Statement statement = connection.createStatement();
    //SELAMLAMA YAPAN BIR FUNCTION OLUSTURUP CALISTIRINIZ

    //Callable Statement Adimlari:
    // 1. Adim: Function kodunu yaziniz
    String sql="CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS $$ BEGIN RETURN 'Merhaba ' || x || ', nasılsın?'; END; $$ LANGUAGE plpgsql;";

    //2. Adim: Function icin olusturdugumuz kodu calistiriyoruz
    statement.execute(sql); //function olusturan query'i calistirdik


   // String sql2="Select selamlama('Ali')"; ==>Burasi callable statement kullanmadan funtion'i cagirma islemi
    //ResultSet resultSet= statement.executeQuery(sql2);//function'u parametre ile cagirdik, bize bir table dondu
    //resultSet.next();
      //  System.out.println(resultSet.getObject(1));

        //1. ornek: Selamlama yapan bir function'i callable statement ile cagiriniz




        //3. Adim: Funtion'i cagiriyorum
        CallableStatement callableStatement=connection.prepareCall("{? = call selamlama(?)}"); //1. soru isareti return type, 2. soru isareti parametremiz

        //4. Adim: Return icin registeroutParameter() methodunu, parametreler icin setInt, setString vs kullaniyoruz
        callableStatement.registerOutParameter(1, Types.VARCHAR); //ilk ?
        callableStatement.setString(2,"Ali"); //Ikinci ?

        //5. Adim: execute() ile callableStatement'i calistir
        callableStatement.execute();

        //6. Adim: CallableStatement'imin icindeki data'yi okuyorum
        //CallableStatement'da data resultSet icinde alinmaz. Direk callableStatement'dan alinir
        System.out.println(callableStatement.getString(1));


    //2. Ornek:Iki sayiyi toplayan bir function yaziniz ve callableStatement ile cagiriniz

        // 1. Adim: Function kodunu yaziniz
    String sql2="CREATE OR REPLACE FUNCTION toplama(x NUMERIC, y NUMERIC) \n" +
            "RETURNS NUMERIC AS $$ BEGIN RETURN x+y; END; $$ LANGUAGE plpgsql;";

    //2. Adim: funtion kodunu calistir
        statement.execute(sql2);
    //3. adim: Funtion'i cagir
        CallableStatement callableStatement2=connection.prepareCall("{? = call toplama(?,?)}");
    //4. Adim: Return icin registeroutParameter() methodunu, parametreler icin setInt
    callableStatement2.registerOutParameter(1, Types.NUMERIC);
    callableStatement2.setInt(2,6);
    callableStatement2.setInt(3,4);
    //5. Adim: callebStatement'i execute ile calistir
    callableStatement2.execute();
    //6. adim: Sonucu gormek icin callableStatement'dan data turunu cagir
        System.out.println(callableStatement2.getBigDecimal(1));

        connection.close();
        statement.close();

    }
}
