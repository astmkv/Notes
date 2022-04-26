package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Note {

    public Note() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private String name;            // название (имя) заметки
    private String priority;        // приоритет заметки
    private String status;          // статус заметки
    private Date dueDate;           // срок окончания
    private String content;         // содержимое

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", dueDate=" + dueDate +
                ", content='" + content + '\'' +
                '}';
    }

    public Note (String name, String priority, String status, Date dueDate, String content) {
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.content = content;
    }

    public void choice(Scanner in) throws IOException {

        int select = 0;
        do {
            System.out.println("МЕНЮ: ");
            System.out.println("1 - Создать заметку \n" +
                    "2 - Сохранить заметку \n" +
                    "3 - Удалить заметку \n" +
                    "4 - Показать все заметки \n" +
                    "5 - Завершить программу");

             select = in.nextInt();
            in.nextLine();
            switch (select) {
                case 1 -> createNote(in);
                case 2 -> saveNote();
                case 3 -> deleteNote();
                case 4 -> showNotes();
            }
        } while (select < 5);
            exit();
    }
    // метод создания заметки
    public void createNote(Scanner ex) {

        System.out.print("Краткое название: ");
        name = ex.nextLine();
        ex.nextLine();
        System.out.print("Приоритет: ");
        priority = ex.nextLine();
        ex.nextLine();
        System.out.print("Содержимое: ");
        content = ex.nextLine();
        ex.nextLine();
        System.out.print("Срок (укажите целое число дней): ");
        int time = ex.nextInt();

        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        now.set(Calendar.DAY_OF_MONTH, day + time);

        dueDate = now.getTime();
        int day1 = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH);

        status = checkDueDate(day1,month);
    }

    // метод проверки дня и месяца для установления статуса заметки
    public String checkDueDate(int day, int month){
        Calendar cal = Calendar.getInstance();
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);
        int nowMonth = cal.get(Calendar.MONTH);

        if (month >= nowMonth) {
            return "актуально";
        } else if (month == nowMonth) {
            if (day >= nowDay) {
                return "актуально";
            } else return "просрочено";
        } else return "просрочено";
    }

    // метод сохранения заметки
    public void saveNote() throws IOException {

          String nameStream = "Название: " + name;
          String contentStream = "Содержимое: " + content;
          String priorityStream = "Приоритет: " + priority;
          String dueDateStream = "Срок: " + dueDate;
          String statusStream = "Статус: " + status;
          String str = nameStream + "\n" + contentStream + "\n" + priorityStream + "\n" + dueDateStream + "\n" + statusStream + "\n\n";
          FileOutputStream fos = new FileOutputStream("C://Users/astmk/Desktop/Notes.txt",  true);
          byte[] buff = str.getBytes();
          fos.write(buff);
    }
    // метод удаления заметки
    public void deleteNote() throws IOException {
        String str = "";
        FileOutputStream fos = new FileOutputStream("C://Users/astmk/Desktop/Notes.txt");
        byte[] buff = str.getBytes();
        fos.write(buff);
        fos.close();
    }


    // метод просмотра статусов заметок
    public void showNotes() throws IOException {
        ArrayList<Character> listBIS = new ArrayList<Character>();
        FileReader fr = new FileReader("C://Users/astmk/Desktop/Notes.txt");
        StringBuilder sb = new StringBuilder();
        int code = -1;
        while ((code = fr.read()) != -1) {
            sb.append(Character.toChars(code));
        }
        System.out.println(sb.toString());
        fr.close();
    }

    // метод завершения программы
    public void exit(){
        System.exit(0);
    }
}
