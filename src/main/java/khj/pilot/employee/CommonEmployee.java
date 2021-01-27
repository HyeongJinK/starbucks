package khj.pilot.employee;

public abstract class CommonEmployee  implements Employee{
    protected String name;
    protected int processingTime;

    protected CommonEmployee(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public int getMillisProcessingTime() {
        return processingTime * 1000;
    }
}
