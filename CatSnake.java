/* Kevin Plotzker
 * CISS 241 
 * HVCC Spring 2016
 * 
 * CatSnake.java
 * 
 * This program will use the Graphics 2D class to draw static and moving 
 * shapes.  Static shapes include: solid green grass rectangle, a cyan to white gradient 
 * rectagle sky, a solid yellow ellipse sun, a solid green arc tree top, and a solid brown
 * basic stroke tree trunk.  A general path and ellipses are used to draw a cat head and eyes
 * on the grass rectangle, which uses the static sleep method to move from left to right 
 * across the screen.
 */

package graphics15;

import java.awt.geom.*;  //Needed for Graphics 2D
import javax.swing.JApplet; //Needed for applet
import java.awt.*;

public class CatSnake extends JApplet {

	private static final long serialVersionUID = 1L;  //Needed to avoid Eclipse warning
	boolean firstTime = true;  //Flag to indicate if static object need to be painted
	int catHeadLeft = 30;  //Starting x position of vertical cat head lines
	int catHeadRight = 80;
	int catEarLeft = 45; //Starting x position of diagonal cat ear lines 
	int catEarRight = 65;
	int catEyeLeftX = 37;  //Starting x position of cat eyes
	int catEyeRightX = 63;
	int appletHeight; //Applet width and height
	int appletWidth;
	
	//Method to initialize applet, and get dimensions
	public void init() {
		this.setSize(600, 400); // Sets size of applet viewer
		Dimension appletSize = this.getSize(); // Gets applet size
		appletHeight = appletSize.height;
		appletWidth = appletSize.width;
	}
	
	//Method to override paint, does not clear the screen to reduce flicker
	public void update(Graphics g) {
		paint(g);
	}
	
	//Method to paint the applet, calls repaint to move cat head after sleep and increment
	public void paint(Graphics g) {
 
		Graphics2D g2 = (Graphics2D) g;  //Instantiates new Graphics2D object, casts a Graphics object (g)
										 //as Graphics2D
		
		if (firstTime) {  //Paints static objects if first call to paint
			
			
			//Paints solid green grass rectangle
			
			Rectangle2D.Float grassRectangle = new Rectangle2D.Float(0, appletHeight / 2, appletWidth,
					appletHeight / 2);  //Spans entire applet width and half the height from bottom
			g2.setColor(Color.green);  //Sets color 
			g2.fill(grassRectangle);  //Paints grass

			
			//Paints gradient sky rectangle
			
			GradientPaint gradientPainter = new GradientPaint(0, 0, Color.cyan, appletWidth - 100, appletHeight - 100,
					Color.white, false);  //Instantiates new GradientPaint object using cyan and white
			//Rectangle spans entire applet width, half the height from top
			Rectangle2D.Float skyRectangle = new Rectangle2D.Float(0, 0, appletWidth, appletHeight / 2);
			g2.setPaint(gradientPainter); //Sets gradient color
			g2.fill(skyRectangle); //Paints sky

			
			//Paints Basic Stroke tree trunk
			
			BasicStroke trunkStroke = new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND); //Defines stroke
			//Positions endpoints to create a vertical tree trunk 50 pixels to the right of center, with base at horizon 
			Point2D.Float trunkTop = new Point2D.Float(appletWidth / 2 + 50, appletHeight / 2); 
			Point2D.Float trunkBottom = new Point2D.Float(appletWidth / 2 + 50, (appletHeight / 2) - 60);
			Line2D.Float trunkLine = new Line2D.Float(trunkTop, trunkBottom);  //Creates Line2D object
			g2.setColor(Color.darkGray);  //Sets color
			g2.setStroke(trunkStroke);  //Sets stroke
			g2.draw(trunkLine);  //Paints trunk

			
			//Paints solid green arc tree top
			
			Arc2D.Float treeArc = new Arc2D.Float(appletWidth / 2 + 10, appletHeight / 2 - 80, 80, 50, 10f, 160f,
					Arc2D.CHORD);  //Creates new Arc2D centered on top of trunk
			g2.setColor(Color.green);  //Sets color
			g2.fill(treeArc);  //Paints tree top

			
			//Paints solid yellow ellipse sun
			
			//Creates new Ellipse2D circle in top left corner of applet
			Ellipse2D.Float sunEllipse = new Ellipse2D.Float(appletWidth / 10, appletHeight / 10, 60f, 60f);
			g2.setColor(Color.yellow);  //Sets color
			g2.fill(sunEllipse);  //Paints sun

			firstTime = false;  //Changes flag so static shapes will not be repainted to reduce flicker
		}
		
		
		try{  //Try-catch block to handle animation and sleep
		
			// Sets cat head stroke
			BasicStroke catStroke = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			
			//Paints green rectangle over cat head
			Rectangle2D catCover = new Rectangle2D.Float(catHeadLeft - 5, 225, 65, 70);
			g2.setColor(Color.GREEN);
			g2.fill(catCover);
			
			//Increments x position of cat head and eyes, to move to the right
			catHeadLeft += 20;
			catHeadRight += 20;
			catEarLeft += 20;
			catEarRight += 20;
			catEyeLeftX += 20;
			catEyeRightX += 20;
			
			//Paints GeneralPath cat head in red, which will be visible
			GeneralPath catHead = new GeneralPath();
			catHead.moveTo(catHeadLeft, 230f);
			catHead.lineTo(catHeadLeft, 290f);
			catHead.lineTo(catHeadRight, 290f);
			catHead.lineTo(catHeadRight, 230f);
			catHead.lineTo(catEarRight, 245f);
			catHead.lineTo(catEarLeft, 245f);
			catHead.closePath();
			g2.setStroke(catStroke);
			g2.setColor(Color.red);
			g2.draw(catHead);

			//Paints Ellipse2D cat eyes in blue, which will be visible
			Ellipse2D.Float catEyeLeft = new Ellipse2D.Float(catEyeLeftX, 250, 10f, 10f);
			Ellipse2D.Float catEyeRight = new Ellipse2D.Float(catEyeRightX, 250, 10f, 10f);
			g2.setColor(Color.blue);
			g2.fill(catEyeLeft);
			g2.fill(catEyeRight);
			
			//Sleeps for quarter second
			Thread.sleep(250);
			
		} catch (InterruptedException e) {  //Empty catch to handle interrupted exception

		}
		if (catHeadRight < appletWidth - 20) {  //Tests if right edge of cat head is on the applet.  
			repaint();  //Calls repaint if head is on the applet.  Static shapes are not redrawn,
						//cat head is covered in green, incremented, and repainted 
		}
	}
}
