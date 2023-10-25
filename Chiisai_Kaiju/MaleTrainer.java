
public class MaleTrainer extends MovingImage {
	// FIELDS

	private double moveX, moveY;

	// CONSTRUCTOR
	public MaleTrainer(int x, int y) 
	{
		super("boy1.gif", x, y, 48, 48);
		moveX = 0;
		moveY = 0;
	}

	// METHODS FOR WALKING
	public void walkHorizontal(int dir) 
	{
		// positive direction means right
		// negative direction means left
		moveX = dir * 48;
		moveByAmount((int)moveX, 0);
	}

	public void walkVertical(int dir) 
	{
		// positive direction means up
		// negative direction means down
		moveY = dir * 48;
		moveByAmount(0, (int)moveY);
	}
	
	public void newMapYUp ()
	{
		moveByAmount(0, 624);
	}
	
	public void newMapYDown ()
	{
		moveByAmount(0, -624);
	}

	public void newMapXLeft ()
	{
		moveByAmount(672, 0);
	}
	
	public void newMapXRight ()
	{
		moveByAmount(-672, 0);
	}
	
}