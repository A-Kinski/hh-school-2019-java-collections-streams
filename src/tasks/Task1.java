package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис (он выдает несортированный Set<Person>)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */

/*
* Ассимптотика создания List - O(n)
* Ассимптотика создания mapPersons - также O(n)
* Таким образом общая ассимптотика функции O(n)
* */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);

    Map<Integer, Person> mapPersons = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity()));

    return personIds.stream()
            .map(id -> mapPersons.get(id))
            .collect(Collectors.toList());

  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
