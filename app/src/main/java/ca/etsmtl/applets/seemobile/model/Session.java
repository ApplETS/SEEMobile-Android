package ca.etsmtl.applets.seemobile.model;

import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gnut3ll4 on 28/12/15.
 */
public class Session {

    private String session;

    public Session() {
        DateTime dateTime = DateTime.now();
        int period = (dateTime.monthOfYear().get() - 1) / 4 + 1;
        session = dateTime.year().getAsShortText() + period;
    }

    public Session(String session) {
        Pattern p = Pattern.compile("^20[1-9][0-9][1-3]$");
        Matcher m = p.matcher(session);
        if (m.matches()) {
            this.session = session;
        }
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Session getSessionBefore() {

        int period = Integer.valueOf(session.substring(4));
        int year = Integer.valueOf(session.substring(0, 4));
        if (period == 1) {
            period = 3;
            year--;
        } else {
            period--;
        }
        return new Session(year + "" + period);

    }
}
