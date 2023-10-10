package ActionWithTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllTasks implements Action {

    @Override
    public void actionWithTasks() {


        try (Connection connection = JdbcConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_FROM_TASK)) {
            System.out.println("Вы уверены , что хотите удалить все задачи?");
            System.out.println("ДА");
            System.out.println("НЕТ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Да")){
                preparedStatement.executeUpdate();
                System.out.println("Удалены все задачи");
            }else {
                System.out.println("Продолжаем работу");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

