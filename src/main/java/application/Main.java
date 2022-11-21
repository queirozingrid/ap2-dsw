package application;

import dao.ProdutoDAO;
import model.Produto;

public class Main {
	public static void main(String[] args) {
		Produto produto = new Produto();
		produto.setDescricao("Batata");
		ProdutoDAO.salvar(produto);
	}
}
