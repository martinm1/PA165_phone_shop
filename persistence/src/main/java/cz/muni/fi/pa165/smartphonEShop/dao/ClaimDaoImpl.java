package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClaimDaoImpl implements ClaimDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Claim claim) {
        if (claim == null){
            throw new IllegalArgumentException("claim is null!");
        }
        entityManager.persist(claim);
    }

    @Override
    public void update(Claim claim) {
        if (claim == null){
            throw new IllegalArgumentException("claim is null!");
        }
        entityManager.merge(claim);
    }

    @Override
    public void delete(Claim claim) {
        if (claim == null){
            throw new IllegalArgumentException("claim is null!");
        }
        entityManager.remove(claim);
    }

    @Override
    public Claim findById(Long id) {
        if (id == null || id < 0){
            throw new IllegalArgumentException("claim is null or less than 0!");
        }
        return entityManager.find(Claim.class, id);
    }

    @Override
    public List<Claim> findAll() {
        return entityManager.createQuery("select c from Claim c", Claim.class).getResultList();
    }

    @Override
    public List<Claim> findClaimByOrderId(Long orderId) {
        return entityManager.createQuery("select c from Claim c join c.order o where o.id = :orderId").
                setParameter("orderId", orderId).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByUserId(Long userId) {
        return entityManager.createQuery("select c from Claim c join c.order o join o.person p where p.id = :userId").
                setParameter("userId", userId).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByClaimState(ClaimState claimState) {
        return entityManager.createQuery("select c from Claim c where c.claimState = :claimState").
                setParameter("claimState", claimState).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByClaimSolution(ClaimSolution claimSolution) {
        return entityManager.createQuery("select c from Claim c where c.claimSolution = :claimSolution").
                setParameter("claimSolution", claimSolution).
                getResultList();
    }
}
