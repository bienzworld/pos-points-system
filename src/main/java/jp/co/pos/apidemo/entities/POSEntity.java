package jp.co.pos.apidemo.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="POSEntity")
public class POSEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="DATETIME")
    private Timestamp dateTime;

    @Getter
    @Setter
    @Column(name="SALES")
    private double sales;

    @Getter
    @Setter
    @Column(name="POINTS")
    private int points;

    @Getter
    @Setter
    @Column(name="PAYMENT_METHOD")
    private String paymentMethod;

    public POSEntity() {
    }

    public POSEntity(@NonNull Timestamp dateTime, @NonNull double sales, @NonNull int points,
                     @NonNull String paymentMethod) {
        this.dateTime = dateTime;
        this.sales = sales;
        this.points = points;
        this.paymentMethod = paymentMethod;
    }

}
