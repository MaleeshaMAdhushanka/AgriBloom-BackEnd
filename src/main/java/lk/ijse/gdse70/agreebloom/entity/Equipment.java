package lk.ijse.gdse70.agreebloom.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lk.ijse.gdse70.agreebloom.annotation.CustomGenerator;
import lk.ijse.gdse70.agreebloom.enums.EquipmentStatus;
import lk.ijse.gdse70.agreebloom.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Equipment {

    @Id
    @CustomGenerator(prefix = "EQUIPMENT-")
    private String equipmentId;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private Field field;




}
