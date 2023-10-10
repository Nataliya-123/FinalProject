package ActionWithTasks;

import java.util.Scanner;

public interface Action {

    String DELETE_ALL_FROM_TASK = "DELETE from task.task";
    String INSERT_TASK = "INSERT INTO task.task (name, state) VALUES(?, 'new')";
    String SELECT_TASK_BY_NAME = "Select * from task.task where name = ?";
    String GET_ALL_FROM_TASK = "Select * from task.task";
    String DELETE_TASK = "DELETE from task.task  where id = ?";
    String SELECT_NAME_BY_ID = "Select name from task.task where id = ?";
    String UPDATE_STATE_IN_PROCESS = "Update task.task set state = 'in process' where id = ?";
    String SELECT_STATE_OF_TASK = "Select state from task.task where id = ?";
    String SELECT_TASK_BY_ID = "Select * from task.task where id = ?";
    String UPDATE_STATE = "Update task.task set state = 'done' where id = ?";

    Scanner scanner = new Scanner(System.in);

    void actionWithTasks();
}
