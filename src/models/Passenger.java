package models;

import views.graphics.SimulationPanel;
import controllers.MainController;

/**
 *
 * @author x_nem
 */
public abstract class Passenger {

    protected MainController controller;

    // personal counter
    protected long beginTime;
    protected Elevator elevator;
    protected int currentFloor;
    protected int wantedFloor;

    /**
     * Constructor Passenger with two runs in parameter
     *
     * @param current_floor the floor or the passenger is currently
     * @param wanted_floor upstairs where he wants to go
     */
    public Passenger(int current_floor, int wanted_floor) {
        this.controller = MainController.getInstance();
        this.currentFloor = current_floor % this.controller.getBuilding().getFloorCountWithGround();
        this.wantedFloor = wanted_floor % this.controller.getBuilding().getFloorCountWithGround();
        this.elevator = null;
        resetTime();
    }

    public abstract int getTotalMass();

    public abstract int getPersonCount();

    public abstract boolean canEnterElevator(Elevator elevator);

    public Elevator getElevator() {
        return elevator;
    }

    // Definit dans quel ascenseur l'individu se trouve
    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public boolean isArrived() {
        return (wantedFloor == currentFloor) && !isInTheElevator();
    }

    public boolean isInTheElevator() {
        return elevator != null;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        currentFloor = floor;
    }

    public long getTime() {
        return ((System.currentTimeMillis() - beginTime) / 1000) * SimulationPanel.framePerSecond;
    }

    public int getWantedFloor() {
        return wantedFloor;
    }

    public boolean isWaitingAtFloor(int floor) {
        return !isArrived() && !isInTheElevator() && currentFloor == floor;
    }

    public boolean isArrivedAtFloor(int floor) {
        return isArrived() && currentFloor == floor;
    }

    public final void resetTime() {
        beginTime = System.currentTimeMillis();
    }

    /**
     * 1 : ok, 2 : a bit angry, 3 : very angry
     *
     * @return
     */
    public int getMood() {
        if (isArrived()) {
            return 1;
        }
        if (getTime() > 180) {
            return 3;
        } else if (getTime() > 60) {
            return 2;
        } else {
            return 1;
        }
    }

}
