package Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public enum Direction {
    UP(1),
    RIGHT(2),
    DOWN(3),
    LEFT(4);
    
    private final int dir;
    
    private Direction(int dir) {
        this.dir = dir;
    }
    
    public Direction add() {
        if(this.dir==4) return Direction.values()[0];
        return Direction.values()[this.dir];
    }
    
    public Direction min() {        
        if(this.dir==1) return Direction.values()[3];
        return Direction.values()[this.dir-2];
    }
}
