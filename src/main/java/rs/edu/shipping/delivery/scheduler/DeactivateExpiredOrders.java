package rs.edu.shipping.delivery.scheduler;

import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.shipping.delivery.service.OrdersService;

@Component
@AllArgsConstructor
public class DeactivateExpiredOrders {

  private final OrdersService ordersService;

  @Scheduled(
      cron = "${scheduled.expired-orders-deactivator.cron}",
      zone = "${time.default-timezone}"
  )
  @SchedulerLock(name = "expired-orders-deactivator", lockAtMostFor = "30m", lockAtLeastFor = "1m")
  @Transactional
  public void deactivateExpiredOrders() {
    ordersService.deactivateExpiredOrders();
  }

}
