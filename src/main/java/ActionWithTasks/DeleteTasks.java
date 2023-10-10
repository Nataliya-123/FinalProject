package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteTasks implements Action {

    @Override
    public void actionWithTasks() {

        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatementDeleteTask = connection.prepareStatement(DELETE_TASK)) {
            System.out.println("Введите номер задачи , которую хотите  удалить");
            int taskId = scanner.nextInt();
            preparedStatementDeleteTask.setInt(1, taskId);
            PreparedStatement preparedStatementSelectName = connection.prepareStatement(SELECT_NAME_BY_ID);
            preparedStatementSelectName.setInt(1, taskId);
            String name = null;
            ResultSet resultSet = preparedStatementSelectName.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            if (name == null) {
                System.out.println("Такой задачи нет или она уже удалена");
            } else {
                preparedStatementDeleteTask.executeUpdate();
                System.out.println("Задача удалена");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

