/**
 * Title: Person
 * Description: Holds the current room and facing information of the person
 * Date: 02/15/2012
 * @author John Linford, Shailesh Benake
 * @version 1.0
 */

package cs286.apartment;

public class Person 
{
	/**
	 * Person constructor, sets up with room and facing
	 * @param f The current facing
	 * @param r The current room
	 */
	public Person(char f, Room r)
	{
		face = f;
		currentRoom = r;
	}
	
	/**
	 * Sets the current room to r
	 * @param r New current room
	 */
	public void setRoom(Room r)
	{
		currentRoom = r;
	}
	/**
	 * Sets the current facing to f
	 * @param f New facing
	 */
	public void setFace(char f)
	{
		face = f;
	}
	
	/**
	 * Returns the room that the person is in
	 * @return current room
	 */
	public Room getRoom()
	{
		return currentRoom;
	}
	/**
	 * Returns the current dir that the person is facing
	 * @return current facing
	 */
	public char getFace()
	{
		return face;
	}
	
	Room currentRoom;			// The current room the person is in
	char face;					// The current direction the person is facing
}
