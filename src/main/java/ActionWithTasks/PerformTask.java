package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformTask implements Action {

    @Override
    public void actionWithTasks() {

        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatementUpdateState = connection.prepareStatement(UPDATE_STATE)) {
            System.out.println("Введите номер задачи , которую хотите выполнить");
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
                System.out.println("Чтобы выполнить задачу,  возьмите её в работу");
            }
            if (state.equals("in process")) {
                preparedStatementUpdateState.executeUpdate();
                System.out.println("Задача выполнена и переведена в статус 'done'");
                PreparedStatement preparedStatementSelectTaskById = connection.prepareStatement(SELECT_TASK_BY_ID);
                preparedStatementSelectTaskById.setInt(1, taskId);
                ResultSet resultSetSelectTask = preparedStatementSelectTaskById.executeQuery();
                while (resultSetSelectTask.next()) {
                    System.out.println(resultSetSelectTask.getInt("id") + ". Наименование: '" +
                            resultSetSelectTask.getString("name") + "'  статус:  '" +
                            resultSetSelectTask.getString("state") + "'");
                }
            } else {
                System.out.println("Данная задача  уже выполнена");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
