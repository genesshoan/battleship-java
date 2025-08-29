package dev.shoangenes.model;

import dev.shoangenes.model.enums.ShotResult;
import java.util.function.Predicate;

public class Board {
    private final int SIZE = 10;
    private final Cell[][] cells;

    /**
     * Initializes a 10x10 board with empty cells.
     * Each cell is represented by a Cell object.
     */
    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * Checks if the given row and column are within the valid board boundaries.
     * @param row The row index to check.
     * @param col The column index to check.
     * @return True if the indices are within bounds, false otherwise.
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < SIZE && col < SIZE;
    }

    /**
     * Checks if the specified cells are free (empty) for placing a ship.
     * @param row The starting row index.
     * @param col The starting column index.
     * @param shipSize The number of cells to check.
     * @param horizontal True if checking horizontally, false if vertically.
     * @return True if all specified cells are empty, false otherwise.
     */
    private boolean areCellsFree(int row, int col, int shipSize, boolean horizontal) {
        return iterateCells(row, col, shipSize, horizontal, Cell::isEmpty);
    }

    /**
     * Checks if the ship can be placed within the board's boundaries.
     * @param row The starting row index.
     * @param col The starting column index.
     * @param size The size of the ship (number of cells it occupies).
     * @param horizontal True if the ship is placed horizontally, false if vertically.
     * @return True if the ship fits within the board's boundaries, false otherwise.
     */
    private boolean isWithinBounds(int row, int col, int size, boolean horizontal) {
        if (!isValid(row, col)) {
            return false;
        }

        if (horizontal) {
            return col + size <= SIZE;
        } else {
            return row + size <= SIZE;
        }
    }

    /**
     * Place a ship in the given x and y coordinates.
     * @param ship The ship to place.
     * @param row The x coordinate of the ship's starting position.
     * @param col The y coordinate of the ship's starting position.
     * @param horizontal True if the ship is placed horizontally, false if vertically.
     */
    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        int size = ship.getSize();
        if (!areCellsFree(row, col, size, horizontal) || !isWithinBounds(row, col, ship.getSize(), horizontal)) {
            return false;
        }

        return iterateCells(row, col, size, horizontal, (cell) -> {
            cell.setShip(ship);
            return true;
        });
    }

    /**
     * Receives an attack at the given x and y coordinates.
     * @param row The x coordinate of the attack.
     * @param col The y coordinate of the attack.
     * @return The result of the attack (WATER, HIT, SUNK, ALREADY_SHOT, or OUT_OF_BOUNDS).
     */
    public ShotResult receiveAttack(int row, int col) {
        if (row < 0 || col < 0 || row >= SIZE || col >= SIZE) {
            return ShotResult.OUT_OF_BOUNDS;
        }

        Cell cell = cells[row][col];
        return cell.markAttack();
    }

    /**
     * Iterator implementation that traverses cells either horizontally or vertically,
     * applying the provided predicate to each cell.
     * @param row The starting row index.
     * @param col The starting column index.
     * @param size The number of cells to iterate over.
     * @param horizontal True if iterating horizontally, false if vertically.
     * @param predicate The predicate to apply to each cell.
     * @return True if the predicate returns true for all cells, false otherwise.
     */
    private boolean iterateCells(int row, int col, int size, boolean horizontal, Predicate<Cell> predicate) {
        int dRow = horizontal ? 0 : 1;
        int dCol = horizontal ? 1 : 0;

        for (int i = 0; i < size; i++) {
            if (!predicate.test(cells[row + i * dRow][col + i * dCol])) {
                return false;
            }
        }
        return true;
    };
}
