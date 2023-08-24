import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForMeetingTopic() {
        Meeting meeting = new Meeting(1, "Тест", "Домашка", "Полсле полуночи");

        boolean expected = true;
        boolean actual = meeting.matches("ес");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForMeetingProject() {
        Meeting meeting = new Meeting(1, "Тест", "Домашка", "Полсле полуночи");

        boolean expected = true;
        boolean actual = meeting.matches("шка");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestQueryForMeetingStart() {
        Meeting meeting = new Meeting(1, "Тест", "Домашка", "Полсле полуночи");

        boolean expected = false;
        boolean actual = meeting.matches("ал");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestQueryForMeeting() {
        Meeting meeting = new Meeting(1, "Тест", "Текст", "Полсле полуночи");

        boolean expected = true;
        boolean actual = meeting.matches("Те");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForSimpleTaskTrue() {
        SimpleTask task = new SimpleTask(2, "Класс");
        boolean expected = true;
        boolean actual = task.matches("ас");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForSimpleTaskFalse() {
        SimpleTask task = new SimpleTask(2, "Класс");
        boolean expected = false;
        boolean actual = task.matches("ул");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForEpicTrue() {
        ;

        Epic task = new Epic(3, new String[]{"проснуться", "выйти из дома", "дойти до остоновки"});
        boolean expected = true;
        boolean actual = task.matches("до");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForEpicFalse() {
        ;

        Epic task = new Epic(3, new String[]{"проснуться", "выйти из дома", "дойти до остоновки"});
        boolean expected = false;
        boolean actual = task.matches("вов");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForAllClasses() {
        SimpleTask simple = new SimpleTask(5, "таракан");
        String[] subtasks = {"Прогулка", "Работа", "Отдых"};
        Epic tasks = new Epic(5, subtasks);
        Meeting meeting = new Meeting(5, "Вечеринка", "Выпускной", "Весна, май");

        Todos todos = new Todos();
        todos.add(simple);
        todos.add(tasks);
        todos.add(meeting);


        Task[] expected = {simple, tasks, meeting};
        Task[] actual = todos.search("а");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTestQueryForAllClassesFalse() {
        SimpleTask simple = new SimpleTask(5, "таракан");
        String[] subtasks = {"Прогулка", "Работа", "Отдых"};
        Epic tasks = new Epic(5, subtasks);
        Meeting meeting = new Meeting(5, "Вечеринка", "Выпускной", "Весна, май");
        Task[] task = {};

        Todos todos = new Todos();
        todos.add(simple);
        todos.add(tasks);
        todos.add(meeting);


        Task[] expected = task;
        Task[] actual = todos.search("круг");
        Assertions.assertArrayEquals(expected, actual);
    }

}

