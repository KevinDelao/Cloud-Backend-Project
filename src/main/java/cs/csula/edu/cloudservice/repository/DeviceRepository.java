package cs.csula.edu.cloudservice.repository;

import cs.csula.edu.cloudservice.entity.device.Device;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

  int countDevicesByName(String name);

  Optional<Device> getDeviceByName(String name);
}
