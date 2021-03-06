/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

/**
 *
 * @author csc190
 */
public class Pacman implements movingSprites {

    protected int x, y, sx, sy;
    protected int picIdx = 0;
    protected String[] arrPics = {"pacman1.png", "pacman2.png", "pacman3.png"};

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return 40;
    }

    public int getH() {
        return 40;
    }

    public Pacman(int x, int y, int sx, int sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    public void setDirection(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void draw(API api) {
        String pic = this.arrPics[this.picIdx];
        api.drawImg(pic, x, y, 40, 40);
    }

    private int counter = 0;

    @Override
    public void update() {
        counter++;
        this.x += this.sx;
        this.y += this.sy;

        if (counter % 25 == 0) {
            this.picIdx = (picIdx + 1) % this.arrPics.length;
        }
    }

    @Override
    public int decideDirection(int x, int y) {
        return 0;
    }

}
