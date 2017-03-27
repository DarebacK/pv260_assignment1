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
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    
    private final int dir;
    
    private Direction(int dir) {
        this.dir = dir;
    }
    
    public Direction add() {
        if(this.dir==3) return Direction.values()[0];
        return Direction.values()[this.dir+1];
    }
    
    public Direction min() {        
        if(this.dir==0) return Direction.values()[3];
        return Direction.values()[this.dir-1];
    }
}
