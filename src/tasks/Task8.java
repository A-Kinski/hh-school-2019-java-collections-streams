package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
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

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    // 0 - фальшивая персона для отладки, не возвращаем.
    // вместо remove из-за возжной неизменяемости перданного парметра применяем skip
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  // функция вовращает саписок различных имен персон
  public Set<String> getDifferentNames(List<Person> persons) {
    //отказываемся от stream  в пользу Set - в множествах нет повторяющихся элементов
    return new HashSet<>(getNames(persons));
  }

  // функция возврщает полное имя персоны
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getMiddleName(), person.getFirstName())
            .filter(name -> name != null)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    /* упростим синтаксис с использованием потоков
    * теперь не нужно создавать новую Map, она создается автоматом
    * и не нужно проверять на дублирование ключей
    * (коллизи автоматически решаются, оставляя старое значение)
    * */
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString,
                    (oldValue, newValue) -> oldValue
            ));
  }

  // функция проверки коллекций на наличие совпадающих персон
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    /*Если я правильно посчитал, то new HashSet и  addAll отработают за O(n)
    * (так как HashSet.add() отрабатывает за O(1))
    * Таким образом ассимптотика всей функции O(n)*/
    HashSet<Person> personHashSet = new HashSet<>(persons1);
    personHashSet.addAll(persons2);
    if (persons1.size() + persons2.size() != personHashSet.size()) return true;
    else return false;
  }

  //Функция возвращает количество четных чисел в потоке
  public long countEven(Stream<Integer> numbers) {
    // убрали переменную count чтобы избежать пересечения с полем класса
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    //System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
