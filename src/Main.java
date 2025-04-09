import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        try{
            /*
            * El ResulSet Se usa tanto en el insertar, Update y Delete. Elbusador usa un ResulsetPropio
            */
            Medico medico = new Medico("43409432F", "Antonio", 36, "Cabrera");


            /*Insertar*/
            //medico.insertar();
            /*Update*/
            //medico.update();
            /*Delete*/
            medico.delete();
            /*Buscar por nombre*/
            //ArrayList<Medico> medicos = Medico.buscador("g",3);
            //for (Medico m: medicos) {
            //    System.out.print(m.getNombre());
            //}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
