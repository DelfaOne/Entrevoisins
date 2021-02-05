package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Check if neighbourList contains all the neighbour generate by DUMMY_NEIGHBOURS
     */
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * Check if a neighbour is correctly deleted
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Check if getFavorites() return all neighbour added in favoriteList
     */
    @Test
    public void getFavoriteWithSuccess() {
        Neighbour neighbour = new Neighbour(1, "Toto", "toto.png", "rue de toto", "0356756984", "Lorem ipsum");
        service.getFavorites().add(neighbour);
        assertTrue(service.getFavorites().contains(neighbour));

    }

    /**
     * Check if addFavorites() correctly add neighbour in favoriteList
     */
    @Test
    public void addFavoriteWithSuccess() {
        Neighbour neighbour = new Neighbour(1, "Toto", "toto.png", "rue de toto", "0356756984", "Lorem ipsum");
        service.addFavorites(neighbour);
        assertTrue(service.getFavorites().contains(neighbour));
    }

    /**
     * Check if deleteFavorite() correctly delete neighbour in favoriteList
     */
    @Test
    public void deleteFavoriteWithSuccess() {
        Neighbour deletedNeighbour = new Neighbour(1, "Toto", "toto.png", "rue de toto", "0356756984", "Lorem ipsum");
        service.deleteFavorite(deletedNeighbour);
        assertFalse(service.getFavorites().contains(deletedNeighbour));
    }
}
