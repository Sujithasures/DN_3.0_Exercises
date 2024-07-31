class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    // Getters
    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class SinglyLinkedList {
    private TaskNode head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Method to add a task
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Method to traverse and print all tasks
    public void traverseTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Method to delete a task by taskId
    public boolean deleteTask(String taskId) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId().equals(taskId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false; // Task not found
    }
}
public class TaskManagementSystem {
    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();

        // Adding tasks
        taskList.addTask(new Task("T001", "Task 1", "Pending"));
        taskList.addTask(new Task("T002", "Task 2", "Completed"));
        taskList.addTask(new Task("T003", "Task 3", "In Progress"));

        // Traverse and print all tasks
        System.out.println("All Tasks:");
        taskList.traverseTasks();

        // Search for a task
        System.out.println("\nSearch Task T002:");
        Task task = taskList.searchTask("T002");
        System.out.println(task != null ? task : "Task not found");

        // Delete a task
        System.out.println("\nDeleting Task T003:");
        taskList.deleteTask("T003");
        taskList.traverseTasks();
    }
}

