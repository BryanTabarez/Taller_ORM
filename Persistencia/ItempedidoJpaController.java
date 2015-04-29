/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Pizzabase;
import Modelo.Ingredienteadicional;
import Modelo.Factura;
import Modelo.Itempedido;
import Modelo.ItempedidoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bryanstm
 */
public class ItempedidoJpaController implements Serializable {

    public ItempedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itempedido itempedido) throws PreexistingEntityException, Exception {
        if (itempedido.getItempedidoPK() == null) {
            itempedido.setItempedidoPK(new ItempedidoPK());
        }
        itempedido.getItempedidoPK().setPizzaId(itempedido.getPizzabase().getPizzaId());
        itempedido.getItempedidoPK().setIngredienteId(itempedido.getIngredienteadicional().getIngredienteId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase pizzabase = itempedido.getPizzabase();
            if (pizzabase != null) {
                pizzabase = em.getReference(pizzabase.getClass(), pizzabase.getPizzaId());
                itempedido.setPizzabase(pizzabase);
            }
            Ingredienteadicional ingredienteadicional = itempedido.getIngredienteadicional();
            if (ingredienteadicional != null) {
                ingredienteadicional = em.getReference(ingredienteadicional.getClass(), ingredienteadicional.getIngredienteId());
                itempedido.setIngredienteadicional(ingredienteadicional);
            }
            Factura facturaId = itempedido.getFacturaId();
            if (facturaId != null) {
                facturaId = em.getReference(facturaId.getClass(), facturaId.getFacturaId());
                itempedido.setFacturaId(facturaId);
            }
            em.persist(itempedido);
            if (pizzabase != null) {
                pizzabase.getItempedidoCollection().add(itempedido);
                pizzabase = em.merge(pizzabase);
            }
            if (ingredienteadicional != null) {
                ingredienteadicional.getItempedidoCollection().add(itempedido);
                ingredienteadicional = em.merge(ingredienteadicional);
            }
            if (facturaId != null) {
                facturaId.getItempedidoCollection().add(itempedido);
                facturaId = em.merge(facturaId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItempedido(itempedido.getItempedidoPK()) != null) {
                throw new PreexistingEntityException("Itempedido " + itempedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itempedido itempedido) throws NonexistentEntityException, Exception {
        itempedido.getItempedidoPK().setPizzaId(itempedido.getPizzabase().getPizzaId());
        itempedido.getItempedidoPK().setIngredienteId(itempedido.getIngredienteadicional().getIngredienteId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedido persistentItempedido = em.find(Itempedido.class, itempedido.getItempedidoPK());
            Pizzabase pizzabaseOld = persistentItempedido.getPizzabase();
            Pizzabase pizzabaseNew = itempedido.getPizzabase();
            Ingredienteadicional ingredienteadicionalOld = persistentItempedido.getIngredienteadicional();
            Ingredienteadicional ingredienteadicionalNew = itempedido.getIngredienteadicional();
            Factura facturaIdOld = persistentItempedido.getFacturaId();
            Factura facturaIdNew = itempedido.getFacturaId();
            if (pizzabaseNew != null) {
                pizzabaseNew = em.getReference(pizzabaseNew.getClass(), pizzabaseNew.getPizzaId());
                itempedido.setPizzabase(pizzabaseNew);
            }
            if (ingredienteadicionalNew != null) {
                ingredienteadicionalNew = em.getReference(ingredienteadicionalNew.getClass(), ingredienteadicionalNew.getIngredienteId());
                itempedido.setIngredienteadicional(ingredienteadicionalNew);
            }
            if (facturaIdNew != null) {
                facturaIdNew = em.getReference(facturaIdNew.getClass(), facturaIdNew.getFacturaId());
                itempedido.setFacturaId(facturaIdNew);
            }
            itempedido = em.merge(itempedido);
            if (pizzabaseOld != null && !pizzabaseOld.equals(pizzabaseNew)) {
                pizzabaseOld.getItempedidoCollection().remove(itempedido);
                pizzabaseOld = em.merge(pizzabaseOld);
            }
            if (pizzabaseNew != null && !pizzabaseNew.equals(pizzabaseOld)) {
                pizzabaseNew.getItempedidoCollection().add(itempedido);
                pizzabaseNew = em.merge(pizzabaseNew);
            }
            if (ingredienteadicionalOld != null && !ingredienteadicionalOld.equals(ingredienteadicionalNew)) {
                ingredienteadicionalOld.getItempedidoCollection().remove(itempedido);
                ingredienteadicionalOld = em.merge(ingredienteadicionalOld);
            }
            if (ingredienteadicionalNew != null && !ingredienteadicionalNew.equals(ingredienteadicionalOld)) {
                ingredienteadicionalNew.getItempedidoCollection().add(itempedido);
                ingredienteadicionalNew = em.merge(ingredienteadicionalNew);
            }
            if (facturaIdOld != null && !facturaIdOld.equals(facturaIdNew)) {
                facturaIdOld.getItempedidoCollection().remove(itempedido);
                facturaIdOld = em.merge(facturaIdOld);
            }
            if (facturaIdNew != null && !facturaIdNew.equals(facturaIdOld)) {
                facturaIdNew.getItempedidoCollection().add(itempedido);
                facturaIdNew = em.merge(facturaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ItempedidoPK id = itempedido.getItempedidoPK();
                if (findItempedido(id) == null) {
                    throw new NonexistentEntityException("The itempedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ItempedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itempedido itempedido;
            try {
                itempedido = em.getReference(Itempedido.class, id);
                itempedido.getItempedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itempedido with id " + id + " no longer exists.", enfe);
            }
            Pizzabase pizzabase = itempedido.getPizzabase();
            if (pizzabase != null) {
                pizzabase.getItempedidoCollection().remove(itempedido);
                pizzabase = em.merge(pizzabase);
            }
            Ingredienteadicional ingredienteadicional = itempedido.getIngredienteadicional();
            if (ingredienteadicional != null) {
                ingredienteadicional.getItempedidoCollection().remove(itempedido);
                ingredienteadicional = em.merge(ingredienteadicional);
            }
            Factura facturaId = itempedido.getFacturaId();
            if (facturaId != null) {
                facturaId.getItempedidoCollection().remove(itempedido);
                facturaId = em.merge(facturaId);
            }
            em.remove(itempedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itempedido> findItempedidoEntities() {
        return findItempedidoEntities(true, -1, -1);
    }

    public List<Itempedido> findItempedidoEntities(int maxResults, int firstResult) {
        return findItempedidoEntities(false, maxResults, firstResult);
    }

    private List<Itempedido> findItempedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itempedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Itempedido findItempedido(ItempedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itempedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getItempedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itempedido> rt = cq.from(Itempedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
