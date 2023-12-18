import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {

    //ATRIBUTOS
    private int id;
    private String dni;
    private String nombre;
    private String apellido_1;

    private Date fecha_nac;
    //CONSTRUCTORES
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
    //GETTER Y SETTER

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
    //METODOS

    /**
     * Inserta un registro en la tabla
     * @param resultSet el resultado de una querry
     * @return true si ha sido exitoso o false si no lo ha sido
     */
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

    /**
     * Borra el registro que se le pase por el resulSet
     * @param resultSet el resultado de una querry
     * @return true si ha sido exitoso o false si no lo ha sido
     */
    public boolean delete(ResultSet resultSet){
        try {
            resultSet.deleteRow();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Edita el registro que se le pase por el resulSet
     * @param resultSet el resultado de una querry
     * @param numFila La fila donde se encuentra el registro
     * @return true si ha sido exitoso o false si no lo ha sido
     */
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

    /**
     * Buscador que devolve una cantidad de registros buscando por el nombre
     * @param nombre el nombre por el que va a buscar
     * @param limite la cantidad maxima de registros que va a buscar
     * @return Un array list con los registros que ha encontrado. O null si no encuentra
     */
    public ArrayList<Modelo> buscador (String nombre, int limite) {
        ArrayList<Modelo> medicos= new ArrayList<Modelo>();
        try (Statement st = Conexion.getConnection().createStatement()){
            ResultSet resultSet = st.executeQuery("select * from medicos where nombre like '"+"%"+nombre+"%'");
            for (int i=0;resultSet.next()&& i < limite; i++) {
                //La columna index 2 3 y 4 son el dni el nombre y el apellido1 en la base de datos
                medicos.add(new Modelo(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        }catch(SQLException | ClassNotFoundException e){

            return null;
        }
        return medicos;
    }



}