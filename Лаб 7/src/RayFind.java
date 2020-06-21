import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RayFind {
    private JMapCell[][] mapCells;
    /** The number of grid cells in the X direction. **/
    private int width;

    /** The number of grid cells in the Y direction. **/
    private int height;

    /** The location where the path starts from. **/
    private Location startLoc;

    /** The location where the path is supposed to finish. **/
    private Location finishLoc;

    private class MapCellHandler implements MouseListener
    {
        /**
         * This value will be true if a mouse button has been pressed and we are
         * currently in the midst of a modification operation.
         **/
        private boolean modifying;

        /**
         * This value records whether we are making cells passable or
         * impassable.  Which it is depends on the original state of the cell
         * that the operation was started within.
         **/
        private boolean makePassable;

        /** Initiates the modification operation. **/
        public void mousePressed(MouseEvent e)
        {
            modifying = true;

            JMapCell cell = (JMapCell) e.getSource();

            // If the current cell is passable then we are making them
            // impassable; if it's impassable then we are making them passable.

            makePassable = !cell.isPassable();

            cell.setPassable(makePassable);
        }

        /** Ends the modification operation. **/
        public void mouseReleased(MouseEvent e)
        {
            modifying = false;
        }

        /**
         * If the mouse has been pressed, this continues the modification
         * operation into the new cell.
         **/
        public void mouseEntered(MouseEvent e)
        {
            if (modifying)
            {
                JMapCell cell = (JMapCell) e.getSource();
                cell.setPassable(makePassable);
            }
        }

        /** Not needed for this handler. **/
        public void mouseExited(MouseEvent e)
        {
            // This one we ignore.
        }

        /** Not needed for this handler. **/
        public void mouseClicked(MouseEvent e)
        {
            // And this one too.
        }
    }

    public RayFind(int w, int h) {
        if (w <= 0)
            throw new IllegalArgumentException("w must be > 0; got " + w);

        if (h <= 0)
            throw new IllegalArgumentException("h must be > 0; got " + h);

        width = w;
        height = h;

        startLoc = new Location(2, h / 2);
        finishLoc = new Location(w - 3, h / 2);

        initGUI();
    }

    private void initGUI()
    {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());

        // Use GridBagLayout because it actually respects the preferred size
        // specified by the components it lays out.

        GridBagLayout gbLayout = new GridBagLayout();
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 1;
        gbConstraints.insets.set(0, 0, 1, 1);

        JPanel mapPanel = new JPanel(gbLayout);
        mapPanel.setBackground(Color.GRAY);

        mapCells = new JMapCell[width][height];

        MapCellHandler cellHandler = new MapCellHandler();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                mapCells[x][y] = new JMapCell();

                gbConstraints.gridx = x;
                gbConstraints.gridy = y;

                gbLayout.setConstraints(mapCells[x][y], gbConstraints);

                mapPanel.add(mapCells[x][y]);
                mapCells[x][y].addMouseListener(cellHandler);
            }
        }

        contentPane.add(mapPanel, BorderLayout.CENTER);

        JButton findPathButton = new JButton("Find Path");
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { findAndShowPath(); }
        });

        contentPane.add(findPathButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        mapCells[startLoc.xCoord][startLoc.yCoord].setEndpoint(true);
        mapCells[finishLoc.xCoord][finishLoc.yCoord].setEndpoint(true);
    }

    private void findAndShowPath() {
        timeSet();
        Location pos = startLoc;
        boolean activ=true;

        System.out.println(mapCells[0][0].isPassable());

         while (activ){
            double minDist = Double.MAX_VALUE;
            Location newLoc=pos;
            for (int i=-1;i<=1;i+=2){
                if (mapCells[newLoc.xCoord+i][newLoc.yCoord].isPassable() && !mapCells[newLoc.xCoord+i][newLoc.yCoord].path) {
                    double dist = newLoc.addCoord(i, 0).gipotenuza(finishLoc.xCoord, finishLoc.yCoord);
                    if (dist < minDist) {
                        minDist = dist;
                        pos = newLoc.addCoord(i, 0);
                    }
                }
                if (mapCells[newLoc.xCoord][newLoc.yCoord+i].isPassable() && !mapCells[newLoc.xCoord][newLoc.yCoord+i].path) {
                    double dist = newLoc.addCoord(0, i).gipotenuza(finishLoc.xCoord, finishLoc.yCoord);
                    if (dist < minDist) {
                        minDist = dist;
                        pos = newLoc.addCoord(0, i);
                    }
                }
            }

            mapCells[pos.xCoord][pos.yCoord].setPath(true);
            if (minDist==Double.MAX_VALUE) activ=false;
            if (pos.equals(finishLoc)) activ=false;
        }
        timeSet();

    }

    public static void main(String[] args) {
        RayFind rayFind = new RayFind(10,10);
    }

    static long timer=0;
    public static void timeSet(){
        if (timer==0) timer=System.nanoTime();
        else {
            double val = (double)(System.nanoTime()-timer);
            System.out.println(val/1000000);
            timer=0;
        }
    }


}
