package DatabaseLogic;

import java.sql.SQLException;
import java.util.Objects;

public class Session extends BaseEntity{

    private String sessionUUID;
    private String eventUUID;
    private String sessionName;
    private int maxAttendees;
    private String description;
    private String summary;
    private String location;
    private String speaker;
    private String dateTimeStart;
    private String dateTimeEnd;
    private String type;
    private float price;

    public Session(int SessionId, int Entity_version, int active, String Timestamp,
                   String SessionUUID, String eventUUID, String SessionName, int maxAttendees, String description, String summary, String DateTimeStart, String DateTimeEnd, String Speaker, String location, String Type, float price) {

        super(SessionId,Entity_version,active,Timestamp);

        this.sessionUUID = SessionUUID;
        this.eventUUID=eventUUID;
        this.sessionName = SessionName;
        this.maxAttendees = maxAttendees;
        this.description = description;
        this.summary = summary;
        this.dateTimeStart = DateTimeStart;
        this.dateTimeEnd = DateTimeEnd;
        this.speaker = Speaker;
        this.location = location;
        this.type = Type;
        this.price = price;

    }

    public int getSessionId() {
        // get id from inherited class
        return this.getEntityId();
    }
    public void setSessionId(int sessionId) {
        this.setEntityId(sessionId);
    }

    public String getEventUUID() {
        return eventUUID;
    }
    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public String getSessionUUID() {
        return sessionUUID;
    }
    public void setSessionUUID(String Uuid) {
        this.sessionUUID = Uuid;
    }

    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }
    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }
    public void setDateTimeStart(String dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }
    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getSpeaker() {
        return speaker;
    }
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return getMaxAttendees() == session.getMaxAttendees() &&
                Float.compare(session.getPrice(), getPrice()) == 0 &&
                Objects.equals(getSessionUUID(), session.getSessionUUID()) &&
                Objects.equals(getEventUUID(), session.getEventUUID()) &&
                Objects.equals(getSessionName(), session.getSessionName()) &&
                Objects.equals(getDescription(), session.getDescription()) &&
                Objects.equals(getSummary(), session.getSummary()) &&
                Objects.equals(getLocation(), session.getLocation()) &&
                Objects.equals(getSpeaker(), session.getSpeaker()) &&
                Objects.equals(getDateTimeStart(), session.getDateTimeStart()) &&
                Objects.equals(getDateTimeEnd(), session.getDateTimeEnd()) &&
                Objects.equals(getType(), session.getType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getSessionUUID(), getEventUUID(), getSessionName(), getMaxAttendees(), getDescription(), getSummary(), getLocation(), getSpeaker(), getDateTimeStart(), getDateTimeEnd(), getType(), getPrice());
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionUUID='" + sessionUUID + '\'' +
                ", eventUUID='" + eventUUID + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", maxAttendees=" + maxAttendees +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", location='" + location + '\'' +
                ", speaker='" + speaker + '\'' +
                ", dateTimeStart='" + dateTimeStart + '\'' +
                ", dateTimeEnd='" + dateTimeEnd + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}

