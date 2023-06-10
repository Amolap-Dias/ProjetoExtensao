package Controller;

public class Morador extends Pessoa{
    
    private String numeroApartamento;
    private String bloco;

    public String getNumeroApartamento(){
        return numeroApartamento;
    }
     public void setNumeroApartamento(String numeroApartamento){
        this.numeroApartamento = numeroApartamento;
    }
     public String getBloco(){
        return bloco;
    }
     public void setBloco(String bloco){
        this.bloco = bloco;
     }
}
