package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
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
}
