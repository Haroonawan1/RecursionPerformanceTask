import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProcessProcessor {
    ArrayList<Process> processes;

    public ProcessProcessor(String fileName) {
        processes = processFile(fileName);
    }

    public int processProcessRuntime(String processName) {
        Process process = findProcess(processName);
        ArrayList<Process> queue = new ArrayList<>(Collections.singletonList(process));
        int totalRunTime = process.getProcessRunTime();
        process.setProcessRunTime(-1);
        if (process.getProcessNames() != null) {
            for (String name : process.getProcessNames()) {
                if (queue.get(0).getProcessRunTime() == -1) {
                    queue.remove(0);
                }
                queue.add(findProcess(name));
                totalRunTime += processProcessRuntime(queue.get(0).getProcessName());
            }
        }
        return totalRunTime;
    }

    public Process findProcess(String processName) {
        for (Process process : processes) {
            if (process.getProcessName().equals(processName)) {
                return process;
            }
        }
        return null;
    }

    public  ArrayList<Process> processFile(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine()) {
            fileData.add(s.nextLine());
        }

        ArrayList<Process> processes = new ArrayList<>();
        for (String process : fileData) {
            String name = process.substring(0, process.indexOf("(") - 1);
            int runTime = Integer.parseInt(process.substring(process.indexOf("(") + 1, process.indexOf(")")));
            String[] processNames = null;
            if (process.contains("->")) {
                processNames = process.substring(process.indexOf(">") + 1).split(",");
                for (int j = 0; j < processNames.length; j++) {
                    processNames[j] = processNames[j].substring(1);
                }
            }
            processes.add(new Process(name, runTime, processNames));
        }
        return processes;
    }
}