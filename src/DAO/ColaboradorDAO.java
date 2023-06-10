package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import Controller.Colaborador;
import Controller.Pessoa;
import Interface.IPessoa;
import Repository.ConexaoBancoDados;

public class ColaboradorDAO implements IPessoa{

    private Scanner scNome = new Scanner(System.in);
    private Scanner scCpf = new Scanner(System.in);
    private Scanner scSenha = new Scanner(System.in);
    private Scanner scUsuario = new Scanner(System.in);

    public void inserirDadosBanco(Pessoa pessoa) {
        var colaborador = (Colaborador) pessoa;
        var query = "INSERT INTO funcionario (nome, senha, cpf, usuario) VALUES (?,?,?,?)";

        Connection con = null;

        PreparedStatement preparedStatement = null;

        System.out.println("Digite o nome: \n");
        colaborador.setNome(scNome.nextLine());
        System.out.println("\nDigite o CPF: \n");
        colaborador.setCpf(scCpf.nextLine());
        System.out.println("\nDigite a senha: \n");
        colaborador.setSenha(scSenha.nextLine());
        System.out.println("\nDigite o usuário: \n");
        colaborador.setUsuario(scUsuario.nextLine());

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, colaborador.getNome());
            preparedStatement.setString(2, colaborador.getSenha());
            preparedStatement.setString(3, colaborador.getCpf());
            preparedStatement.setString(4, colaborador.getUsuario());

            preparedStatement.execute();

            System.out.println("Colaborador cadastrado com sucesso!");
        }
        catch(Exception e){
           System.out.println("Não foi possível cadastrar um novo colaborador!");
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

    public List<Colaborador> pesquisarDadosBanco(){

        var query = "SELECT * FROM funcionario WHERE cpf = ?";

        List<Colaborador> colaboradores = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        var colaborador =  new Colaborador();
        colaborador.setCpf(scCpf.nextLine());

        ResultSet resultSet = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, colaborador.getCpf());

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
            
                colaborador.setId(resultSet.getInt(1));
                colaborador.setNome(resultSet.getString(2));
                colaborador.setCpf(resultSet.getString(3));
                colaborador.setSenha(resultSet.getString(4));
                colaborador.setSenha(resultSet.getString(5));

                colaboradores.add(colaborador);
            }

        }
        catch(Exception e){
            System.out.println("Colaborador não encontrado!");
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
         return colaboradores;
    }

    public void retornaPesquisaBanco(){
        
        var colaboradorDAO = new ColaboradorDAO();

        System.out.println("Digite o CPF do colaborador: \n");
        
        for(Colaborador colaborador2 : colaboradorDAO.pesquisarDadosBanco()){
            System.out.println("ID: "+colaborador2.getId());
            System.out.println("Nome: "+colaborador2.getNome());
            System.out.println("CPF: "+colaborador2.getCpf());
            System.out.println("Senha: "+colaborador2.getSenha());
            System.out.println("Usuário: "+colaborador2.getUsuario());

        }
    }

    @Override
    public void atualizarCadastroBanco(Pessoa pessoa) {
        var colaborador = (Colaborador) pessoa;
        var query = "UPDATE funcionario SET nome = ?, senha = ?, usuario = ? WHERE cpf = ?";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = (PreparedStatement) con.prepareStatement(query);

            colaborador =  new Colaborador();
            System.out.println("Digite o CPF do colaborador que deseja atualizar o cadastro: \n");
            colaborador.setCpf(scCpf.nextLine());
            System.out.println("Digite o nome do colaborador: \n");
            colaborador.setNome(scNome.nextLine());
            System.out.println("Digite a senha do colaborador: \n");
            colaborador.setSenha(scSenha.nextLine());
            System.out.println("Digite o usuário do colaborador: \n");
            colaborador.setUsuario(scUsuario.nextLine());

            preparedStatement.setString(1, colaborador.getNome());
            preparedStatement.setString(2, colaborador.getSenha());
            preparedStatement.setString(3, colaborador.getUsuario());
            preparedStatement.setString(4, colaborador.getCpf());

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
        
        Colaborador colaborador = (Colaborador) pessoa;

        System.out.println("Digite o CPF do colaborador: ");
        var query = "DELETE FROM funcionario WHERE cpf = ?";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        colaborador.setCpf(scCpf.nextLine());

        try{
        con = ConexaoBancoDados.conexaoMySQL();
        preparedStatement = (PreparedStatement) con.prepareStatement(query);
        preparedStatement.setString(1, colaborador.getCpf());
        preparedStatement.execute();
        System.out.println("Colaborador deletado com sucesso!");
        }
        catch(Exception e){
            System.out.println("Erro ao deletar colaborador!");
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
    public List<Colaborador> listaDadosBanco() {
        
        var query = "SELECT * FROM funcionario";

        List<Colaborador> colaboradores = new ArrayList<>();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        try{
            con = ConexaoBancoDados.conexaoMySQL();
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                
                Colaborador colaborador = new Colaborador();
                colaborador.setId(resultSet.getInt("id_funcionario"));
                colaborador.setNome(resultSet.getString("nome"));
                colaborador.setCpf(resultSet.getString("cpf"));
                colaborador.setUsuario(resultSet.getString("usuario"));

                colaboradores.add(colaborador);
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
         return colaboradores;
    }

    public void retornaListaBanco(){
        
        var colaboradorDAO = new ColaboradorDAO();

        for(Colaborador colaborador2 : colaboradorDAO.listaDadosBanco()){
            System.out.println("ID: "+colaborador2.getId());
            System.out.println("Nome: "+colaborador2.getNome());
            System.out.println("CPF: "+colaborador2.getCpf());
            System.out.println("Usuário: "+colaborador2.getUsuario());
        }
    }
}
