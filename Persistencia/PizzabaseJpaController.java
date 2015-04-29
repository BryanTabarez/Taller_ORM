/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Itempedido;
import Modelo.Pizzabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bryanstm
 */
public class PizzabaseJpaController implements Serializable {

    public PizzabaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pizzabase pizzabase) {
        if (pizzabase.getItempedidoCollection() == null) {
            pizzabase.setItempedidoCollection(new ArrayList<Itempedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itempedido> attachedItempedidoCollection = new ArrayList<Itempedido>();
            for (Itempedido itempedidoCollectionItempedidoToAttach : pizzabase.getItempedidoCollection()) {
                itempedidoCollectionItempedidoToAttach = em.getReference(itempedidoCollectionItempedidoToAttach.getClass(), itempedidoCollectionItempedidoToAttach.getItempedidoPK());
                attachedItempedidoCollection.add(itempedidoCollectionItempedidoToAttach);
            }
            pizzabase.setItempedidoCollection(attachedItempedidoCollection);
            em.persist(pizzabase);
            for (Itempedido itempedidoCollectionItempedido : pizzabase.getItempedidoCollection()) {
                Pizzabase oldPizzabaseOfItempedidoCollectionItempedido = itempedidoCollectionItempedido.getPizzabase();
                itempedidoCollectionItempedido.setPizzabase(pizzabase);
                itempedidoCollectionItempedido = em.merge(itempedidoCollectionItempedido);
                if (oldPizzabaseOfItempedidoCollectionItempedido != null) {
                    oldPizzabaseOfItempedidoCollectionItempedido.getItempedidoCollection().remove(itempedidoCollectionItempedido);
                    oldPizzabaseOfItempedidoCollectionItempedido = em.merge(oldPizzabaseOfItempedidoCollectionItempedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pizzabase pizzabase) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase persistentPizzabase = em.find(Pizzabase.class, pizzabase.getPizzaId());
            Collection<Itempedido> itempedidoCollectionOld = persistentPizzabase.getItempedidoCollection();
            Collection<Itempedido> itempedidoCollectionNew = pizzabase.getItempedidoCollection();
            List<String> illegalOrphanMessages = null;
            for (Itempedido itempedidoCollectionOldItempedido : itempedidoCollectionOld) {
                if (!itempedidoCollectionNew.contains(itempedidoCollectionOldItempedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itempedido " + itempedidoCollectionOldItempedido + " since its pizzabase field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Itempedido> attachedItempedidoCollectionNew = new ArrayList<Itempedido>();
            for (Itempedido itempedidoCollectionNewItempedidoToAttach : itempedidoCollectionNew) {
                itempedidoCollectionNewItempedidoToAttach = em.getReference(itempedidoCollectionNewItempedidoToAttach.getClass(), itempedidoCollectionNewItempedidoToAttach.getItempedidoPK());
                attachedItempedidoCollectionNew.add(itempedidoCollectionNewItempedidoToAttach);
            }
            itempedidoCollectionNew = attachedItempedidoCollectionNew;
            pizzabase.setItempedidoCollection(itempedidoCollectionNew);
            pizzabase = em.merge(pizzabase);
            for (Itempedido itempedidoCollectionNewItempedido : itempedidoCollectionNew) {
                if (!itempedidoCollectionOld.contains(itempedidoCollectionNewItempedido)) {
                    Pizzabase oldPizzabaseOfItempedidoCollectionNewItempedido = itempedidoCollectionNewItempedido.getPizzabase();
                    itempedidoCollectionNewItempedido.setPizzabase(pizzabase);
                    itempedidoCollectionNewItempedido = em.merge(itempedidoCollectionNewItempedido);
                    if (oldPizzabaseOfItempedidoCollectionNewItempedido != null && !oldPizzabaseOfItempedidoCollectionNewItempedido.equals(pizzabase)) {
                        oldPizzabaseOfItempedidoCollectionNewItempedido.getItempedidoCollection().remove(itempedidoCollectionNewItempedido);
                        oldPizzabaseOfItempedidoCollectionNewItempedido = em.merge(oldPizzabaseOfItempedidoCollectionNewItempedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pizzabase.getPizzaId();
                if (findPizzabase(id) == null) {
                    throw new NonexistentEntityException("The pizzabase with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pizzabase pizzabase;
            try {
                pizzabase = em.getReference(Pizzabase.class, id);
                pizzabase.getPizzaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pizzabase with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itempedido> itempedidoCollectionOrphanCheck = pizzabase.getItempedidoCollection();
            for (Itempedido itempedidoCollectionOrphanCheckItempedido : itempedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pizzabase (" + pizzabase + ") cannot be destroyed since the Itempedido " + itempedidoCollectionOrphanCheckItempedido + " in its itempedidoCollection field has a non-nullable pizzabase field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pizzabase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pizzabase> findPizzabaseEntities() {
        return findPizzabaseEntities(true, -1, -1);
    }

    public List<Pizzabase> findPizzabaseEntities(int maxResults, int firstResult) {
        return findPizzabaseEntities(false, maxResults, firstResult);
    }

    private List<Pizzabase> findPizzabaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pizzabase.class));
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

    public Pizzabase findPizzabase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pizzabase.class, id);
        } finally {
            em.close();
        }
    }

    public int getPizzabaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pizzabase> rt = cq.from(Pizzabase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
