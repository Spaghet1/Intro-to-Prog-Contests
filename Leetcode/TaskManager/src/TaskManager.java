import java.util.*;

public class TaskManager {



    /*PriorityQueue<List<Integer>> priorityQueue;

    public TaskManager(List<List<Integer>> tasks) {
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt((List<Integer> a) -> -a.get(2)).thenComparingInt( a -> a.get(2)));
        priorityQueue.addAll(tasks);
    }

    public void add(int userId, int taskId, int priority) {
        List<Integer> list = new ArrayList<>(3);
        list.add(userId);
        list.add(taskId);
        list.add(priority);
        priorityQueue.add(list);
    }

    public void edit(int taskId, int newPriority) {
        Iterator<List<Integer>> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            List<Integer> list = iterator.next();
            if (list.get(1) == taskId) {
                List<Integer> newList = new ArrayList<>(list);
                newList.set(2, newPriority);
                iterator.remove();
                priorityQueue.add(newList);
                return;
            }

        }
    }

    public void rmv(int taskId) {
        Iterator<List<Integer>> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            List<Integer> list = iterator.next();
            if (list.get(1) == taskId) {
                iterator.remove();
            }
        }
    }

    public int execTop() {
        if (priorityQueue.isEmpty()) {
            return -1;
        }
        return priorityQueue.poll().getFirst();
    } */

    static class Task implements Comparable<Task> {
        int userID, taskID, priority;

        Task(int userID, int taskID, int priority) {
            this.userID = userID;
            this.taskID = taskID;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task o) {
            int compare = Integer.compare(o.priority, this.priority);
            return (compare != 0) ? compare : Integer.compare(o.taskID, this.taskID);
        }
    }

    private PriorityQueue<Task> pq = new PriorityQueue<>();
    private Map<Integer, Task> latest = new HashMap<>();

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> list : tasks) {
            add(list.getFirst(), list.get(1), list.getLast());
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task t = new Task(userId, taskId, priority);
        pq.add(t);
        latest.put(taskId, t);
    }

    public void edit(int taskId, int newPriority) {
        Task latestTask = latest.get(taskId);
        if (latestTask == null) return; // not found
        add(latestTask.userID, taskId, newPriority); // just add new version
    }

    public void rmv(int taskId) {
        latest.remove(taskId); // mark as removed (lazy delete)
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            Task top = pq.poll();
            Task valid = latest.get(top.taskID);
            if (valid == top) { // still the latest version
                latest.remove(top.taskID);
                return top.userID;
            }
            // else skip stale task
        }
        return -1;
    }
}
