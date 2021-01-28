package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all the Favorites
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Add Favorite
     * @return {@link List}
     */
    void addFavorites(Neighbour neighbour);

    /**
     * Delete a Favorite Neighbour
     * @return {@link List}
     */
    void deleteFavorite(Neighbour neighbour);

    /**
     * Delete a Favorite Neighbour
     * @return {@link List}
     */
    //boolean isFavorite(Neighbour neighbour);
}
