
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {


    private int id;

    private String dni;
    private String nombre;

    private String apellido_1;

    private int numFila =2;

    private Date fecha_nac;
    public Modelo() {
    }

    public Modelo(String dni, String nombre, int id, String ape_1) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido_1 = ape_1;
        this.id = id;
    }
    public Modelo(String dni, String nombre,  String ape_1) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido_1 = ape_1;

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_1() {
        return apellido_1;
    }

    public void setApellido_1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }
    public boolean insertar(ResultSet resultSet){
        try {

            resultSet.moveToInsertRow();
            resultSet.updateString("dni", dni);
            resultSet.updateString("nombre", nombre);
            resultSet.updateString("apellido1", apellido_1);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            return true;

        } catch (SQLException e) {

            return false;

        }

    }
    public boolean delete(ResultSet resultSet, int numFila){

        try {
            resultSet.deleteRow();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean update(ResultSet resultSet , int numFila){
        try {

            resultSet.absolute(numFila);
            resultSet.updateString("dni", dni);
            resultSet.updateString("nombre", nombre);
            resultSet.updateString("apellido1", apellido_1);
            resultSet.updateRow();
            resultSet.moveToCurrentRow();
            return true;

        } catch (SQLException e) {

            return false;

        }
    }
    public ArrayList<Modelo> buscador (String nombre) {
        int contador=0;


        ArrayList<Modelo> medicos= new ArrayList<Modelo>();
        try (Statement st = Conexion.getConnection().createStatement()){
            ResultSet resultSet = st.executeQuery("select * from medicos where nombre like '"+"%"+nombre+"%'");
            while(resultSet.next()&& contador < 15) {
                contador++;
                medicos.add(new Modelo(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
        }catch(SQLException | ClassNotFoundException e){

            return null;
        }
        return medicos;
    }



}