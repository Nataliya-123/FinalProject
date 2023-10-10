package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateTask implements Action {

    @Override
    public void actionWithTasks() {
        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatementInsertTask = connection.prepareStatement(INSERT_TASK)) {
            System.out.println("Введите название задачи, которую хотите запланировать");
            String taskName = scanner.next();
            preparedStatementInsertTask.setString(1, taskName);
            preparedStatementInsertTask.executeUpdate();
            System.out.println("Задача создана со статусом 'new'");

            PreparedStatement preparedStatementSelectTaskById = connection.prepareStatement(SELECT_TASK_BY_NAME);
            preparedStatementSelectTaskById.setString(1, taskName);
            ResultSet resultSetSelectTask = preparedStatementSelectTaskById.executeQuery();
            while (resultSetSelectTask.next()) {
                System.out.println("Создана новая задача: "+ resultSetSelectTask.getInt("id") + ". Наименование: '" +
                        resultSetSelectTask.getString("name") + "'  статус:  '" +
                        resultSetSelectTask.getString("state") + "'");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

