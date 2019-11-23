package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  // функция вовращает список имен полученных персон
  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    // 0 - фальшивая персона для отладки, не возвращаем.
    persons.remove(0);
    return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
  }

  // функция вовращает саписок различных имен персон
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().distinct().collect(Collectors.toSet());
  }

  // функция возврщает полное имя персоны
  public String convertPersonToString(Person person) {
    /*Улучшаем читамемость
    * с использованием тернарного оператора
    * и исключаем повторную проверку
    * */
    String result = "";
    result += (person.getSecondName() != null) ? person.getSecondName() : "";
    result += (person.getFirstName() != null) ? person.getFirstName() : "";
    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    /* упростим синтаксим с использованием потоков */
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString,
                    (oldValue, newValue) -> oldValue
            ));
  }

  // функция проверки коллекций на наличие совпадающих персон
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    /* заменим проверку через массивы на проверку через stream*/
    return persons1.stream()
            .anyMatch(persons2::contains);
  }

  //Функция возвращает количество четных чисел в потоке
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
    return count;
  }

  @Override
  public boolean check() {
    //System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
