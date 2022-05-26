package src.com.company.services;

import src.com.company.users.Action;

import java.text.ParseException;
import java.util.Date;

public class AuditService {
    private static AuditService instance;

    private AuditService() throws ParseException, InterruptedException, IllegalAccessException {
    }

    public static AuditService createInstance() throws ParseException, InterruptedException, IllegalAccessException {
        if (instance == null){
            instance = new AuditService();
        }
        return instance;
    }

    void audit(int id, String name) throws IllegalAccessException {
        Action a = new Action(id, name, new Date());
        WriteService.write("src/com/company/data/Audit.csv", a, Action.class);
    }

}
