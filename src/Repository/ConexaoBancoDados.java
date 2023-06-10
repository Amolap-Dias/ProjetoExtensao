package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {

    //Dps mudar os nomes, forma de declarar e frases de exceção
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String serverName = "localhost"; 
    private static String mydatabase = "sistemaportaria";        //nome do seu banco de dados
    private static String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    private static String username = "root";        //nome de um usuário de seu BD
    private static String password = ""; 

    public static Connection conexaoMySQL() {

        
        try{

            Class.forName(driverName);

            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;

        } catch(ClassNotFoundException e) {  

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }
            
    }   

}
