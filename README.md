## ScanFolder

#### Содержание:
<a name="zeroDot"></a>

* [Начало работы](#start)
* [Что используется в модуле](#ServiceLogic)
* [Сервис автосканирования](#autoScan)
* [Логика работы autoScan](#autoScan_logic)
* [Ручная проверка папки](#manualScan)
* [Настройка модуля](#set)
* [Работа модуля](#work)
* [Ссылка на файлы](#link)
* [Координаты модуля](#coord)

---



#### Начало работы
<a name="start"></a>

Есть зависимость от `https://github.com/svgetm/logservice`

Сервис автосканирования не запускается при старте приложения.
Все [настройки](#set) производятся в одном месте.  

**ВАЖНО:** 
- Приложение стартует с дефолтным путем, который указан в настройках - `~`. Путь для сканирования необходимо поменять в [настройках](#set)

**ВАЖНО:**
Корректный путь до расшаренной папки выглядит так: ` \\toquads\share\ `

- Максимальное количество файлов которе сервис может обработать = ± 200 штук, на практике до 250 файлов было без ошибочно.
  Если вдруг файлов поступит больше, то все они будут обработаны вызванным ручным сканером, но наблюдается "каша" в логах.


<small>[в начало](#zeroDot)</small>

---

#### Содержание сервисов в модуле
<a name="ServiceLogic"></a> 

В данном модуле имеется следующие сервисы:
- Сервисы сканирования: автосканирование выбранной папки и ручное сканирование выбранной папки
- Сервис настройки: отвечает за указания данных для корректной работы сервиса, по типо путь к папке, дата обновления
  папки.


<small>[в начало](#zeroDot)</small>

--------------------------------------------------

#### Сервис автосканирования
<a name="autoScan"></a>

Автосканирование, постоянно отлеживает нужыне нам изменения в выбранной директории.

У данного сервиса имеется 2 события на которые он реагирует:
- Создание файла
- Модификация файла

Было принято решение не реагировать на событие "удаление", данная информация не актуальна в использование.


<small>[в начало](#zeroDot)</small>

------------------------

#### Логика автоскана
<a name="autoScan_logic"></a>

* При создании файла - сервис проверяет наличие сведений о файле в БД:
  - Сведения в БД не найдены - добавляет данные в БД.
  - Если сведения уже есть в БД - отправляется Log, что БД уже содержит информацию о этом файле
  
* При событии модификация файла:  
  Отправляется Log, что БД уже содержит информацию о этом файле

* При событии файл удален - действий не происходит.

Файлы логирования создают в той же папке, которую мы мониторим.


<small>[в начало](#zeroDot)</small>

--------------------------------------------------

#### Ручное сканирование
<a name="manualScan"></a> 

Логика сервиса аналогичная автосканированию, но в отличии от него, делается проверка по нажатию кнопки (расположена она над таблицей, красного цвета),
так же список файлов выводится на экран в таблицу. Папка сканирования меняется все там же - [настройки](#set)

**ВАЖНО:** при удалении файлов в папке - не происходит удаление данных из БД, т.е. на экране мы будем видеть все файлы,
которые есть в БД, но могли быть удалены из папки.


<small>[в начало](#zeroDot)</small>

--------------------------------------------------

#### Выбор папки сканирования
<a name="set"></a>

Компонент который отвечает за сервис мониторинга реализовывает паттерн prototype, мы можем создавать сколько угодно экземпляров,
которые будут мониторить папки в отдельных потоках.

Здесь есть 4 поля:
- Путь папки
- Дата последнего обновления папки
- Булево на активность.  
- Список расширений файлов на которые мы будем реагировать
- Список неймов файлов которые мы будем игнорировать

Для изменения папки сканирования необходимо выделить строку с данными, после чего нажать edit и внести новые данные.
При установки новой директории необходимо так же будет выбрать
время последнего обновления - установить можно текущую дату и время, когда происходит изменения пути папки.

По дефолту путь установлен на `~` - для отладки работы модуля

После создания экземпляра с новыми данными, необходимо запустить скан по кнопке, важно что бы булево активности было в тру.


<small>[в начало](#zeroDot)</small>

--------------------------------------------------
#### Работа модуля
<a name="work"></a>

1.  При старте приложения запускается сервис autoScan. И при событии - добавление данных в БД, 
    он обновляет на экране таблицу и показывает уведомление - таблица обновлена, при условии, что данный экран открыт у пользователя.
2.  На назначенную задачу вешается метод ручного скана и раз в сутки проверяются все файлы в папке. (на случай если
    отвалился сервис автоскана)
3.  Реализована резервная возможность проверить файлы в папке - кнопка "Обновить список"
4.  На экране установлен таймер обновления таблицы - раз в минуту.
5.  Так же можно обновить таблицу руками - перезапустив браузер с окном файлов.
6.  Логирование:
    * Если импорт прошел успешно, то в этой же папке создается файл txt с сообщением "Данные успешно импортированы " :
`file.getName + дата_время`.
    * Если импорт прошел НЕ успешно, то в этой же папке создается файл txt с логом ошибок:
`file.getName + дата_время_Ошибка`.
    * Если файл был изменен: Система присылает лог `файл с таким именем уже существует`.
    * При удаление никаких действий не происходит. 


<small>[в начало](#zeroDot)</small>

--------------------------------------------------

#### Ссылка на файл
<a name="link"></a>

У сущности есть атрибут "ссылка", который храниться в последнем столбце таблицы.  
При работе на OS Windows при нажатии открывается файловый менеджер "проводник" и выделяет нужный нам файл.


<small>[в начало](#zeroDot)</small>

--------------------------------------------------
#### Координаты модуля
<a name="coord"></a>

 `ru.spacecorp.getmanenko.scanfolder:scanFolder-global:2.2-SNAPSHOT`
 
 
<small>[в начало](#zeroDot)</small>
