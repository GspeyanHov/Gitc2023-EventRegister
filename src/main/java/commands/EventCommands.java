package commands;

public interface EventCommands {

    int EXIT = 0;
    int ADD_EVENT = 1;
    int ADD_USER = 2;
    int SHOW_ALL_EVENTS = 3;
    int SHOW_ALL_USER = 4;

    static void printCommands(){
        System.out.println("Please input " + EXIT + "for exit ");
        System.out.println("Please input " + ADD_EVENT + "for add event ");
        System.out.println("Please input " + ADD_USER + "for add user ");
        System.out.println("Please input " + SHOW_ALL_EVENTS + "for show all events ");
        System.out.println("Please input " + SHOW_ALL_USER + "for show all users ");
    }
}
