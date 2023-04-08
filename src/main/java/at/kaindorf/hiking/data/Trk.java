package at.kaindorf.hiking.data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@XmlRootElement(name = "trk")
@Getter
@ToString
public class Trk {
    @XmlElement(name = "name")
    private String name;
    @XmlElementWrapper(name = "trkseg")
    @XmlElement(name = "trkpt")
    private List<TrackingPoint> trackingPoints;
}
