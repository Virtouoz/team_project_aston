# Сортировка студентов с паттерном Strategy

**Java 17 + Maven**  
**Команда из 5 человек** · Aston · 2025

[![Java 17](https://img.shields.io/badge/Java-17-blue)](https://openjdk.java.net/projects/jdk/17/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-green)](https://maven.apache.org/)

## Задачи (To Do)

| Задача                                   | Ответственный    | Статус     | 
|------------------------------------------|------------------|------------|
| Добавить валидацию в Builder             | Андрей Белоус    | Not Done   | 
| Реализовать 9 стратегий сортировки       | Андрей Белоус    | Not Done   |
| Кастомная коллекция `CustomArrayList<E>` | Елена Мусийчук   | Not Done   |
| Чтение/запись в файл + append            | Даниил Тиханкин  | Not Done   |
| Многопоточный поиск элемента             |                  | Not Done   |
| Доп.задание 1 (чёт/нечёт по зачётке)     | Владимир Сиваев  | Not Done   |
| Главное меню + переключение стратегий    |                  | Not Done   |
| Защитить ветку main                      | Александр Клюцук | Done       |
| Сделать GitHub Projects                  |                  | Not Done   |
| Написать тесты (ручные + JUnit)          |                  | Not Done   |
| Финальный README + скриншоты             |                  | InProgress |

## Команда

| Имя                  | Роль      | GitHub | Основной вклад |
|----------------------|-----------|--------|----------------|
| **Клюцук Александр** | Team Lead | @alexk | -              |
| **Добавить себя**    |           | @      |                |

## Как запустить

```bash
# 1. Собрать проект (генерируются Builder'ы автоматически)
mvn clean compile

# 2. Запустить
mvn exec:java -Dexec.mainClass=com.learn.Main