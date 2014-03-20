package homework07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * An application for demonstrating how to use JScrollPane objects effectively.
 *
 * @author *** Your name here ***
 * @version March 3, 2014
 */
public class TreePanel extends JPanel implements MouseMotionListener, MouseListener {

    // Instance variables for our TreePanel object..

    private JScrollPane enclosingPane;
    private int lastMouseX, lastMouseY;
    private boolean dimensionsCalculated;
    private Tree panelTree;

    public TreePanel(Tree inputTree) {
        dimensionsCalculated = false;
        this.panelTree = inputTree;
        // Create a frame to hold the scroll pane.

        JFrame frame = new JFrame("Tree Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Other code will go right here.


        // Create a scroll pane to contain our oversized JPanel.
        //   Add it to the center of the content area.

        JScrollPane pane = new JScrollPane(this);
        pane.setBackground(Color.WHITE);
        frame.add(pane, BorderLayout.CENTER);

        // Register the pane with our panel.
        this.setEnclosingPane(pane);

        // Add self mouse listeners to our panel.
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // make the frame automatically maximized
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        // Pack the frame and make it visible.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Allows the main method to set an instance variable in this panel.
     *
     * @param pane a JScrollPane object that is controlling the view of this panel
     */
    public void setEnclosingPane(JScrollPane pane) {
        enclosingPane = pane;
    }

    /**
     * The paint method for drawing our panel.  Note that because our
     * panel is huge, and the visible window is small, the graphics object
     * will be set to 'clip' any drawing that occurs outside of the current
     * drawing area.  (The current drawing area can be quite small.  If the
     * window is scrolled, only the newly exposed region needs to be drawn.)
     *
     * @param g a graphics object
     */
    public void paint(Graphics g) {

        g.setFont(new Font("Serif", Font.PLAIN, 16));

        // calculates the correct dimensions

        if (dimensionsCalculated == false) {
            int treeWidth = panelTree.getTreeWidth(g);
            System.out.println("paint: treeWidth = " + treeWidth);
            int treeHeight = panelTree.getTreeHeight(g);
            System.out.println("paint: treeHeight = " + treeHeight);

            this.setMinimumSize(new Dimension(treeWidth, treeHeight));
            this.setPreferredSize(new Dimension(treeWidth, treeHeight));

            dimensionsCalculated = true;
        }

        // Get clipped coordinates
        int upperLeftX = g.getClipBounds().x;
        int upperLeftY = g.getClipBounds().y;
        int visibleWidth = g.getClipBounds().width;
        int visibleHeight = g.getClipBounds().height;

        // Compute a position on the panel that is above and to the left of the currently
        //   exposed view.

        int firstX = Math.max(0, upperLeftX - upperLeftX % 100 - 100);
        int firstY = Math.max(0, upperLeftY - upperLeftY % 100 - 100);

        // Compute the last possible position of a coordinate in the visible space.

        int lastX = (upperLeftX + visibleWidth) - (upperLeftX + visibleWidth) % 100 + 200;
        int lastY = (upperLeftY + visibleHeight) - (upperLeftY + visibleHeight) % 100 + 200;

        // Ignore the fact that most of our panel is invisible.  Just draw everything.

        // Clear the background.

        g.setColor(Color.WHITE);
        g.fillRect(firstX, firstY, lastX - firstX, lastY - firstY);

        // Draw coordinates every 100x100 pixels.

        g.setColor(Color.BLACK);
        for (int y = firstY; y <= lastY; y += 100)
            for (int x = firstX; x <= lastX; x += 100) {
                g.fillOval(x - 2, y - 2, 5, 5);
                g.drawString("(" + x + "," + y + ")", x + 2, y);
            }

    }

    /**
     * Adjusts the scroll pane's view by an amount equal to the mouse motion.
     */
    public void mouseDragged(MouseEvent e) {
        // Compute the offset from the last mouse location.

        int deltaX = e.getX() - lastMouseX;
        int deltaY = e.getY() - lastMouseY;

        // Adjust the scroll pane by the delta.  Note that to move the
        //   surface with the mouse, we must move the view in the opposite
        //   direction.  This means that we subtract the delta.

        JViewport view = enclosingPane.getViewport();
        Point pos = view.getViewPosition();
        view.setViewPosition(new Point(pos.x - deltaX, pos.y - deltaY));

        // Keep track of the last mouse location.  Note:  Because we moved
        //   the view, the logical location of the mouse moved an equal amount
        //   in the same direction.

        lastMouseX = e.getX() - deltaX;
        lastMouseY = e.getY() - deltaY;

        // Make sure the JViewPort fully repaints.  This will make the 
        // out-of-bounds area appear all gray instead of giving a
        // torn appearance.

        view.repaint();
    }

    /**
     * Keeps track of the last mouse position.
     */
    public void mousePressed(MouseEvent e) {
        // Keep track of the last mouse location.

        lastMouseX = e.getX();
        lastMouseY = e.getY();
    }

    // Unused event handlers

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}