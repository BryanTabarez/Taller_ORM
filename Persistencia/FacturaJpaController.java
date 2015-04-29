/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.Factura;
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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getItempedidoCollection() == null) {
            factura.setItempedidoCollection(new ArrayList<Itempedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itempedido> attachedItempedidoCollection = new ArrayList<Itempedido>();
            for (Itempedido itempedidoCollectionItempedidoToAttach : factura.getItempedidoCollection()) {
                itempedidoCollectionItempedidoToAttach = em.getReference(itempedidoCollectionItempedidoToAttach.getClass(), itempedidoCollectionItempedidoToAttach.getItempedidoPK());
                attachedItempedidoCollection.add(itempedidoCollectionItempedidoToAttach);
            }
            factura.setItempedidoCollection(attachedItempedidoCollection);
            em.persist(factura);
            for (Itempedido itempedidoCollectionItempedido : factura.getItempedidoCollection()) {
                Factura oldFacturaIdOfItempedidoCollectionItempedido = itempedidoCollectionItempedido.getFacturaId();
                itempedidoCollectionItempedido.setFacturaId(factura);
                itempedidoCollectionItempedido = em.merge(itempedidoCollectionItempedido);
                if (oldFacturaIdOfItempedidoCollectionItempedido != null) {
                    oldFacturaIdOfItempedidoCollectionItempedido.getItempedidoCollection().remove(itempedidoCollectionItempedido);
                    oldFacturaIdOfItempedidoCollectionItempedido = em.merge(oldFacturaIdOfItempedidoCollectionItempedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getFacturaId());
            Collection<Itempedido> itempedidoCollectionOld = persistentFactura.getItempedidoCollection();
            Collection<Itempedido> itempedidoCollectionNew = factura.getItempedidoCollection();
            List<String> illegalOrphanMessages = null;
            for (Itempedido itempedidoCollectionOldItempedido : itempedidoCollectionOld) {
                if (!itempedidoCollectionNew.contains(itempedidoCollectionOldItempedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itempedido " + itempedidoCollectionOldItempedido + " since its facturaId field is not nullable.");
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
            factura.setItempedidoCollection(itempedidoCollectionNew);
            factura = em.merge(factura);
            for (Itempedido itempedidoCollectionNewItempedido : itempedidoCollectionNew) {
                if (!itempedidoCollectionOld.contains(itempedidoCollectionNewItempedido)) {
                    Factura oldFacturaIdOfItempedidoCollectionNewItempedido = itempedidoCollectionNewItempedido.getFacturaId();
                    itempedidoCollectionNewItempedido.setFacturaId(factura);
                    itempedidoCollectionNewItempedido = em.merge(itempedidoCollectionNewItempedido);
                    if (oldFacturaIdOfItempedidoCollectionNewItempedido != null && !oldFacturaIdOfItempedidoCollectionNewItempedido.equals(factura)) {
                        oldFacturaIdOfItempedidoCollectionNewItempedido.getItempedidoCollection().remove(itempedidoCollectionNewItempedido);
                        oldFacturaIdOfItempedidoCollectionNewItempedido = em.merge(oldFacturaIdOfItempedidoCollectionNewItempedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getFacturaId();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getFacturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itempedido> itempedidoCollectionOrphanCheck = factura.getItempedidoCollection();
            for (Itempedido itempedidoCollectionOrphanCheckItempedido : itempedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Itempedido " + itempedidoCollectionOrphanCheckItempedido + " in its itempedidoCollection field has a non-nullable facturaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
