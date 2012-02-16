/**
 * Title: Room
 * Description: A room inside the apartment
 * Date: 2/15/2012
 * @author John Linford, Shailesh Benake
 * @version 1.0
 */

package cs286.apartment;

public class Room 
{
	/**
	 * Room constructor that holds the current room's images and text description
	 * @param nV picture north of this room
	 * @param eV picture east of this room
	 * @param sV picture south of this room
	 * @param wV picture west of this room
	 * @param s the description
	 */
	public Room(int nV, int eV, int sV, int wV, String s)
	{
		northView = nV;
		eastView = eV;
		southView = sV;
		westView = wV;
		
		description = s;
	}
	
	/**
	 * Sets the adjacent rooms to the current room
	 * @param nR The room north of current room
	 * @param eR The room east of current room
	 * @param sR The room south of current room
	 * @param wR The room west of current room
	 */
	public void setAdjRooms(Room nR, Room eR, Room sR, Room wR)
	{
		northRoom = nR;
		eastRoom = eR;
		southRoom = sR;
		westRoom = wR;
	}
	
	/**
	 * Gets the image depending on the parameter direction
	 * @param dir Direction of image to get (n/s/e/w)
	 * @return The image of the facing
	 */
	public int getView(char dir)
	{
		if (dir == 'n')
			return northView;
		else if (dir == 'e')
			return eastView;
		else if (dir == 's')
			return southView;
		else if (dir == 'w')
			return westView;
		else
			return 0;
	}
		
	/**
	 * Gets the adjacent room in the direction of the parameter
	 * @param dir The direction to get adjacent room
	 * @return The adjacent room in direction dir
	 */
	public Room getRoom(char dir)
	{
		if (dir == 'n')
			return northRoom;
		else if (dir == 'e')
			return eastRoom;
		else if (dir == 's')
			return southRoom;
		else if (dir == 'w')
			return westRoom;
		else
			return null;
	}
	
	/**
	 * Gets the text description of the current room
	 * @return Text description of current room
	 */
	public String getDesc()
	{
		return description;
	}
	
	String description;			// Text description
	Room northRoom;				// North adj room
	Room eastRoom;				// East adj room
	Room southRoom;				// South adj room
	Room westRoom;				// West adj room
	int northView;				// North view
	int eastView;				// East view
	int southView;				// South view
	int westView;				// West view
}
