package at.kaindorf.hiking.data;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
public class TrackingPoint {
    @XmlAttribute(name = "lat")
    private double lat;
    @XmlAttribute(name = "lon")
    private double lon;
    @XmlElement(name = "ele")
    private double ele;
    @XmlElement(name = "time")
    @XmlJavaTypeAdapter(InstantAdapter.class)
    private Instant time;
}
