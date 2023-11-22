package rs.edu.shipping.delivery.scheduler;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.shipping.delivery.service.OrdersService;

@Component
@AllArgsConstructor
public class NotifyCourier {

  private final OrdersService ordersService;

  @Scheduled(
      cron = "${scheduled.courier-notifier.cron}",
      zone = "${time.default-timezone}"
  )
  @SchedulerLock(name = "courier-notifier", lockAtMostFor = "30m", lockAtLeastFor = "1m")
  @Transactional
  public void NotifyCourier() {
    ordersService.getMessage();
  }

}
