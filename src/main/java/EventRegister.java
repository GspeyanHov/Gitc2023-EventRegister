import commands.EventCommands;
import manager.EventManager;
import manager.UserManager;
import model.Event;
import model.EventType;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class EventRegister implements EventCommands {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Scanner SCANNER = new Scanner(System.in);
    private static EventManager eventManager = new EventManager();
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            EventCommands.printCommands();
            int command = Integer.parseInt(SCANNER.nextLine());
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_EVENT:
                    addEvent();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case SHOW_ALL_EVENTS:
                    showAllEvents();
                    break;
                case SHOW_ALL_USER:
                    showAllUsers();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void showAllUsers() {
        List<User> All = userManager.getAll();
        for (User user : All) {
            System.out.println(user);
        }
    }

    private static void showAllEvents() {
        List<Event> All = eventManager.getAll();
        for (Event event : All) {
            System.out.println(event);
        }
    }

    private static void addUser() {
        showAllEvents();
        System.out.println("please choose event id");
        int eventId = Integer.parseInt(SCANNER.nextLine());
        Event event = eventManager.getById(eventId);
        if (event == null) {
            System.out.println("please choose correct event id");
        } else {
            System.out.println("please input user's name, surname, email");
            String userDataStr = SCANNER.nextLine();
            String[] userData = userDataStr.split(",");
            User user = User.builder()
                    .name(userData[0])
                    .surname(userData[1])
                    .email((userData[2]))
                    .event(event)
                    .build();
            userManager.add(user);
            System.out.println("user added");
        }

    }

    private static void addEvent() {
        System.out.println("please input event's name, place, isOnline, eventTYpe(FILM, GAME, SPORT), price,date(yyyy-MM-dd HH:mm:ss)");
        String eventDataStr = SCANNER.nextLine();
        String[] eventData = eventDataStr.split(",");
        Event event = null;
        try {
            event = Event.builder()
                    .name(eventData[0])
                    .place(eventData[1])
                    .isOnline(Boolean.valueOf(eventData[2]))
                    .eventType(EventType.valueOf(eventData[3]))
                    .price(Double.parseDouble(eventData[4]))
                    .eventDate(sdf.parse(eventData[5]))
                    .build();

            eventManager.add(event);
            System.out.println("event added");

        } catch (ParseException e) {
            System.out.println("invalid date format");
        }

    }
}
