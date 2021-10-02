import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PersonIteratorOverCourseTests {

    /**
     * Проверяет методы next() и hasNext().
     */
    @Test
    public void courseIterator_nextTest () {
        SchoolJournal journal = new SchoolJournal();
        Iterator<Person> itr = journal.courseIterator(1);

        journal.addPerson(new Person("Иван", "Петров", 1));
        journal.addPerson(new Person("Петр", "Иванов", 2));
        journal.addPerson(new Person("Сергей", "Белов", 12));
        journal.addPerson(new Person("Максим", "Светлов", 1));

        Assert.assertTrue(itr.hasNext());
        Assert.assertEquals("Иван", itr.next().getFirstName());
        Assert.assertEquals("Максим", itr.next().getFirstName());

        Throwable throwable = Assertions.assertThrows(NoSuchElementException.class, itr::next);
        Assertions.assertEquals("Некорректное использование итератора", throwable.getMessage());
    }

    /**
     * Проверяет метод remove().
     */
    @Test
    public void courseIterator_removeTest () {
        SchoolJournal journal = new SchoolJournal();
        Iterator<Person> itr = journal.courseIterator(1);

        journal.addPerson(new Person("Иван", "Петров", 1));
        journal.addPerson(new Person("Петр", "Иванов", 2));
        journal.addPerson(new Person("Сергей", "Белов", 12));
        journal.addPerson(new Person("Максим", "Светлов", 1));

        Throwable throwable = Assertions.assertThrows(IllegalStateException.class, itr::remove);
        Assertions.assertEquals("Некорректное использование итератора", throwable.getMessage());

        itr.next();
        itr.remove();
        itr.next();
        itr.remove();

        throwable = Assertions.assertThrows(IllegalStateException.class, itr::remove);
        Assertions.assertEquals("Некорректное использование итератора", throwable.getMessage());
    }

}
