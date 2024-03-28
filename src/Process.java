public class Process {
    String processName;
    int processRunTime;
    String[] processNames;

    public Process(String processName, int processRunTime, String[] processNames) {
        this.processName = processName;
        this.processRunTime = processRunTime;
        this.processNames = processNames;
    }

    public String getProcessName() {
        return processName;
    }

    public int getProcessRunTime() {
        return processRunTime;
    }

    public void setProcessRunTime(int processRunTime) {
        this.processRunTime = processRunTime;
    }

    public String[] getProcessNames() {
        return processNames;
    }
}
