package Interface;

import java.util.List;

import Controller.Pessoa;

public interface IPessoa <T extends Pessoa>{

    public void inserirDadosBanco(T object);
    public List<Class<T>> pesquisarDadosBanco();
    public void atualizarCadastroBanco(T object);
    public void retornaPesquisaBanco();
    public void deletarDadosBanco(T object);
    public List<Class<T>> listaDadosBanco();
    } 
