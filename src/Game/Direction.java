package Game;

/**
 * Enum for player's direction
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private final int dir;

    private Direction(int dir) {
        this.dir = dir;
    }

    /**
     *Method returns direction on pressing on right mouse button
     *right mouse button will turn the player in the clockwise direction
     * @return 
     */
    public Direction GetDirectionForRightButton() {
        if (this.dir == 3) {
            return Direction.values()[0];
        }
        return Direction.values()[this.dir + 1];
    }

    /**
     *Method returns direction on pressing on left mouse button
     *left mouse button will turn the player in the counter-clockwise direction
     * @return 
     */
    public Direction GetDirectionForLeftButton() {
        if (this.dir == 0) {
            return Direction.values()[3];
        }
        return Direction.values()[this.dir - 1];
    }
}
