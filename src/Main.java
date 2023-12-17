import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try{
            Statement stmt = (Statement) Conexion.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = stmt.executeQuery("select * from medicos where id = 1");
            Modelo modelo = new Modelo("311231233", "Manolo", "Maurisio" );
            //mm.insertar(rs);
            for ( Modelo m: modelo.buscador("A")){
                if (resultSet.next()){
                    m.delete(resultSet,resultSet.getRow());
                }
                //System.out.println(m.getNombre());
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
