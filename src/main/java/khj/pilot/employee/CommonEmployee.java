package khj.pilot.employee;

public abstract class CommonEmployee  implements Employee{
    protected String name;
    protected int processingTime;

    protected CommonEmployee(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
    }

    protected String getName() {
        return this.name;
    }
}
