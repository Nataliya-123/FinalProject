import ActionWithTasks.*;

import java.util.Scanner;

public class ActionWithTask {
    Scanner scanner = new Scanner(System.in);

    public void actionWithDifferentTask() {
        while (true) {
            System.out.println("****************************");
            System.out.println("Выберите действие: ");
            System.out.println("1. Показать список всех задач");
            System.out.println("2. Создать задачу ");
            System.out.println("3. Взять задачу в работу ");
            System.out.println("4. Выполнить задачу");
            System.out.println("5. Удалить задачу");
            System.out.println("6. Очистить список задач");
            System.out.println("7. Выйти ");
            System.out.println("*****************************");

            int command = scanner.nextInt();

            if (command == 1) {
                new GetTaskList().actionWithTasks();

            } else if (command == 2) {
                new CreateTask().actionWithTasks();

            } else if (command == 3) {
                new TakeTaskToWork().actionWithTasks();

            } else if (command == 4) {
                new PerformTask().actionWithTasks();

            } else if (command == 5) {
                new DeleteTasks().actionWithTasks();

            } else if (command == 6) {
                new DeleteAllTasks().actionWithTasks();
            } else if (command == 7) {
                System.exit(0);
            } else {
                System.err.println("Unknown command");
            }
        }
    }
}