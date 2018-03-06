/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.Region;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class CommonBean {

    @PersistenceContext(name = "birds-ejbPU")
    private EntityManager em;

    public List<Region> getAllRegions() {
        List<Region> listofRegions = null;

        String queryString = "";
        try {
            queryString = "SELECT e FROM Region e";

            listofRegions = (List<Region>) em.createQuery(queryString).getResultList();
            return listofRegions;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public String createNewRegion(Region region) {
        try {
            region.setUpdated("NO");
            region.setDeleted("NO");
            em.persist(region);
            em.flush();
            return region.getRegionId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean regionLogicalDelete(Region region) {
        try {
            region.setDeleted("YES");
            region.setUpdated("NO");
            em.merge(region);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean regionUpdateDetails(Region region) {
        try {
            region.setDeleted("NO");
            region.setUpdated("NO");
            em.merge(region);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean regionDelete(Region region, boolean permanent) {
        try {
            if (permanent == true) {
                em.remove(em.merge(region));
            } else if (permanent == false) {
                region.setDeleted("YES");
                region.setUpdated("NO");
                em.merge(region);
            }
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Region regionFind(String regionId) {
        try {
            Region region = em.find(Region.class, regionId);
            return region;
        } catch (Exception e) {
            return null;
        }
    }

}
