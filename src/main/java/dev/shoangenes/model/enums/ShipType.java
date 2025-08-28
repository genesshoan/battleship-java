package dev.shoangenes.model.enums;

public enum ShipType {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    CRUISER(3, "Cruiser"),
    SUBMARINE(3, "Submarine"),
    DESTROYER(2, "Destroyer");

    int size;
    String name;

    /**
     * Constructor for ShipType
     * @param size The size of the ship
     * @param name The name of the ship
     */
    ShipType(int size, String name) {
        this.size = size;
        this.name = name;
    }

    /**
     * Get the size of the ship
     * @return The size of the ship
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the standard fleet of ships
     * @return An array of ShipType representing the standard fleet
     */
    public static ShipType[] getStandardFleet() {
        return values();
    }

    /**
     * Get the name of the ship
     * @return The name of the ship
     */
    @Override
    public String toString() {
        return name;
    }
}
