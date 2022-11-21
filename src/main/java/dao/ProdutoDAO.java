package dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Produto;
import util.JPAUtil;

public class ProdutoDAO {
	private static EntityManager em = JPAUtil.criarEntityManager();
	
	public static Produto salvar(Produto produto) {
		em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
		return produto;
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
	
	public void remover(Produto produto) {
		em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Produto produtoEncontrado = em.find(Produto.class, produto.getId());
		em.remove(produtoEncontrado);
		em.getTransaction().commit();
		ProdutoDAO.listar();
	}
	
}

