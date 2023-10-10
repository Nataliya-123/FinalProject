package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TakeTaskToWork implements Action {

    @Override
    public void actionWithTasks() {
        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatementUpdateState = connection.prepareStatement(UPDATE_STATE_IN_PROCESS)) {
            System.out.println("Введите номер задачи , которую хотите  взять в работу");
            int taskId = scanner.nextInt();
            preparedStatementUpdateState.setInt(1, taskId);
            PreparedStatement preparedStatementSelectState = connection.prepareStatement(SELECT_STATE_OF_TASK);
            preparedStatementSelectState.setInt(1, taskId);
            String state = null;

            ResultSet resultSetSelectState = preparedStatementSelectState.executeQuery();
            while (resultSetSelectState.next()) {
                state = resultSetSelectState.getString("state");
            }
            if (state.equals("new")) {
                preparedStatementUpdateState.executeUpdate();
                System.out.println("Задача переведена в статус 'in process' и взята в работу ");
                PreparedStatement preparedStatementSelectTaskById = connection.prepareStatement(SELECT_TASK_BY_ID);
                preparedStatementSelectTaskById.setInt(1, taskId);
                ResultSet resultSetSelectTask = preparedStatementSelectTaskById.executeQuery();
                while (resultSetSelectTask.next()) {
                    System.out.println(resultSetSelectTask.getInt("id") + ". Наименование: '" +
                            resultSetSelectTask.getString("name") + "'  статус:  '" +
                            resultSetSelectTask.getString("state") + "'");
                }
            } else {
                System.out.println("Задачу с этим статусом нельзя взять в работу");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
