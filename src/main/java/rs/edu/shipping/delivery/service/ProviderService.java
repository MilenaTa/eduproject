package rs.edu.shipping.delivery.service;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.entity.ProviderEntity;
import rs.edu.shipping.delivery.repository.ProviderEntityRepository;

@Service
@RequiredArgsConstructor
public class ProviderService {

  private final ProviderEntityRepository providerEntityRepository;

  //CRUD

  public void update(String name, BigDecimal courierFee){
    var provider = getByName(name).get();
    provider.setCourierFee(courierFee);
    providerEntityRepository.save(provider);
  }

  public void create(String name, BigDecimal courierFee){
    ProviderEntity provider = ProviderEntity.builder()
        .name(ProviderName.valueOf(name))
        .courierFee(courierFee)
        .build();
    providerEntityRepository.save(provider);
  }

  public Optional<ProviderEntity> getByName(String name){
    return providerEntityRepository.getProviderEntityByName(ProviderName.valueOf(name));
  }
}
