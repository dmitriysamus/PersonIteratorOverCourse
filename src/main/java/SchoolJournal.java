import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * School journal.
 */
public class SchoolJournal {

    private class PersonIteratorOverCourse implements Iterator<Person> {

        int courseNumber;
        int cursor;
        int lastNext = -1;

        public PersonIteratorOverCourse(int courseNumber) {
            this.courseNumber = courseNumber;
            this.cursor = 0;
        }

        /**
         * Проверяет наличие следующего объекта того же курса.
         */
        @Override
        public boolean hasNext() {
            if (cursor != persons.size()) {
                if (search(cursor) != -1) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Возвращает позицию следующего объекта того же курса.
         */
        private int search(int cursor) {
            for (int i = cursor; i < persons.size(); i++) {
                if (persons.get(i).getCourse() == courseNumber) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Итерируется к следующему объекту того же курса.
         */
        @Override
        public Person next() {
            if (cursor > persons.size()) {
                throw new NoSuchElementException("Некорректное использование итератора");
            }

            int i = search(cursor);
            if (i == -1) {
                throw new NoSuchElementException("Некорректное использование итератора");
            }

            Person prevPerson = persons.get(i);
            lastNext = i;
            cursor = i + 1;
            return prevPerson;

        }

        /**
         * Удаляет объект, который вернул последний вызов next().
         */
        @Override
        public void remove() {
            if (lastNext < 0 || lastNext > persons.size())
                throw new IllegalStateException("Некорректное использование итератора");

            persons.remove(lastNext);
            cursor = lastNext;
            lastNext = -1;
        }
    }

    private List<Person> persons;

    public SchoolJournal() {
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public Iterator<Person> courseIterator(int courseNumber) {
        return new PersonIteratorOverCourse(courseNumber);
    }
}
