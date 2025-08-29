package dev.shoangenes.model;

import dev.shoangenes.model.enums.ShotResult;
import dev.shoangenes.model.enums.CellState;

public class Cell {
    private CellState state;
    private Ship ship;

    /**
     * Initializes a cell with an EMPTY state.
     */
    public Cell() {
        this.state = CellState.EMPTY;
        this.ship = null;
    }

    /**
     * Sets the cell's state to SHIP and associates it with a Ship object.
     * @param ship The ship to be placed in this cell.
     */
    public void setShip(Ship ship) {
        this.state = CellState.OCCUPIED_SHIP;
        this.ship = ship;
    }

    /**
     * Retrieves the ship associated with this cell, if any.
     * @return The ship in this cell, or null if the cell is empty.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Retrieves the current state of the cell.
     * @return The state of the cell (EMPTY, SHIP, HIT, or MISS).
     */
    public CellState getState() {
        return state;
    }

    /**
     * Checks if the cell has already been shot at.
     * @return true if the cell has been hit or missed, false otherwise.
     */
    public boolean wasAlreadyShot() {
        return state == CellState.HIT ||
                state == CellState.MISS ||
                state == CellState.SUNK;
    }

    /**
     * Checks if the cell can be shot at (i.e., it is either EMPTY or contains an OCCUPIED_SHIP).
     * @return true if the cell is empty or contains a ship, false otherwise.
     */
    public boolean canBeShot() {
        return state == CellState.EMPTY ||
                state == CellState.OCCUPIED_SHIP;
    }

    /**
     * Checks if the cell has reference to a ship.
     * @return true if the cell has a ship, false otherwise.
     */
    public boolean hasShip() {
        return ship != null;
    }

    /**
     * Checks if the cell is empty.
     * @return true if the cell is empty.
     */
    public boolean isEmpty() {
        return state == CellState.EMPTY;
    }

    /**
     * Marks the cell as attacked. If the cell contains a ship, it updates the state to HIT
     * and notifies the ship of the hit. If the cell is empty, it updates the state to MISS.
     */
    public ShotResult markAttack() {
        if (wasAlreadyShot()) {
            return ShotResult.ALREADY_SHOT;
        }

        if (hasShip()) {
            state = ship.receiveHit() ? CellState.SUNK : CellState.HIT;
            return state == CellState.SUNK ? ShotResult.SUNK : ShotResult.HIT;
        } else {
            state = CellState.MISS;
            return ShotResult.WATER;
        }
    }
}
