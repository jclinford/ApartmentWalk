/**
 * 	Title: ApartmentWalk
 *  Description: Walks through an apartment and shows user images
 *  Date: 2/15/2012
 *  @author John Linford,Shailesh Benake
 *  @version 1.0
 */

package cs286.apartment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Driver/entry class
 * 
 * Sets up all variables and is the main driver for the
 * apartmentwalk application. 
 */
public class ApartmentWalkActivity extends Activity 
{
	
	/** Called when the activity is first created.
	 * @param savedInstanceState 
	 * 			the saved instance state from previous opening 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getContentView(R.layout.main);
        // setting up handles to the image and texts views
        view = (ImageView) findViewById(R.id.image);
       // view.setRotation(90);
        
        textView = (TextView) findViewById(R.id.text);
        
        // set up kitchen
        int nImage = R.drawable.k_n;
        int eImage = R.drawable.k_e;
        int sImage = R.drawable.k_s;
        int wImage = R.drawable.k_w;
        kitchen = new Room(nImage, eImage, sImage, wImage, kString);
        
        // lroom 1
        nImage = R.drawable.lr_n;
        eImage = R.drawable.lr_e;
        sImage = R.drawable.lr_s;
        wImage = R.drawable.lr_w;
        livingRoom1 = new Room(nImage, eImage, sImage, wImage, lr1String);
        
        // lroom2
        nImage = R.drawable.lr2_n;
        eImage = R.drawable.lr2_e;
        sImage = R.drawable.lr2_s;
        wImage = R.drawable.lr2_w;
        livingRoom2 = new Room(nImage, eImage, sImage, wImage, lr2String);
        
        // bedroom
        nImage = R.drawable.bd_n;
        eImage = R.drawable.bd_e;
        sImage = R.drawable.bd_s;
        wImage = R.drawable.bd_w;
        bedRoom = new Room(nImage, eImage, sImage, wImage, bdString);
        
        // bathroom
        nImage = R.drawable.bth_n;
        eImage = R.drawable.bth_e;
        sImage = R.drawable.bth_s;
        wImage = R.drawable.bth_w;
        bathRoom = new Room(nImage, eImage, sImage, wImage, bthString);
        
        
        // now we need to connect the rooms properly based on my apartments layout...
        // i.e. kitchen only has a livingroom1 to south.., etc
        kitchen.setAdjRooms(null, null, livingRoom1, null);
        livingRoom1.setAdjRooms(kitchen, livingRoom2, null, null);
        livingRoom2.setAdjRooms(bedRoom, null, null, livingRoom1);
        bedRoom.setAdjRooms(null, bathRoom, livingRoom2, null);
        bathRoom.setAdjRooms(null, null, null, bedRoom);
        
        // set up our person to default in the living room2, facing north
        person = new Person('n', livingRoom2);

        // set our view to be the same as person
        view.setImageResource(livingRoom2.getView('n'));
        
        // set text
        textView.setText(livingRoom2.getDesc());
    }
    
    /**
     * Returns the content view, returning null because we dont use it
     * @param main id
     * @return returns null
     */
    private Context getContentView(int main) 
    {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * When forward button is clicked this method is called
     * Once called, it will check if next room is available and
     * move the person accordingly, else displays the wall hit message
     * in the toast view.
     * @param v The current view the action takes place in
     */
	public void onForwardButton(View v)
    {
    	// current face and current room position
    	char curFace = person.getFace();
    	Room curRoom = person.getRoom();
    	
    	// if we can move forward, then get room != null
    	if (curRoom.getRoom(curFace) != null)
    	{
    		Room newRoom = curRoom.getRoom(curFace);
    		
    		// move forward
    		person.setRoom(newRoom);
    		
    		// update view with newroom, but still same face
    		view.setImageResource(newRoom.getView(curFace));
    		
    		// update text
    		textView.setText(newRoom.getDesc());
    	}else
    	{
    		// if we can't move forward display wall text
    		Toast.makeText(v.getContext(), "Ouch! You hit a wall!", Toast.LENGTH_SHORT).show();
    	}
    }
    
	/**
	 * When the turn button is clicked this message is called.
	 * Will rotate the player's facing clockwise (to the right)
	 * and update the view accordingly.
	 * @param v The view that the action took place in
	 */
    public void onTurnButton(View v)
    {
    	char curFace = person.getFace();
    	Room curRoom = person.getRoom();
    	
    	// turn clockwise.. so n becomes e, etc and update person
    	if (curFace == 'n')
    	{
    		curFace = 'e';
    		person.setFace(curFace);
    		
    		//update view with new face, same room
    		view.setImageResource(curRoom.getView(curFace));
    	}else if (curFace == 'e')
    	{
    		curFace = 's';
    		person.setFace(curFace);
    		
    		view.setImageResource(curRoom.getView(curFace));
    	}else if (curFace == 's')
    	{
    		curFace = 'w';
    		person.setFace(curFace);
    		
    		view.setImageResource(curRoom.getView(curFace));
    	}
    	else if (curFace == 'w')
    	{
    		curFace = 'n';
    		person.setFace(curFace);
    		
    		view.setImageResource(curRoom.getView(curFace));
    	}
    }
    
    Context context;			// Context used for toast messages
    ImageView view;				// ImageView that images are displayed in
    TextView textView;			// TextView for room location information
    Person person;				// The person holds the current room and direction
    Room kitchen;				// Kitchen room, connected to livingRoom1
    Room livingRoom1;			// LivingRoom1, connected to kitchen and livingRoom2
    Room livingRoom2;			// LivingRoom2, connected to livingRoom1 and bedRoom
    Room bedRoom;				// bedRoom, connected to livingRoom2 and bathRoom
    Room bathRoom;				// bathRoom, connected to bedRoom
    
    // Strings to display at top for orientation and walls for location information
    String kString = "You are inside the kitchen.";
    String lr1String = "You are inside the living room.";
    String lr2String = "You are inside the den";
    String bdString = "You are inside the bedroom.";
    String bthString = "You are inside the bathroom.";
    String wallString = "Ouch! You hit a wall!";
}