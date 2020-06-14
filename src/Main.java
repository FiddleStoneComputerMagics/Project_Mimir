import Model.*;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            // Создаем экземпляр по работе с БFacultyDB facultyDB ;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите название таблицы с которой хотите работать!");
                System.out.println("Доступны следующие таблицы: faculties,departaments,workers,audits,buildings,rooms,roomsInfo ");
                String dbName = scanner.nextLine();
                if (dbName.equals("faculties")) {
                    FacultyDB facultyDB = FacultyDB.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить факультет ФизФак Иван Иванов Иванович'" + "\n"
                            + "'Удалить таблицу факультеты'" + "\n"
                            + "'Показать все факультеты'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Удалить таблицу факультеты")) {
                            facultyDB.delete();
                        } else if (otvet.equals("Показать все факультеты")) {
                            facultyDB.showAllUsers();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить факультет")){
                            String name = arrayList.get(3) + " " + arrayList.get(4) + " " + arrayList.get(5);
                            facultyDB.addFaculty(new Faculty(arrayList.get(2), name));
                        }
                    }
                } else if (dbName.equals("departaments")) {
                    DepartamentTable departamentTable = DepartamentTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить кафедру 1 Кафедра1 (1,2,4)'" + "\n"
                            + "'Удалить таблицу кафедры'" + "\n"
                            + "'Показать все кафедры'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Удалить таблицу кафедры")) {
                            departamentTable.delete();
                        } else if (otvet.equals("Показать все кафедры")) {
                            departamentTable.showAllDepartaments();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить кафедру")){
                            String name = arrayList.get(3);
                            Integer facultyID = Integer.valueOf(arrayList.get(2));
                            String arrays = arrayList.get(4).substring(1, arrayList.get(4).length() - 1);
                            departamentTable.addDepartament(new Departament(facultyID, name, arrays));
                        }
                    }
                } else if (dbName.equals("workers")) {
                    WorkerTable workerTable = WorkerTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить человека Иван Иванов преподаватель'" + "\n"
                            + "'Удалить таблицу людей'" + "\n"
                            + "'Показать всех людей'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Показать всех людей")) {
                            workerTable.showAllWorkers();
                        } else if (otvet.equals("Удалить таблицу людей")) {
                            workerTable.delete();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить человека")) {
                            String firstName = arrayList.get(2);
                            String lastName = arrayList.get(3);
                            String post = arrayList.get(4);
                            workerTable.addWorker(new Worker(firstName, lastName, post));
                        }
                    }
                } else if (dbName.equals("audits")) {
                    AuditTable auditTable = AuditTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить аудит 1 ИмяАудита ДатаАудита ВидАудита *рекомендации* '" + "\n"
                            + "'Удалить таблицу аудитов'" + "\n"
                            + "'Показать все аудиты'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Показать все аудиты")) {
                            auditTable.showAllAudits();
                        } else if (otvet.equals("Удалить таблицу аудитов")) {
                            auditTable.delete();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить аудит")) {
                            Integer departamentID = Integer.valueOf(arrayList.get(2));
                            String nameAudit = arrayList.get(3);
                            String dataAudit = arrayList.get(4);
                            String markAudit = arrayList.get(5);
                            String recommendations = arrayList.get(6).substring(arrayList.get(6).indexOf("*")+1, arrayList.get(6).lastIndexOf("*"));
                            auditTable.addAudit(new Audit(departamentID, nameAudit, dataAudit, markAudit, recommendations));
                        }
                    }
                }else if (dbName.equals("buildings")){
                    BuildingTable buildingTable = BuildingTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить строение 1 НазваниеСтроения Адресс Смотритель  '" + "\n"
                            + "'Удалить таблицу строений'" + "\n"
                            + "'Показать все строения'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Показать все строения")) {
                            buildingTable.showAllBuildings();
                        } else if (otvet.equals("Удалить таблицу строений")) {
                            buildingTable.delete();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить строение")) {
                            Integer departamentID = Integer.valueOf(arrayList.get(2));
                            String nameBuilding = arrayList.get(3);
                            String adress = arrayList.get(4);
                            String watchmen = arrayList.get(5);
                            buildingTable.addBuilding(new Building(departamentID,nameBuilding,adress,watchmen));
                        }
                    }
                }else if (dbName.equals("rooms")){
                    RoomTable roomTable = RoomTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить комнату 1 Номер Площадь Тип'" + "\n"
                            + "'Удалить таблицу комнат'" + "\n"
                            + "'Показать все комнаты'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Показать все комнаты")) {
                            roomTable.showAllRooms();
                        } else if (otvet.equals("Удалить таблицу комнат")) {
                            roomTable.delete();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)).equals("Добавить комнату")) {
                            Integer buildingID = Integer.valueOf(arrayList.get(2));
                            Integer number = Integer.valueOf(arrayList.get(3));
                            Integer floor = Integer.valueOf(arrayList.get(4));
                            String type = arrayList.get(5);
                            roomTable.addRoom(new Room(buildingID,number,floor,type));
                        }
                    }
                }else if (dbName.equals("roomsInfo")){
                    RoomInfoTable roomInfoTable = RoomInfoTable.getInstance();
                    System.out.println("Доступны следующие команды:\n'Добавить описание комнаты Длина Ширина Высота Объем Этаж'" + "\n"
                            + "'Удалить таблицу комнат'" + "\n"
                            + "'Показать все комнаты'");
                    while (true) {
                        String otvet = scanner.nextLine().trim();
                        List<String> arrayList = Arrays.asList(otvet.split(" "));
                        if (otvet.equals("Показать все комнаты")) {
                            roomInfoTable.showAllRoomInfo();
                        } else if (otvet.equals("Удалить таблицу комнат")) {
                            roomInfoTable.delete();
                        }else if (otvet.equals("back")){
                            break;
                        } else if ((arrayList.get(0) + " " + arrayList.get(1)+" "+arrayList.get(2)).equals("Добавить описание комнаты")) {
                            Float length = Float.valueOf(arrayList.get(3));
                            Float width = Float.valueOf(arrayList.get(4));
                            Float height = Float.valueOf(arrayList.get(5));
                            Float volume = Float.valueOf(arrayList.get(6));
                            Integer floor = Integer.valueOf(arrayList.get(7));
                            roomInfoTable.addRoomInfo(new RoomInfo(length,width,height,volume,floor));
                        }
                    }
                }
            }
            // Добавляем запись
            // Получаем все записи и выводим их на консоль
//            List<Model.Faculty> facultyList = facultyDB.getAllFacultyes();
//            for (Model.Faculty faculty : facultyList) {
//                System.out.println(faculty.toString());
//            }
            // Удаление записи с id = 8
            //dbHandler.deleteProduct(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
