# Project_Mimir 
# Проект по SQL

__ЮФУ ФизФак | Кафедра Нанотехнологий | 2020г__

**Задание**

Задача 8. _Учет аудиторного фонда университета._

>Программа должна содержать следующую информацию об аудиторном фонде университета. Наименование корпуса, в котором расположено помещение, номер комнаты, расположение комнаты в корпусе, ширина и длина комнаты в метрах, назначение и вид помещения, подразделение университета, за которым закреплено помещение. В программе также должна содержаться информация о высоте потолков в помещениях в зависимости от места расположения помещений в корпусе. Следует также учитывать, что структура подразделений университета имеет иерархический вид, когда одни подразделения входят в состав других (факультет, кафедра, лаборатория)..  
>Помимо SQL-запросов для создания таблиц базы данных, составьте запрос на создания представления (__VIEW__), в котором помимо приведенной выше информации присутствовали бы данные о площадях и объемах каждого помещения

**Сущности**
+ Корпус  
+ Комната  
+ Факультет  
+ Кафедра  
+ Лаборатория  

**Поля**

| Корпус       | Комната            | Факультет     | Кафедра          | Лаборатория    |
| ---          | ---                | ---           | ---              | ---            |
| Наименование | Номер              | Название      | Название         | Кафедра        |
| ® Комнаты    | Ширина и длина     | Декан         | Зав. Кафедрой    | Ответственный  |
|              | Назначение         | ® Кафедры     | Специализация    | ® Комнаты      |
|              | Вид помещения      | ® Комнаты     | ® Факультет      | ® Кафедра      |
|              | Высота потолков    |               | ® Лаборатории    |                |
|              | Подразделение      |               | ® Комнаты        |                |
|              | Этаж               |               |                  |                |
|              | ® Корпус           |               |                  |                |
|              | ® Лаборатория      |               |                  |                |
|              | ® Факультет        |               |                  |                |
|              | ® Кафедра          |               |                  |                |

_® - связи (relationship)_

**Схема**

[СХЕМА](https://github.com/FiddleStoneComputerMagics/Project_Mimir/blob/master/docs/Scheme.pdf)

**Загрузки**

.........
