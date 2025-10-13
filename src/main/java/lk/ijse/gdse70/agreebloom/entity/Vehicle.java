package lk.ijse.gdse70.agreebloom.entity;


import jakarta.persistence.*;
import lk.ijse.gdse70.agreebloom.annotation.CustomGenerator;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Vehicle {

    @Id
    @CustomGenerator(prefix = "CAR-")
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private  String remarks;


    @ManyToOne
    @JoinColumn(name = "staffId", referencedColumnName = "staffId")
    private Staff staff;



}
