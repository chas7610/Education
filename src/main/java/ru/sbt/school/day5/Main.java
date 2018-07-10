package ru.sbt.school.day5;

import ru.sbt.school.day5.model.Person;
import ru.sbt.school.day5.model.TerminalImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        TerminalImpl terminal = TerminalImpl.initTerminal(5000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = new Person();
        try {
            System.out.print("Введите имя: ");
            String name = reader.readLine();
            System.out.print("Введите id: ");
            long id = Long.parseLong(reader.readLine());
            person = new Person(name, id, 0);
        }catch (IOException ex){ex.printStackTrace();}

        printInfo();

        String caseInt = "";
        try {
            while ((caseInt = reader.readLine()) != "q") {
                switch (caseInt) {
                    case "1": {
                        printPinInfo(terminal, reader, person);
                        System.out.print("Баланс: ");
                        terminal.checkBalance(person.getAccountId());
                        break;
                    }
                    case "2": {
                        printPinInfo(terminal, reader, person);
                        do {
                            System.out.print("Положить...");
                        }while(!terminal.putToAccoun(person.getAccountId(), Integer.parseInt(reader.readLine())));
                        break;
                    }
                    case "3": {
                        printPinInfo(terminal, reader, person);
                        do{
                            System.out.print("Снять...");
                        }while(!terminal.pullFromAccount(person.getAccountId(), Integer.parseInt(reader.readLine())));

                        break;
                    }
                    case "q": {
                        return;
                    }
                }
                System.out.println();
                printInfo();
            }


        }catch (IOException ex){}
    }

    private static void printPinInfo(TerminalImpl terminal, BufferedReader reader, Person person) {
        do {
            System.out.print("Введите Pin: ");
            try {
                int pin = Integer.parseInt(reader.readLine());
                person.setPin(pin);
            }catch (IOException ex){ex.printStackTrace();}
        }while (!terminal.validatePin(person.getAccountId(), person.getPin()));
    }

    private static void printInfo(){
        System.out.println("Баланс: 1");
        System.out.println("Внести наличные: 2");
        System.out.println("Снять наличные: 3");
        System.out.println("Выйти: q");
    }
}

