import java.util.*;

class Process {
    int id;
    boolean isActive;

    Process(int id) {
        this.id = id;
        this.isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public void activate() {
        isActive = true;
    }
}

public class BullyAlgorithm {
    private List<Process> processes;
    private int coordinatorId;

    public BullyAlgorithm(int n) {
        processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processes.add(new Process(i + 1));
        }
        coordinatorId = n;  // Initially, highest ID is the leader
    }

    public void startElection(int initiatorId) {
        System.out.println("\nProcess " + initiatorId + " has started an election.");
        boolean higherProcessAlive = false;

        for (Process p : processes) {
            if (p.id > initiatorId && p.isActive) {
                System.out.println("Process " + initiatorId + " sends election message to " + p.id);
                System.out.println("Process " + p.id + " responds OK to " + initiatorId);
                higherProcessAlive = true;
                startElection(p.id);  // Recursive call: higher process starts election
                break;
            }
        }

        if (!higherProcessAlive) {
            coordinatorId = initiatorId;
            System.out.println("Process " + initiatorId + " becomes the new Coordinator!");
        }
    }

    public void crashProcess(int id) {
        processes.get(id - 1).deactivate();
        System.out.println("Process " + id + " has crashed.");
    }

    public void recoverProcess(int id) {
        processes.get(id - 1).activate();
        System.out.println("Process " + id + " has recovered.");
        startElection(id);
    }

    public static void main(String[] args) {
        BullyAlgorithm bully = new BullyAlgorithm(5);

        System.out.println("Initial Coordinator: Process 5");
        bully.crashProcess(5);

        bully.startElection(2);

        bully.recoverProcess(5);
    }
}
