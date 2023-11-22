package rs.edu.shipping.delivery.entity;

import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rs.edu.shipping.user.entity.UserEntity;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class OrdersEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "location_from")
  private String locationFrom;
  @Column(name = "city_from")
  private String cityFrom;
  @Column(name = "location_to")
  private String locationTo;
  @Column(name = "city_to")
  private String cityTo;
  private BigDecimal weight;
  @Column(name = "delivery_date")
  private Date deliveryDate;
  private boolean active;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user", referencedColumnName = "username")
  private UserEntity user;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "courier", referencedColumnName = "id")
  private CourierEntity courier;
}
