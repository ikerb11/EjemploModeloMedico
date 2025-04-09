import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Medico {

    //ATRIBUTOS
    private int id;
    private String dni;
    private String nombre;
    private String apellido_1;

    private String telefono;

    private String sexo;
    private static ResultSet resultSet;

    private int row;

    //CONSTRUCTORES
    public Medico() {
        setResultSet();
        setRow();
    }

    public Medico(String dni, String nombre, int id, String ape_1) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido_1 = ape_1;
        this.id = id;
        setResultSet();
        this.row=row;
    }
    public Medico(String dni, String nombre,  String ape_1) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido_1 = ape_1;
        setResultSet();
        this.row=row;

    }
    //GETTER Y SETTER


    public String getNombre() {
        return nombre;
    }

    public void setRow() {

    }
    public void setResultSet(){
        if (resultSet!=null){
            try {
                Statement stmt = Conexion.getConnection().createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                resultSet = stmt.executeQuery("select * from medicos");
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //METODOS



    /**
     * Inserta un registro en la t abla
     */
    public void insertar(){
        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt("id", id);
            resultSet.updateString("dni", dni);
            resultSet.updateString("nombre", nombre);
            resultSet.updateString("apellido1", apellido_1);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            row=resultSet.getRow();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    /**
     * Borra el registro que se le pase por el resulSet
     */
    public void delete(){
        try {
            if(row==0){
                resultSet.absolute(row);
            }else {
                resultSet.absolute(row-1);
            }
            if (resultSet.next()){
                resultSet.deleteRow();
                row=0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edita el registro a la que hace referencia esta instancia del modelo
     */
    public void update(){
        try {
                Statement stmt = Conexion.getConnection().createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet= stmt.executeQuery("select * from medicos where id like '"+id+"'");
                resultSet.absolute(1);
                resultSet.updateString("dni", dni);
                resultSet.updateString("nombre", nombre);
                resultSet.updateString("apellido1", apellido_1);
                resultSet.updateRow();
                resultSet.moveToCurrentRow();
                setRow();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Buscador que devolve una cantidad de registros buscando por el nombre
     * @param nombre el nombre por el que va a buscar
     * @param limite la cantidad maxima de registros que va a buscar
     * @return Un array list con los registros que ha encontrado. O null si no encuentra
     */
    public static ArrayList<Medico> buscador (String nombre, int limite) {
        ArrayList<Medico> medicos= new ArrayList<>();
        try {
            //Encuentro cuál es el número total de Rows encontrando la última
            Statement stmt = Conexion.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet= stmt.executeQuery("select * from medicos where nombre like '%"+nombre+"%'");
            while(resultSet.next()) {
                try {
                    System.out.println(resultSet.getRow());
                    //Si el registro contiene el nombre que se busca se añade al array
                    medicos.add(new Medico(resultSet.getString("dni"), resultSet.getString("nombre"),
                            resultSet.getString("apellido1")));
                    //Si me he pasado del último registro abandono el for
                    if (limite==medicos.size()){
                        break;
                    }

                }catch(SQLException ignored){}
            }
        }catch(SQLException e ){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return medicos;
    }


}