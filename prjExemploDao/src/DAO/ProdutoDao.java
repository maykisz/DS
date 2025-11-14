package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Model.Produto;

public class ProdutoDao {

    private Connection connection;

    public ProdutoDao() {
        // Inicializa a conexão com o banco de dados
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adcionarCadastro(Cadastro cadastro ) {
        // Lógica para adicionar um produto ao banco de dados

        try {
            // Cria o comando SQL
            String sql = "INSERT INTO tbcadastro (nome, idcpf, telefone, dataNascimento) VALUES (?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = connection.prepareStatement(sql);

            // Define os valores dos parâmetros
            stmt.setString(1, cadastro.getnome());
            stmt.setString(2, cadastro.getidcpf());
            stmt.setString(3, cadastro.gettelefone());
            stmt.setString(4, cadastro.getdataNascimento());


            // Executa o comando SQL
            stmt.execute();
            stmt.close();
        } 
        // Tratamento de exceção em caso de erro na operação
        catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
        finally {
            // Fecha a conexão com o banco de dados
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }

    }
}
