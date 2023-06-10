package View;

import java.util.Scanner;
import Controller.Colaborador;
import Controller.Morador;
import DAO.ColaboradorDAO;
import DAO.MoradorDAO;

public class TelaInicial{
    
public void menuTelaInicial(){

    var scEntrada = new Scanner(System.in);
    var scOpcao = new Scanner(System.in);
    var sair = false; 

    do{
    System.out.println("______________________________ Tela Inicial ______________________________");
    System.out.println("\n Digite um número para cada opção \n"+
    "\n 1- Cadastrar um novo morador "+
    "\n 2- Cadastrar outro colaborador "+
    "\n 3- Pesquisar cadastro de colaborador "+
    "\n 4- Pesquisar cadastro de morador "+
    "\n 5- Atualizar cadastro de colaborador "+
    "\n 6- Atualizar cadastro de morador "+
    "\n 7- Deletar cadastro de colaborador"+
    "\n 8- Deletar cadastro de morador"+
    "\n 9- Listagem de todos os colaboradores" +
    "\n 10- Listagem de todos os moradores" +
    "\n 11- Sair \n");
    int opcao = scOpcao.nextInt();

    switch(opcao){
        case 1:
        var morador =  new Morador();
        var moradorDAO = new MoradorDAO();
        moradorDAO.inserirDadosBanco(morador);
        break;

        case 2:
        var colaborador =  new Colaborador();
        var colaboradorDAO = new ColaboradorDAO();
        colaboradorDAO.inserirDadosBanco(colaborador);
        break;

        case 3:
        colaboradorDAO = new ColaboradorDAO();
        colaboradorDAO.pesquisarDadosBanco();
        colaboradorDAO.retornaPesquisaBanco();
        break;

        case 4:
        moradorDAO =  new MoradorDAO();
        moradorDAO.pesquisarDadosBanco();
        moradorDAO.retornaPesquisaBanco();
        break;

        case 5:
        colaboradorDAO = new ColaboradorDAO();
        colaborador = new Colaborador();
        colaboradorDAO.atualizarCadastroBanco(colaborador);
        break;

        case 6:
        moradorDAO = new MoradorDAO();
        morador = new Morador();
        moradorDAO.atualizarCadastroBanco(morador);
        break;

        case 7:
        colaboradorDAO = new ColaboradorDAO();
        colaborador = new Colaborador();
        colaboradorDAO.deletarDadosBanco(colaborador);
        break;

        case 8:
        moradorDAO = new MoradorDAO();
        morador = new Morador();
        moradorDAO.deletarDadosBanco(morador);
        break;

        case 9:
        colaboradorDAO = new ColaboradorDAO();
        colaboradorDAO.listaDadosBanco();
        colaboradorDAO.retornaListaBanco();
        break;

        case 10:
        moradorDAO = new MoradorDAO();
        moradorDAO.listaDadosBanco();
        moradorDAO.retornaListaBanco();
        break;

        case 11:
        sair = true;
        break;

        default: System.out.println("Digite uma opção válida!");
    }
}while(sair == false);
    scEntrada.close();            
    scOpcao.close();
}
}