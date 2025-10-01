package lk.ijse.gdse70.agreebloom.entity;


import jakarta.persistence.*;
import lk.ijse.gdse70.agreebloom.annotation.CustomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Field {

    @Id
    @CustomGenerator(prefix = "FIELD-")
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double extentSize;
    private String fieldImage1;
    private String fieldImage2;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Crop> crops;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "field_staff",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<Staff> staff;

}
