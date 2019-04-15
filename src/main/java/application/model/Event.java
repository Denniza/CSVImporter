package application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable=false, unique = true)
    private long id;

    private String ssoid;
    private String type;
    private String subtype;
    private String url;
    private String orgid;
    private String grp;
    private String code;
    private String ltpa;
    private String sudirresponse;
    private String formId;

    @Temporal(TemporalType.TIME)
    private Date time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Event(String ssoid,
                 String type,
                 String subtype,
                 String url,
                 String orgid,
                 String grp,
                 String code,
                 String ltpa,
                 String sudirresponse,
                 String formId,
                 Date time,
                 Date date) {
        this.ssoid = ssoid;
        this.type = type;
        this.subtype = subtype;
        this.url = url;
        this.orgid = orgid;
        this.grp = grp;
        this.code = code;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        this.formId = formId;
        this.time = time;
        this.date = date;
    }
}
