package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    //Retourne la List<Neighbour>
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    //Fonction qui permet de delete un neighbour en paramétres
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    @Override
    public void addFavorites(Neighbour neighbour) {
        if (!favorites.contains(neighbour)) {
            favorites.add(neighbour);
        }
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        favorites.remove(neighbour);
    }

    /*public boolean isFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbours)) {
            return true;
        } else {
            return false;
        }
    }*/
}
