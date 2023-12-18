import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try{
            Statement stmt = (Statement) Conexion.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            /*
            * El ResulSet Se usa tanto en el insertar, Update y Delete. Elbusador usa un ResulsetPropio
            */
            ResultSet resultSet = stmt.executeQuery("select * from medicos");
            Modelo modelo = new Modelo("295867K", "Antoni", "Cabrera" );

            if (resultSet.next()){
                /*Insertar*/
                //modelo.insertar(resultSet);
                /*Update*/
                //modelo.update(resultSet, resultSet.getRow());
                /*Delete*/
                //m.delete(resultSet);
                /*Buscar por nombre*/
                for ( Modelo m: modelo.buscador("A", 15)){
                    System.out.println(m.getNombre());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
