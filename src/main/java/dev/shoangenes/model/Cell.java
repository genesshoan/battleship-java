package dev.shoangenes.model;

import dev.shoangenes.model.enums.CellState;

public class Cell {
    private CellState state;
    private Ship ship;

    /**
     * Initializes a cell with an EMPTY state.
     */
    public Cell() {
        this.state = CellState.EMPTY;
    }

    /**
     * Sets the cell's state to SHIP and associates it with a Ship object.
     * @param ship The ship to be placed in this cell.
     */
    public void setShip(Ship ship) {
        this.state = CellState.SHIP;
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
     * Marks the cell as attacked. If the cell contains a ship, it updates the state to HIT
     * and notifies the ship of the hit. If the cell is empty, it updates the state to MISS.
     */
    public void markAttack() {
        if (state == CellState.SHIP) {
            state = CellState.HIT;
            ship.receiveHit();
        } else if (state == CellState.EMPTY) {
            state = CellState.MISS;
        }
    }
}
