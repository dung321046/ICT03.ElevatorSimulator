package views.graphics;

import java.awt.Color;
import java.awt.Graphics;
import controllers.MainController;
import static java.lang.Math.max;

public class FixedFloor extends FixedObject  {

    public final static int FLOOR_WIDTH = max(
         (int) ((MainController.getInstance().getBuilding().getPassengers().size() / MainController.getInstance().getBuilding().getFloorCountWithGround()) * (AnimatedPerson.PERSON_WIDTH * 1.7)), 180);
    private final int id;

    public FixedFloor(int x, int y, int id) {
        super(x, y);
        this.id = id;
        color = (id % 2 == 0) ? new Color(230, 230, 230) : new Color(210, 210, 210);
    }

    public void drawYourself(Graphics g) {
        if (g != null) {
            g.setColor(color);
            g.fillRect(x, y, FLOOR_WIDTH, AnimatedElevator.ELEVATOR_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawString(id == 0 ? "RDC" : "Floor " + id, x + FLOOR_WIDTH / 4, y + AnimatedElevator.ELEVATOR_HEIGHT / 3);
        }
    }

}
