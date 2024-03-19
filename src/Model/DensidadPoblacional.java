package Model;
public class DensidadPoblacional extends ObjetoGeografico{
    private int nPeople;

    public DensidadPoblacional(int nPeople, int id, String city) {
        super(id, city);
        this.nPeople = nPeople;
    }

    public int getnPeople() {
        return nPeople;
    }

    public void setnPeople(int nPeople) {
        this.nPeople = nPeople;
    }
    
    public int afeccion(){
        if(nPeople < 10000){
            return 0;
        }else if(nPeople >= 10000 && nPeople<= 50000){
            return 1;
        }else{
            return 2;
        }
    }
}

