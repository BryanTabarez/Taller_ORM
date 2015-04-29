/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.Ingredienteadicional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Itempedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bryanstm
 */
public class IngredienteadicionalJpaController implements Serializable {

    public IngredienteadicionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingredienteadicional ingredienteadicional) {
        if (ingredienteadicional.getItempedidoCollection() == null) {
            ingredienteadicional.setItempedidoCollection(new ArrayList<Itempedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itempedido> attachedItempedidoCollection = new ArrayList<Itempedido>();
            for (Itempedido itempedidoCollectionItempedidoToAttach : ingredienteadicional.getItempedidoCollection()) {
                itempedidoCollectionItempedidoToAttach = em.getReference(itempedidoCollectionItempedidoToAttach.getClass(), itempedidoCollectionItempedidoToAttach.getItempedidoPK());
                attachedItempedidoCollection.add(itempedidoCollectionItempedidoToAttach);
            }
            ingredienteadicional.setItempedidoCollection(attachedItempedidoCollection);
            em.persist(ingredienteadicional);
            for (Itempedido itempedidoCollectionItempedido : ingredienteadicional.getItempedidoCollection()) {
                Ingredienteadicional oldIngredienteadicionalOfItempedidoCollectionItempedido = itempedidoCollectionItempedido.getIngredienteadicional();
                itempedidoCollectionItempedido.setIngredienteadicional(ingredienteadicional);
                itempedidoCollectionItempedido = em.merge(itempedidoCollectionItempedido);
                if (oldIngredienteadicionalOfItempedidoCollectionItempedido != null) {
                    oldIngredienteadicionalOfItempedidoCollectionItempedido.getItempedidoCollection().remove(itempedidoCollectionItempedido);
                    oldIngredienteadicionalOfItempedidoCollectionItempedido = em.merge(oldIngredienteadicionalOfItempedidoCollectionItempedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingredienteadicional ingredienteadicional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredienteadicional persistentIngredienteadicional = em.find(Ingredienteadicional.class, ingredienteadicional.getIngredienteId());
            Collection<Itempedido> itempedidoCollectionOld = persistentIngredienteadicional.getItempedidoCollection();
            Collection<Itempedido> itempedidoCollectionNew = ingredienteadicional.getItempedidoCollection();
            List<String> illegalOrphanMessages = null;
            for (Itempedido itempedidoCollectionOldItempedido : itempedidoCollectionOld) {
                if (!itempedidoCollectionNew.contains(itempedidoCollectionOldItempedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itempedido " + itempedidoCollectionOldItempedido + " since its ingredienteadicional field is not nullable.");
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
            ingredienteadicional.setItempedidoCollection(itempedidoCollectionNew);
            ingredienteadicional = em.merge(ingredienteadicional);
            for (Itempedido itempedidoCollectionNewItempedido : itempedidoCollectionNew) {
                if (!itempedidoCollectionOld.contains(itempedidoCollectionNewItempedido)) {
                    Ingredienteadicional oldIngredienteadicionalOfItempedidoCollectionNewItempedido = itempedidoCollectionNewItempedido.getIngredienteadicional();
                    itempedidoCollectionNewItempedido.setIngredienteadicional(ingredienteadicional);
                    itempedidoCollectionNewItempedido = em.merge(itempedidoCollectionNewItempedido);
                    if (oldIngredienteadicionalOfItempedidoCollectionNewItempedido != null && !oldIngredienteadicionalOfItempedidoCollectionNewItempedido.equals(ingredienteadicional)) {
                        oldIngredienteadicionalOfItempedidoCollectionNewItempedido.getItempedidoCollection().remove(itempedidoCollectionNewItempedido);
                        oldIngredienteadicionalOfItempedidoCollectionNewItempedido = em.merge(oldIngredienteadicionalOfItempedidoCollectionNewItempedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingredienteadicional.getIngredienteId();
                if (findIngredienteadicional(id) == null) {
                    throw new NonexistentEntityException("The ingredienteadicional with id " + id + " no longer exists.");
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
            Ingredienteadicional ingredienteadicional;
            try {
                ingredienteadicional = em.getReference(Ingredienteadicional.class, id);
                ingredienteadicional.getIngredienteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingredienteadicional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itempedido> itempedidoCollectionOrphanCheck = ingredienteadicional.getItempedidoCollection();
            for (Itempedido itempedidoCollectionOrphanCheckItempedido : itempedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ingredienteadicional (" + ingredienteadicional + ") cannot be destroyed since the Itempedido " + itempedidoCollectionOrphanCheckItempedido + " in its itempedidoCollection field has a non-nullable ingredienteadicional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ingredienteadicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingredienteadicional> findIngredienteadicionalEntities() {
        return findIngredienteadicionalEntities(true, -1, -1);
    }

    public List<Ingredienteadicional> findIngredienteadicionalEntities(int maxResults, int firstResult) {
        return findIngredienteadicionalEntities(false, maxResults, firstResult);
    }

    private List<Ingredienteadicional> findIngredienteadicionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingredienteadicional.class));
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

    public Ingredienteadicional findIngredienteadicional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingredienteadicional.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredienteadicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingredienteadicional> rt = cq.from(Ingredienteadicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
