package Model;

public class CuerpoDeAgua extends ObjetoGeografico{

    private String name;
    private float irca;
    private String typeWater;
    private String typeClassWater; 

    public CuerpoDeAgua(String name, int id, String city, float irca, String typeWater, String typeClassWater) {
        super(id, city);
        this.name = name;
        this.irca = irca;
        this.typeWater = typeWater;
        this.typeClassWater = typeClassWater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getIrca() {
        return irca;
    }

    public void setIrca(float irca) {
        this.irca = irca;
    }

    public String getTypeWater() {
        return typeWater;
    }

    public void setTypeWater(String typeWater) {
        this.typeWater = typeWater;
    }

    public String getTypeClassWater() {
        return typeClassWater;
    }

    public void setTypeClassWater(String typeClassWater) {
        this.typeClassWater = typeClassWater;
    }
    
    
 
    public String level(){
        if (getIrca() >= 0 && getIrca() <=5){
            return "SIN RIESGO";
        }else if(getIrca() > 5 && getIrca() <=14){
            return "BAJO";
        }else if(getIrca() > 14 && getIrca() <=35){
            return "MEDIO";
        }else if(getIrca() > 35 && getIrca() <=80){
            return "ALTO";
        }else if(getIrca() > 80 && getIrca() <=100){
           return "INVIABLE SANITARIAMENTE";
        }    
        return "No clasifica";
    }
    
}
