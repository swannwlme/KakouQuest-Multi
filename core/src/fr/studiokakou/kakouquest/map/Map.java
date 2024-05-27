package fr.studiokakou.kakouquest.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.studiokakou.kakouquest.utils.Utils;
import fr.studiokakou.network.ServerMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * The Map class represents a game map.
 * This class is used to create a Map object.
 *
 * @version 1.0
 */
public class Map {
    /**
     * The floors of the map. It is a list of floors.
     */
    public ArrayList<Floor> floors = new ArrayList<>();

    public int map_height;
    /**
     * The width of the map.
     */
    public int map_width;
    /**
     * The list of rooms.
     */
    ArrayList<Room> rooms =  new ArrayList<>();
    /**
     * The list of bridges.
     */
    ArrayList<Bridge> bridges = new ArrayList<>();
    /**
     * The list of walls.
     */
    ArrayList<Wall> walls = new ArrayList<>();
    /**
     * The minimum height of a room.
     */
    public static int ROOM_MIN_HEIGHT=7;
    /**
     * The minimum width of a room.
     */
    public static int ROOM_MIN_WIDTH=7;
    /**
     * The maximum height of a room.
     */
    public static int ROOM_MAX_HEIGHT=21;
    /**
     * The maximum width of a room.
     */
    public static int ROOM_MAX_WIDTH=21;
    /**
     * A hash table storing distances.
     */
    public Hashtable<Float, Object> distances = new Hashtable<>();


    /**
     * Constructs a Map object.
     * This constructor is used to create a Map object.
     *
     * @param width  the width of the map
     * @param height the height of the map
     */
    public Map(int width, int height){
        this.map_height = height;
        this.map_width = width;
    }

    public Map(ServerMap onlineMap){

        this.floors.clear();

        this.map_height = onlineMap.map_height;
        this.map_width = onlineMap.map_width;

        this.rooms = onlineMap.rooms;
        this.bridges = onlineMap.bridges;

        System.out.println("floors size: " + onlineMap.floors.size());

        for (int i = 0; i < onlineMap.floors.size(); i++) {
            this.floors.add(new Floor(onlineMap.floors.get(i)));
        }

//        for (int i = 0; i < onlineMap.walls.size(); i++) {
//            this.walls.add(new Wall(onlineMap.walls.get(i)));
//        }
    }

    /**
     * Draws the map.
     *
     * @param batch the sprite batch
     */
    public void drawMap(SpriteBatch batch){
        Texture tmp = new Texture("assets/map/floor_1.png");

        for (Floor f : this.floors){
            batch.draw(tmp, f.pos.x, f.pos.y);
        }

        for (Wall w : this.walls){
            w.draw(batch);
        }
    }

    /**
     * Returns the player spawn point.
     *
     * @return the spawn point
     */
    public Point getPlayerSpawn(){
        return this.rooms.get(0).getCenterOutOfMap();
    }


    /**
     * Checks if points are on floor.
     *
     * @param points the points to check
     * @return true if all points are on floor, false otherwise
     */
    public boolean arePointsOnFloor(Point[] points){
        boolean[] areIn = new boolean[points.length];
        Arrays.fill(areIn, false);

        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            for (Floor floor : this.floors) {
                Point p1 = floor.pos;
                Point p2 = floor.pos.add(Floor.TEXTURE_WIDTH, Floor.TEXTURE_HEIGHT);
                if (point.isPointIn(p1, p2)) {
                    areIn[i]=true;
                }
            }
        }

        for (boolean isIn : areIn){
            if (!isIn){
                return false;
            }
        }

        return true;
    }

    /**
     * Disposes resources.
     */
    public void dispose(){
        for (Floor f : this.floors){
            f.texture.dispose();
        }
    }
}
