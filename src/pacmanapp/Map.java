/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author csc190
 */
public class Map {

    protected char[][] map;
    protected int[] arrSx = new int[]{1, 0, -1, 0};
    protected int[] arrSy = new int[]{0, -1, 0, 1}; //East,North,West,South
    protected static Map Instance;
    boolean[][] wasHere = new boolean[12][12];// = new boolean[1000][1000];
    //boolean[][] correctPath = new boolean[100][100];
    protected GameEngine ge;
    int startX, startY;
    int endX, endY;

    public Map(char[][] Map) {
        this.map = Map;
        Instance = this;
    }

    public static Map getInstance() {
        return Instance;
    }

    public int decideDirection(int x, int y, int dx, int dy) {
        reset();
        ArrayList<Integer> path = new ArrayList<Integer>();
        boolean result = getAvailablePath(x, y, dx, dy, path);
        if (!result) {
            int bp = 1;
            return -1;
        } else {
            int firstDir = path.get(0);
            return firstDir;
        }
    }

    public Set<Integer> getAvailableDirection(int x, int y) {   //return a possible direction given a coordinate
        Set<Integer> Dir = new HashSet<Integer>();
        int nx = x / 50;
        int ny = y / 50;
        for (int dir = 0; dir < 4; dir++) {
            int dx = arrSx[dir];
            int dy = arrSy[dir];
            int fx = nx + dx;
            int fy = ny + dy;
            if (fx >= 0 && fx < 12 && fy >= 0 && fy < 12 && map[fy][fx] != '#') {
                Dir.add(dir);
            }
        }
        return Dir;
    }

    public void reset() {
        for (int a = 0; a < 12; a++) {
            for (int b = 0; b < 12; b++) {
                wasHere[a][b] = false;
            }
        }
    }

    public boolean getAvailablePath(int x, int y, int dx, int dy, ArrayList<Integer> path) { // get a set of direction from map[y][x] to map[dy][dx]
        
        x = x/50;
        y = y/50;
        dx = dx/50;
        dy= dy/50;
        System.out.println("x: " + x + ", y: " + y);
        wasHere[y][x] = true;
        Set<Integer> Dir = getAvailableDirection(x*50, y*50);
        for (int dir : Dir) {
            int nx = x + arrSx[dir];
            int ny = y + arrSy[dir];
            int ax = nx;
            int ay = ny;
            path.add(dir);
            if (ax != dx && ay != dy && wasHere[ay][ax] != true) {
                if(getAvailablePath(ax*50, ay*50, dx*50, dy*50, path)){
                    return true;
                };
            }else{
                //remove the last element from path
                int lastIdx= path.size()-1;
                path.remove(lastIdx);
            } 
        }
        return false;
    }

    public ArrayList<Integer> getPath(int x, int y, int dx, int dy) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        getAvailablePath(x, y, dx, dy, path);
        return path;
    }

    /*
    public void solveMap() {
        for (int row = 0; row < 99; row++) {
            for (int col = 0; col < 99; col++) {
                wasHere[col][row] = false;
                correctPath[col][row] = false;
            }
        }
        boolean b = recursiveSolve(startX, startY);
    }

    public boolean recursiveSolve(int x, int y) {
        if (x == endX && y == endY) {
            return true; // If you reached the end
        }
        if (map[y][x] == '#' || wasHere[x][y]) {
            return false;
        }
        // If you are on a wall or already were here
        wasHere[x][y] = true;
        if (x != 0) // Checks if not on left edge
        {
            if (recursiveSolve(x - 1, y)) { // Recalls method one to the left
                correctPath[y][x] = true; // Sets that path value to true;
                return true;
            }
        }
        if (x != 99) // Checks if not on right edge
        {
            if (recursiveSolve(x + 1, y)) { // Recalls method one to the right
                correctPath[y][x] = true;
                return true;
            }
        }
        if (y != 0) // Checks if not on top edge
        {
            if (recursiveSolve(x, y - 1)) { // Recalls method one up
                correctPath[y][x] = true;
                return true;
            }
        }
        if (y != 99) // Checks if not on bottom edge
        {
            if (recursiveSolve(x, y + 1)) { // Recalls method one down
                correctPath[y][x] = true;
                return true;
            }
        }
        return false;
    }*/
}
