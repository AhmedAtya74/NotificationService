package notificationmodel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        dbRepository dbrepo = new dbRepository();
        Service ob1 = new Service(dbrepo);

        Scanner input = new Scanner(System.in);
        int ch;
        System.out.println("what do you want to do");
        System.out.println("1- Read notification ");
        System.out.println("2- create notification ");
        System.out.println("3- Delete notification ");
        System.out.println("4- Update notification ");
        System.out.println("5- Prepair notification ");
        System.out.println("6- Send notification ");
        ch = input.nextInt();
        if (ch == 1) {
            int id;
            System.out.println("please enter notification id");
            id = input.nextInt();
            ob1.readTemplate(id);
        } else if (ch == 2) {
            Template notifytemp = new Template();

            boolean result = ob1.createNotificationTemplate(notifytemp);
            if (result == true) {
                System.out.println("notification is created");
            } else {
                System.out.println("notification is not created");
            }

        } else if (ch == 3) {
            int id;
            System.out.println("please enter notification id");
            id = input.nextInt();
            ob1.deleteTemplate(id);
        } else if (ch == 4) {
            int id;
            System.out.println("please enter notification id");
            id = input.nextInt();
            ob1.updateTemplate(id);
        } else if (ch == 5) {
            int id;
            System.out.println("please enter notification id");
            id = input.nextInt();
            ob1.perpareToSend(id);
        }
        else if (ch == 6) {
            int id2;
            System.out.println("please enter notification id");
            id2 = input.nextInt();

            ob1.send(id2);
        }

    }

}
