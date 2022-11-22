package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Produto;
import util.JPAUtil;
import util.JSFUtil;

public class ProdutoDAO {
	private static EntityManager em = JPAUtil.criarEntityManager();
	
	public static Produto salvar(Produto produto) {
		em = JPAUtil.criarEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
			JSFUtil.mensagemSucesso("Produto cadastrado com sucesso!");
			return produto;
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.mensagemError("Ocorreu um erro ao tentar persistir o objeto no banco de dados");
			return null;
		} finally {
			em.close();
		}
	}
	public static List<Produto> listar(){
		String jpql = "select p from produto p";
		List<Produto> consulta;
		
		try {
			em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			consulta = em.createQuery(jpql, Produto.class).getResultList();
			
			for (Produto produto : consulta) {
				System.out.println(produto.getDescricao());
			}

			return consulta;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Produto acharPorId(Integer id) {
		em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, id);
		System.out.println(produto.getDescricao());
		return(produto);
	}
	
	public static Produto encontrarMaiorId() {
		String jpql = "select p from produto p order by p.id desc";
		
		try {
			em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			List<Produto> produtos = em.createQuery(jpql, Produto.class).getResultList();
			
			if(!produtos.isEmpty()) {
				Produto produtoMaiorId = produtos.get(0);
				return(produtoMaiorId);
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		
	}
	
	public static void remover(Produto produto) {
		
		try {
			em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			Produto produtoEncontrado = em.find(Produto.class, produto.getId());
			em.remove(produtoEncontrado);
			em.getTransaction().commit();
			JSFUtil.mensagemSucesso("Produto removido com sucesso");
			ProdutoDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.mensagemError("Falha ao tentar remover o produto");
		} finally {
			em.close();
		}
	}
	
}

