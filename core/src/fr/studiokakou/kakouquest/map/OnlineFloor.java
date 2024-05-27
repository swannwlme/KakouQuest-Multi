package fr.studiokakou.kakouquest.map;


import fr.studiokakou.kakouquest.utils.Utils;

import java.util.ArrayList;

public class OnlineFloor {
    public Point pos;
    public String texturePath;

    public static String[] POSSIBLE_TEXTURE =  { "assets/map/floor_1.png", "assets/map/floor_2.png", "assets/map/floor_3.png", "assets/map/floor_4.png", "assets/map/floor_5.png", "assets/map/floor_6.png", "assets/map/floor_7.png", "assets/map/floor_8.png", };

    public OnlineFloor(Point pos){
        this.pos = pos;
        if (Utils.randint(0,4) == 0){
            this.texturePath = OnlineFloor.POSSIBLE_TEXTURE[Utils.randint(1,OnlineFloor.POSSIBLE_TEXTURE.length-1)];
        }else {
            this.texturePath = OnlineFloor.POSSIBLE_TEXTURE[0];
        }
    }

    public OnlineFloor(float x, float y){
        this.pos = new Point(x, y);
        if (Utils.randint(0,4) == 0){
            this.texturePath = OnlineFloor.POSSIBLE_TEXTURE[Utils.randint(1,OnlineFloor.POSSIBLE_TEXTURE.length-1)];
        }else {
            this.texturePath = OnlineFloor.POSSIBLE_TEXTURE[0];
        }
    }

    public OnlineFloor(){}

    public ArrayList<OnlineWall> getSurrounding(ArrayList<OnlineFloor> floors){

        ArrayList<Point> result = new ArrayList<>();
        result.add(this.pos.add(1, 0));
        result.add(this.pos.add(-1, 0));
        result.add(this.pos.add(0, 1));
        result.add(this.pos.add(0, -1));

        for (OnlineFloor f : floors){
            if (f.pos.equals(this.pos.add(1, 0))){
                result.set(0, null);
            }
            if (f.pos.equals(this.pos.add(-1, 0))){
                result.set(1, null);
            }
            if (f.pos.equals(this.pos.add(0, 1))){
                result.set(2, null);
            }
            if (f.pos.equals(this.pos.add(0, -1))){
                result.set(3, null);
            }
        }

        ArrayList<OnlineWall> walls = new ArrayList<>();

        Point orientaion = new Point(0, 0);
        if (result.get(0)!=null){
            orientaion = orientaion.add(1, 0);
        }
        if (result.get(1)!=null){
            orientaion = orientaion.add(-1, 0);
        }
        if (result.get(2)!=null){
            orientaion = orientaion.add(0, 1);
        }
        if (result.get(3)!=null){
            orientaion = orientaion.add(0, -1);
        }

        if (orientaion.equals(new Point(-1, -1))){
            walls.add(new OnlineWall(this.pos.add(-1, -1), "assets/map/wall_outer_front_left.png"));
            walls.add(new OnlineWall(this.pos.add(0, -1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos, "assets/map/wall_top_mid.png"));
            walls.add(new OnlineWall(this.pos.add(-1, 0), "assets/map/wall_edge_mid_right.png"));
        }
        if (orientaion.equals(new Point(1, -1))) {
            walls.add(new OnlineWall(this.pos.add(1, -1), "assets/map/wall_outer_front_right.png"));
            walls.add(new OnlineWall(this.pos.add(0, -1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos, "assets/map/wall_top_mid.png"));
            walls.add(new OnlineWall(this.pos.add(1, 0), "assets/map/wall_edge_mid_left.png"));
        }

        if (orientaion.equals(new Point(-1, 1))) {
            walls.add(new OnlineWall(this.pos.add(-1, 2), "assets/map/wall_outer_top_left.png"));
            walls.add(new OnlineWall(this.pos.add(0, 1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos.add(-1, 1), "assets/map/wall_edge_mid_right.png"));
            walls.add(new OnlineWall(this.pos.add(-1, 0), "assets/map/wall_edge_mid_right.png"));
            walls.add(new OnlineWall(this.pos.add(0, 2), "assets/map/wall_top_mid.png"));
        }
        if (orientaion.equals(new Point(1, 1))) {
            walls.add(new OnlineWall(this.pos.add(1, 2), "assets/map/wall_outer_top_right.png"));
            walls.add(new OnlineWall(this.pos.add(0, 1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos.add(1, 1), "assets/map/wall_edge_mid_left.png"));
            walls.add(new OnlineWall(this.pos.add(1, 0), "assets/map/wall_edge_mid_left.png"));
            walls.add(new OnlineWall(this.pos.add(0, 2), "assets/map/wall_top_mid.png"));
        }

        if (orientaion.equals(new Point(-1, 0))) {
            walls.add(new OnlineWall(this.pos.add(-1, 0), "assets/map/wall_edge_mid_right.png"));
        } if (orientaion.equals(new Point(1, 0))) {
            walls.add(new OnlineWall(this.pos.add(1, 0), "assets/map/wall_edge_mid_left.png"));
        }
        if (orientaion.equals(new Point(0, -1))) {
            walls.add(new OnlineWall(this.pos.add(0, -1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos, "assets/map/wall_top_mid.png"));
        } if (orientaion.equals(new Point(0, 1))) {
            walls.add(new OnlineWall(this.pos.add(0, 1), "assets/map/wall_mid.png"));
            walls.add(new OnlineWall(this.pos.add(0, 2), "assets/map/wall_top_mid.png"));
        }

        return walls;

    }
}
