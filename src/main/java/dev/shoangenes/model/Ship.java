package dev.shoangenes.model;

import dev.shoangenes.model.enums.ShipType;

public class Ship {
    private final ShipType shipType;
    private int hits;
    private boolean sunk;

    /**
     * Constructor for Ship
     * @param shipType The type of ship
     */
    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.hits = 0;
        this.sunk = false;
    }

    /**
     * Check if the ship is sunk
     * @return true if the ship is sunk, false otherwise
     */
    public boolean isSunk() {
        return sunk;
    }

    /**
     * Register a hit on the ship
     * @return true if the ship is sunk after the hit, false otherwise
     */
    public boolean receiveHit() {
        hits++;
        if (hits >= shipType.getSize()) {
            sunk = true;
        }
        return sunk;
    }

    /**
     * Get the name of the ship
     * @return The name of the ship
     */
    public String getName() {
        return shipType.toString();
    }

    /**
     * Get the size of the ship
     * @return The size of the ship
     */
    public int getSize() {
        return shipType.getSize();
    }
}
