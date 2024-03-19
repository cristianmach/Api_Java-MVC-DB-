package Controller;
import DataBase.Connections;
import Model.CuerpoDeAgua;
import Model.DensidadPoblacional;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    ArrayList<CuerpoDeAgua> waterClass = new ArrayList<>();
    ArrayList<DensidadPoblacional> DensidadPoblacionales = new ArrayList<>();
    Connections datasbases = new Connections();
    
    public void Add(String name, int id, String city, float irca, String typeWater, String typeClassWater) throws SQLException{
       String Query_Agregar = "INSERT INTO CuerpoDeAgua(id,name,city,typeClassWater,typeWater,irca) VALUES("+id+",'"+name+"','"+city+"','"+typeClassWater+"','"+typeWater+"',"+irca+");";       
       datasbases.Agregar(Query_Agregar);     
    }
    
    public String Consult() throws SQLException{
       String Query_Consultar = "SELECT * FROM CuerpoDeAgua;";
       ResultSet Resultado = datasbases.consultar(Query_Consultar); 
       String Consulta="";
       
       while(Resultado.next()){
           Consulta += Resultado.getString("id") + " "
                   + Resultado.getString("name") + " "
                   + Resultado.getString("city") + " "
                   + Resultado.getString("typeClassWater")+ " "
                   + Resultado.getString("typeWater") + " "
                   + Resultado.getString("irca") + " "
                   + "\n";
       }
       
       datasbases.cerrar(Resultado);
       return Consulta;
       
    }
    
    public String[] ConsultPorId(int id) throws SQLException{
       String Query_Consultar = "SELECT * FROM CuerpoDeAgua WHERE id="+id+";" ;
       ResultSet Resultado = datasbases.consultar(Query_Consultar); 
       String[] Vector = new String[6];
       
        for (int i = 0; i < Vector.length; i++) {
            Vector[i] = Resultado.getString(i+1);
        }
       
       datasbases.cerrar(Resultado);
       return Vector;
       
    }
    
    public void Edit(String name, int id, String city, float irca, String typeWater, String typeClassWater) throws SQLException{
       String Query_Editar = "UPDATE CuerpoDeAgua "
               + "SET " + " name='"+name+"', city='"+city+"',typeClassWater='"+typeClassWater+"',typeWater='"+typeWater+"',irca="+irca
               + " WHERE  id=" + id + ";";
       datasbases.Agregar(Query_Editar);     
    }
    
    public void Delete(int id) throws SQLException{
       String Query_Eliminar = "DELETE FROM CuerpoDeAgua WHERE id="+id+";" ;
       datasbases.Agregar(Query_Eliminar);  
    }    
    
    public void LlenarArray() throws SQLException{
       waterClass.clear();
       String Query_Consultar = "SELECT * FROM CuerpoDeAgua;";
       ResultSet Resultado = datasbases.consultar(Query_Consultar); 
       while(Resultado.next()){
        CuerpoDeAgua cuerpoagua = new CuerpoDeAgua(Resultado.getString("name"),Integer.parseInt(Resultado.getString("id")),Resultado.getString("city"),Float.parseFloat(Resultado.getString("irca")),Resultado.getString("typeWater"),Resultado.getString("typeClassWater"));
        waterClass.add(cuerpoagua);
       }
    }
    
    public String AllLevels(){
        String riesgo="";
        
        for (int i = 0; i < waterClass.size(); i++) {
            riesgo += waterClass.get(i).level()+ "\n";
        }
        
      return riesgo;
    
    }
    
    public String MimMedio(){
        String menores;
        int contador = 0;
        for (int i = 0; i < waterClass.size(); i++) {
            if(waterClass.get(i).getIrca() >= 0 && waterClass.get(i).getIrca() <= 35){
                contador++;
            }
        
        }
        menores = contador + "\n";
        return menores;
    }
    
    public String Medios(){
        String medios ="NA";
        for (int i = 0; i < waterClass.size(); i++) {
            if("MEDIO".equals(waterClass.get(i).level())){
                medios += waterClass.get(i).getName()+ "\n";
            }
        }
        
        if(medios.length() > 2){
            medios = medios.replace("NA","");
        }else{
            medios += "\n";
        }
        
        return medios;
    }
    
   public String Mim(){ 
        String datos_menor="";
        float menor = waterClass.get(0).getIrca();
        String nomb = waterClass.get(0).getName();
        int cod = waterClass.get(0).getId();
        for (int i = 0; i < waterClass.size(); i++) {
             if (waterClass.get(i).getIrca() < menor){
                 menor = waterClass.get(i).getIrca();
                 nomb = waterClass.get(i).getName();
                 cod = waterClass.get(i).getId();
             }
        }
       datos_menor = nomb + " " + cod;  
       return datos_menor;
   }
    
     public void AñadirDensidad(int nPeople, int id, String city){
          DensidadPoblacional DensidadPoblacion = new DensidadPoblacional(nPeople,
               id,
               city);
       DensidadPoblacionales.add(DensidadPoblacion);
   }
   
   public String ProcesarPoblaciones(){
       String affeciones="";
       for (int i = 0; i < DensidadPoblacionales.size(); i++){
           
           affeciones += "ID: "+ DensidadPoblacionales.get(i).getId()+" "
                   +"Municipio: "+ DensidadPoblacionales.get(i).getCity()+": "
                   +"Afectación:" + DensidadPoblacionales.get(i).afeccion()+"\n";
       }
   
       return affeciones;
       
   }
    
}
