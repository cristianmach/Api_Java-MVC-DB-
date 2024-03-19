package DataBase;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Connections {

    Connection connection = null;
    
    public String obtener_url(){
        String sjdbc = "jdbc:sqlite";
        Path path = Paths.get("src/DataBase/CuerpoDeAgua.db");
        String url = sjdbc + ":\\" + path.toAbsolutePath();

        return url;
    }
    
    public void conectar() throws SQLException{
        try{
            connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             System.out.println("Conexión exitosa!");
          }
        }
        catch ( Exception ex ) {
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }   
        try{
            connection.close();
        }catch ( Exception ex ) {
            System.out.println("Error en la conexión");
        }
    }
    
    public void Agregar(String Query) throws SQLException{  
        try{
            connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             connection.prepareStatement(Query).executeUpdate();
             System.out.println("Conexión exitosa!");
          }
        }
        catch ( Exception ex ) {
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }
        try{
            connection.close();
        }catch ( Exception ex ) {
            System.out.println("Error en la conexión");
        }
    }

    public ResultSet consultar(String Query) throws SQLException{  
        try{
          connection = DriverManager.getConnection(obtener_url());
          if ( connection != null ){
             ResultSet resultado = connection.prepareStatement(Query).executeQuery();
            System.out.println("Conexión exitosa!");
             return resultado;
          }
        }
        catch ( Exception ex ) {
          System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
          System.out.println("Error en la conexión");
        }
        try{
            connection.close();
        }catch ( Exception ex ) {
            System.out.println("Error en la conexión");
        }
        return null;
    }

     public void cerrar(ResultSet resultado) throws SQLException{
        resultado.close();
    }
}
