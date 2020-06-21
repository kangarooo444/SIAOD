import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    public Location addCoord(int x, int y){
        return new Location(xCoord+x,yCoord+y);
    }


    public double gipotenuza(int x, int y){
        return (pow(abs(x-xCoord),2) + pow(abs(y-yCoord),2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xCoord == location.xCoord &&
                yCoord == location.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}