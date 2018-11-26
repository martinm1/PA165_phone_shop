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
        if (orderId == null || orderId < 0){
            throw new IllegalArgumentException("orderId is null or less than 0!");
        }
        return entityManager.createQuery("select c from Claim c join c.order o where o.id = :orderId",
                Claim.class).
                setParameter("orderId", orderId).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByUserId(Long userId) {
        if (userId == null || userId < 0){
            throw new IllegalArgumentException("orderId is null or less than 0!");
        }
        return entityManager.createQuery
                ("select c from Claim c join c.order o join o.person p where p.id = :userId", Claim.class).
                setParameter("userId", userId).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByClaimState(ClaimState claimState) {
        if (claimState == null){
            throw new IllegalArgumentException("claimState is null!");
        }
        return entityManager.createQuery("select c from Claim c where c.claimState = :claimState",
                Claim.class).
                setParameter("claimState", claimState).
                getResultList();
    }

    @Override
    public List<Claim> findClaimByClaimSolution(ClaimSolution claimSolution) {
        if (claimSolution == null){
            throw new IllegalArgumentException("claimState is null!");
        }
        return entityManager.createQuery("select c from Claim c where c.wantedSolutionByCustomer = :claimSolution",
                Claim.class).
                setParameter("claimSolution", claimSolution).
                getResultList();
    }
}
