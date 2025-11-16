package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.InformacoesCadastro;

public class InformacoesDao {

    private Connection connection;

    public InformacoesDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adcionarCadastro(InformacoesCadastro info) {

        String sql = "INSERT INTO tbcadastro (nome, idcpf, telefone, dataNascimento) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, info.getNome());
            stmt.setString(2, info.getIdcpf());
            stmt.setString(3, info.getTelefone());
            stmt.setDate(4, info.getDataNascimento());

            stmt.execute();
            stmt.close();

            System.out.println("Cadastro salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cadastro: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
