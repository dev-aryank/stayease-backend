package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
