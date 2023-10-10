package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTaskList implements Action {

    @Override
    public void actionWithTasks() {
        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FROM_TASK)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ". Наименование: '" +
                        resultSet.getString("name") + "'  статус:  '" +
                        resultSet.getString("state") + "'");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
}
