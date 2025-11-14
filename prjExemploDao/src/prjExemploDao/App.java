package prjExemploDao;

import Model.Produto;
import DAO.ProdutoDao;

public class App {

	public static void main(String[] args) {
		// Cria um novo produto
		Produto produto = new Produto();
		produto.setProduto("Caneta");
		produto.setValorProduto(2.50);

		// Adiciona o produto ao banco de dados
		ProdutoDao produtoDao = new ProdutoDao();
		// Chama o método para adicionar o produto
		produtoDao.adcionarProduto(produto);

	}

}
