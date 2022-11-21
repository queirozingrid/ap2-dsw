package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ProdutoDAO;
import model.Produto;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private ArrayList<Produto> produtos;

	public void salvar() {
		ProdutoDAO.salvar(produto);
	}

	public void listar(){
		produtos = new ArrayList<>(ProdutoDAO.listar());
	}
	
	public void remover() {
		System.out.println("desc " + produto.getDescricao());
		JSFUtil.mensagemSucesso("Produto removido com sucesso");
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
