package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.Morador;
import Controller.Pessoa;
import Interface.IPessoa;
import Repository.ConexaoBancoDados;

public class MoradorDAO implements IPessoa{

    private Scanner scNome = new Scanner(System.in);
    private Scanner scCpf = new Scanner(System.in);
    private Scanner scNumeroApartamento = new Scanner(System.in);
    private Scanner scBloco = new Scanner(System.in);
    
    public void inserirDadosBanco(Pessoa pessoa){
        //verificar cpf, se sao 11 digitos e somente numeros
        var morador = (Morador) pessoa;
        String query = "INSERT INTO morador (nome, cpf, numero_apartamento, bloco) VALUES (?,?,?,?)";

        Connection con = null;

        PreparedStatement preparedStatement = null;

        System.out.println("Digite o nome: \n");
        morador.setNome(scNome.nextLine());
        System.out.println("\nDigite o CPF: \n");
        morador.setCpf(scCpf.nextLine());
        System.out.println("\nDigite o número do apartamento: \n");
        morador.setNumeroApartamento(scNumeroApartamento.nextLine());
        System.out.println("\nDigite o número do bloco: \n");
        morador.setBloco(scBloco.nextLine());

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, morador.getNome());
            preparedStatement.setString(2, morador.getCpf());
            preparedStatement.setString(3, morador.getNumeroApartamento());
            preparedStatement.setString(4, morador.getBloco());

            preparedStatement.execute();

            System.out.println("Morador cadastrado com sucesso!");
        }
        catch(Exception e){
           System.out.println("Não foi possível cadastrar um novo morador!");
        }finally {
            try{
            if(preparedStatement!=null)
            {
                preparedStatement.close();
            }
            if(con!=null)
            {   
                con.close();
            }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<Morador> pesquisarDadosBanco(){
        var query = "SELECT * FROM morador WHERE cpf = ?";

        List<Morador> moradores = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        var morador = new Morador();
        morador.setCpf(scCpf.nextLine());

        ResultSet resultSet = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, morador.getCpf());

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
            
                morador.setId(resultSet.getInt(1));
                morador.setNome(resultSet.getString(2));
                morador.setCpf(resultSet.getString(3));
                morador.setNumeroApartamento(resultSet.getString(4));
                morador.setBloco(resultSet.getString(5));

                moradores.add(morador);
            }

        }
        catch(Exception e){
            System.out.println("Morador não encontrado!");
         }finally {
             try{
            if(preparedStatement!=null)
             {
                 preparedStatement.close();
             }
             if(resultSet!=null)
             {
                 resultSet.close();
             }
             if(con!=null)
             {   
                 con.close();
             }
             }
             catch(Exception e){
                 e.printStackTrace();
             }
         }
         return moradores;
    }

    public void retornaPesquisaBanco(){
        
        var moradorDAO = new MoradorDAO();

        System.out.println("Digite o CPF do morador: \n");

        for(Morador morador2 : moradorDAO.pesquisarDadosBanco()){
            System.out.println("ID: "+morador2.getId());
            System.out.println("Nome: "+morador2.getNome());
            System.out.println("CPF: "+morador2.getCpf());
            System.out.println("Número apartamento: "+morador2.getNumeroApartamento());
            System.out.println("Número bloco: "+morador2.getBloco());

        }
    }

    public void atualizarCadastroBanco(Pessoa pessoa) {
        Morador morador = (Morador) pessoa;
        String query = "UPDATE morador SET nome = ?, numero_apartamento = ?, bloco = ? WHERE cpf = ?";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = (PreparedStatement) con.prepareStatement(query);

            morador =  new Morador();
            System.out.println("Digite o CPF do morador que deseja atualizar o cadastro: \n");
            morador.setCpf(scCpf.nextLine());
            System.out.println("Digite o nome do morador: \n");
            morador.setNome(scNome.nextLine());
            System.out.println("Digite o número do apartamento do morador: \n");
            morador.setNumeroApartamento(scNumeroApartamento.nextLine());
            System.out.println("Digite o bloco do morador: \n");
            morador.setBloco(scBloco.nextLine());

            preparedStatement.setString(1, morador.getNome());
            preparedStatement.setString(2, morador.getNumeroApartamento());
            preparedStatement.setString(3, morador.getBloco());
            preparedStatement.setString(4, morador.getCpf());

            preparedStatement.execute();
            
            System.out.println("Cadastro alterado com sucesso!");

        }catch(Exception e){
            System.out.println("Não foi possível atualizar o cadastro! \n");
        }finally{
            try{
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deletarDadosBanco(Pessoa pessoa) {

        Morador morador = (Morador) pessoa;

        System.out.println("Digite o CPF do morador: ");
        var query = "DELETE FROM morador WHERE cpf = ?";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        morador.setCpf(scCpf.nextLine());

        try{
        con = ConexaoBancoDados.conexaoMySQL();
        preparedStatement = (PreparedStatement) con.prepareStatement(query);
        preparedStatement.setString(1, morador.getCpf());
        preparedStatement.execute();

        System.out.println("Morador deletado com sucesso!");
        }
        catch(Exception e){
            System.out.println("Erro ao deletar morador!");
         }finally {
             try{
            if(preparedStatement!=null)
             {
                 preparedStatement.close();
             }
             if(con!=null)
             {   
                 con.close();
             }
             }
             catch(Exception e){
                 e.printStackTrace();
             }
         }
    }

    @Override
    public List<Morador> listaDadosBanco() {

        var query = "SELECT * FROM morador";

        List<Morador> moradores = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                
                Morador morador = new Morador();
                morador.setId(resultSet.getInt("id_morador"));
                morador.setNome(resultSet.getString("nome"));
                morador.setCpf(resultSet.getString("cpf"));
                morador.setNumeroApartamento(resultSet.getString("numero_apartamento"));
                morador.setBloco(resultSet.getString("bloco"));

                moradores.add(morador);
            }
        }
        catch(Exception e){
            System.out.println("Falha ao carregar a lista!");
         }finally {
             try{
            if(preparedStatement!=null)
             {
                 preparedStatement.close();
             }
             if(resultSet!=null)
             {
                 resultSet.close();
             }
             if(con!=null)
             {   
                 con.close();
             }
             }
             catch(Exception e){
                 e.printStackTrace();
             }
         }
         return moradores;
    }

    public void retornaListaBanco(){
        
        var moradorDAO = new MoradorDAO();

        for(Morador morador2 : moradorDAO.listaDadosBanco()){
            System.out.println("ID: "+morador2.getId());
            System.out.println("Nome: "+morador2.getNome());
            System.out.println("CPF: "+morador2.getCpf());
            System.out.println("Número apartamento: "+morador2.getNumeroApartamento());
            System.out.println("Número bloco: "+morador2.getBloco());

        }
    }
}

