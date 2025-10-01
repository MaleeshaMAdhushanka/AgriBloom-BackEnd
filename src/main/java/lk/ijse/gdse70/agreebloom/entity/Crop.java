package lk.ijse.gdse70.agreebloom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lk.ijse.gdse70.agreebloom.annotation.CustomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Crop {

    @Id
    @CustomGenerator(prefix = "CROP-")
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String image;
    private String category;
    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "fieldCode", referencedColumnName = "fieldCode")
    private Field field;




}
